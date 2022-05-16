package com.everis.evereval.manager.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Candidate;
import com.everis.evereval.dao.entity.Quiz;
import com.everis.evereval.dao.entity.enums.Type;
import com.everis.evereval.dao.repository.CandidateRepository;
import com.everis.evereval.mailsender.MailConfig;
import com.everis.evereval.manager.dto.AnswerDTO;
import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.ProposedResponseDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.service.AnswerService;
import com.everis.evereval.manager.service.CandidateService;
import com.everis.evereval.manager.transformer.CandidateTransformer;
import com.everis.evereval.manager.transformer.QuizTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class CandidateServiceImp extends GenericServiceImpl<Candidate, CandidateDTO, Long> implements CandidateService {

	private static Transformer<Candidate, CandidateDTO> t = new CandidateTransformer();
	private static Transformer<Quiz, QuizDTO> tt = new QuizTransformer();
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private MailConfig emailConfig;

	@Value("${evereval.quiz.link}")
	private String quizBasicLink;

	public CandidateServiceImp() {
		super(t);

	}

	@Override
	public CandidateDTO findByMail(String mail) {

		return t.toDTO(candidateRepository.findByMail(mail));
	}

	@Override
	public CandidateDTO findByToken(String Token) {
		Candidate candidate = candidateRepository.findByToken(Token);

		if (candidate != null) {
			Calendar currentDate = Calendar.getInstance();
			Calendar tokenExpirationDate = Calendar.getInstance();
			tokenExpirationDate.setTime(candidate.getTokenExpirationDate());

			if (ChronoUnit.DAYS.between(tokenExpirationDate.toInstant(), currentDate.toInstant()) >= 1) {
				candidate.setToken(null);
				candidate.setQuiz(null);
				this.candidateRepository.save(candidate);
				return null;
			} else {
				return t.toDTO(candidate);
			}
		}

		return null;

	}

	@Override
	public CandidateDTO findByQuiz(QuizDTO quiz) {
		return t.toDTO(candidateRepository.findByQuiz(tt.toEntity(quiz)));
	}

	@Override
	public Iterable<CandidateDTO> findAllByQuizEvaluated(boolean evaluated) {
		return t.toDTOList(candidateRepository.findAllByQuizEvaluated(evaluated));
	}

	@Override
	public Iterable<CandidateDTO> findAllByQuizNull() {
		return t.toDTOList(candidateRepository.findAllByQuizNull());
	}

	@Override
	public CandidateDTO findByQuizId(Long id) {
		return t.toDTO(this.candidateRepository.findByQuizId(id));
	}

	public double updateScore(QuizDTO quizDTO) {
		// TODO: Calculate score and save it
		double score = this.calculateScore(quizDTO.getQuizQuestions());
		System.out.println("The final score is : " + score);

		return score;
	}

	private double calculateScore(List<QuizQuestionDTO> quizQuestions) {

		double totalAnswersScore = 0;
		double quizQuestionTotalScore = 0;
		double totalScore = 0;

		for (QuizQuestionDTO quizQuestionDTO : quizQuestions) {
			quizQuestionTotalScore += quizQuestionDTO.getScore();

			List<ProposedResponseDTO> proposedResponses = quizQuestionDTO.getProposedResponses();
			List<AnswerDTO> answers = quizQuestionDTO.getAnswers();

			/*
			 * Valid the answers
			 */

			// If RADIO
			if (quizQuestionDTO.getType().equals(Type.RADIO)) {
				for (ProposedResponseDTO proposedResponse : proposedResponses) {

					for (AnswerDTO answer : answers) {

						if (proposedResponse.getContent().equals(answer.getContent()) && proposedResponse.isState()) {
							// Set score in the answer..
							for (int i = 0; i < answers.size(); i++) {
								if (answers.get(i).getId() == answer.getId()) {
									answers.get(i).setScore(quizQuestionDTO.getScore());
								}
							}

							// Calculate the score ...
							totalAnswersScore += quizQuestionDTO.getScore();
						}

					}

				}
			} // End RADIO

			// If CHECKBOX
			boolean allAnswersIsValid = true;
			Long currentquizQuestionId = quizQuestionDTO.getId();

			if (quizQuestionDTO.getType().equals(Type.CHECKBOX)) {

				int trueAnswersSize = answers.size();
				long trueProposedResponsesSize = proposedResponses.stream()
						.filter(proposedResponse -> proposedResponse.isState()).count();

				for (AnswerDTO answer : answers) {

					for (ProposedResponseDTO proposedResponse : proposedResponses) {
						if (proposedResponse.getContent().equals(answer.getContent()) && !proposedResponse.isState()) {
							allAnswersIsValid = false;
							break;
						}
					}

				}

				if (trueAnswersSize != trueProposedResponsesSize) {
					allAnswersIsValid = false;
				}

				if (allAnswersIsValid && currentquizQuestionId == quizQuestionDTO.getId()) {
					// Set score to this question
					for (int i = 0; i < answers.size(); i++) {
						for (int j = 0; j < quizQuestionDTO.getAnswers().size(); j++) {
							double questionScore = quizQuestionDTO.getScore();

							answers.get(i).setScore(questionScore / quizQuestionDTO.getAnswers().size());
						}
					}

					// Calculate the score ...
					totalAnswersScore += quizQuestionDTO.getScore();
				}
			} // End CHECKBOX

			// If TEXTFIELD or TEXTAREA
			if (quizQuestionDTO.getType().equals(Type.TEXTFIELD) || quizQuestionDTO.getType().equals(Type.TEXTAREA)) {
				// Calculate the score ...
				totalAnswersScore += quizQuestionDTO.getAnswers().get(0).getScore();
			}

			System.out.println("Answers : ");
			answers.stream().forEach(a -> System.out.println(a.getContent()));
			answers.stream().forEach(a -> System.out.println(a.getScore()));

//			for (AnswerDTO answer : answers) {
//				this.answerService.sav
//			}
			this.answerService.saveAll(answers);

		}

		totalScore = (totalAnswersScore / quizQuestionTotalScore) * 100;

		System.out.println("Quiz Question Total Score: " + quizQuestionTotalScore);
		System.out.println("Total Answers Score: " + totalAnswersScore);
		System.out.println("Total Score: " + totalScore);

		return totalScore;
	}

	@Override
	public void generateTokenForCandidate(Long candidateId) {

		Candidate candidate = candidateRepository.findById(candidateId).get();
		candidate.setToken(UUID.randomUUID().toString());

		// calculate expiration date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 48);

		candidate.setTokenExpirationDate(calendar.getTime());

		candidateRepository.save(candidate);

	}

	@Override
	public void sendConvocationToCandidate(Long candidateId) throws MessagingException {
		Candidate candidate = candidateRepository.findById(candidateId).get();

		String subject = "Everis Convocation to Quiz";
		String content = "Hi " + candidate.getName()
				+ ",\n\nWe send you this email to invite you to pass an online quiz on the link: \n\n" + quizBasicLink
				+ candidate.getToken()
				+ "\n\n You have a unique access to the quiz and your token will expire after 48 hours.\n\n Good luck,\n\n Recruitment team.";

		emailConfig.sendEmail(candidate.getMail(), subject, content);
	}

	@Override
	public Iterable<CandidateDTO> findAllByConvoked(boolean convoked) {
		return t.toDTOList(candidateRepository.findAllByConvoked(convoked));
	}

}

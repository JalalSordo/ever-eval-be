package com.everis.evereval.manager.service.impl;

import com.everis.evereval.dao.entity.QuizQuestion;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.service.QuizQuestionService;
import com.everis.evereval.manager.transformer.QuizQuestionTransformer;
import com.everis.evereval.manager.transformer.Transformer;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionServiceImpl extends GenericServiceImpl<QuizQuestion, QuizQuestionDTO, Long>
        implements QuizQuestionService {

    private static Transformer<QuizQuestion, QuizQuestionDTO> t = new QuizQuestionTransformer();

    public QuizQuestionServiceImpl() {
        super(t);
    }

}

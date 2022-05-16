package com.everis.evereval.manager.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.manager.service.GenericService;
import com.everis.evereval.manager.transformer.Transformer;

public class GenericServiceImpl<Entity, DTO, Key> implements GenericService<DTO, Key> {

	@Autowired
	private CrudRepository<Entity, Key> crudeRepository;

	private Transformer<Entity, DTO> t;

	public GenericServiceImpl(Transformer<Entity, DTO> t) {
		this.t = t;
	}

	@Override
	public long count() {
		return crudeRepository.count();
	}

	@Override
	public void delete(DTO dto) {
		crudeRepository.delete(t.toEntity(dto));

	}

	@Override
	public void deleteAll(Iterable<DTO> dtoList) {
		crudeRepository.deleteAll(t.toEntityList(dtoList));

	}

	@Override
	public void deleteAll() {
		crudeRepository.deleteAll();
	}

	@Override
	public void deleteById(Key id) {
		crudeRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Key id) {

		return crudeRepository.existsById(id);
	}

	@Override
	public Iterable<DTO> findAll() {
		return t.toDTOList(crudeRepository.findAll());
	}

	@Override
	public Iterable<DTO> findAllById(Iterable<Key> ids) {

		return t.toDTOList(crudeRepository.findAllById(ids));
	}

	@Override
	public Optional<DTO> findById(Key id) {
		return Optional.of(t.toDTO(crudeRepository.findById(id).get()));
	}

	@Override
	public DTO save(DTO dto) {

		return t.toDTO(crudeRepository.save(t.toEntity(dto)));
	}

	@Override
	public Iterable<DTO> saveAll(Iterable<DTO> dtoList) {
		return t.toDTOList(crudeRepository.saveAll(t.toEntityList(dtoList)));
	}

}

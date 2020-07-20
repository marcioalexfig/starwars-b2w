package com.ma.appjava11.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ma.appjava11.domain.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, Integer> {
	Planeta findByNome(String nome);
	Planeta findById(String id);
}

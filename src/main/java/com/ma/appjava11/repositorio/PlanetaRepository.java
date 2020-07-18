package com.ma.appjava11.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ma.appjava11.domain.Planeta;

public interface PlanetaRepository extends JpaRepository<Planeta, Integer> {
	List<Planeta> findByNome(String nome);
	
}

package com.ma.appjava11.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.appjava11.domain.Planeta;
import com.ma.appjava11.dto.PlanetaDTO;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@PostMapping("/")
	public void gravar() {}
	
	@GetMapping("/")
	public ResponseEntity<List<PlanetaDTO>> listar(){
		List<PlanetaDTO> lista = new ArrayList<>();
		PlanetaDTO p1 = new PlanetaDTO("Alex", "terreno 1", "clima 1");
		PlanetaDTO p2 = new PlanetaDTO("Aline", "terreno 2", "clima 2");
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deletar() {}
	
	@GetMapping("/{nome}")
	public List<PlanetaDTO> buscaPorNome() {
		return null;
	}
	
	@GetMapping("/{id}")
	public List<PlanetaDTO> buscarPorid() {
		return null;
	}
}

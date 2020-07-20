package com.ma.appjava11.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.appjava11.domain.Planeta;
import com.ma.appjava11.dto.PlanetaDTO;
import com.ma.appjava11.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService panetaService;
	
	@PostMapping("/")
	public void gravar() {}
	
	@GetMapping("/")
	public ResponseEntity<List<PlanetaDTO>> listar(){
		List<PlanetaDTO> lista = new ArrayList<>();
		panetaService.listarPlanetas().forEach(planeta -> {
			lista.add(new PlanetaDTO(planeta.getNome(), planeta.getTerreno(), planeta.getClima()));
		});
		return ResponseEntity.ok(lista);
	}
	
	@DeleteMapping("/id/{id}")
	public void deletar() {}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<PlanetaDTO> buscaPorNome(@PathVariable String nome) {
		Planeta planeta = panetaService.buscarPorNome(nome);
		PlanetaDTO planetaDto = new PlanetaDTO(
			planeta.getNome(), 
			planeta.getTerreno(),
			planeta.getClima());
		return ResponseEntity.ok(planetaDto);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<PlanetaDTO> buscarPorid(@PathVariable String id) {
		Planeta planeta = panetaService.buscarPorID(id);
		PlanetaDTO planetaDto = new PlanetaDTO(
			planeta.getNome(), 
			planeta.getTerreno(),
			planeta.getClima());
		return ResponseEntity.ok(planetaDto);
	}
}

package com.ma.appjava11.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ma.appjava11.domain.Planeta;
import com.ma.appjava11.dto.PlanetaDTO;
import com.ma.appjava11.exception.StandardError;
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
	public BodyBuilder deletar(@PathVariable String id) {
		panetaService.removerPlaneta(id);
		return ResponseEntity.ok();
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome) {
		Planeta planeta = panetaService.buscarPorNome(nome);
		if(planeta!=null) {
			PlanetaDTO planetaDto = new PlanetaDTO(
			planeta.getNome(), 
			planeta.getTerreno(),
			planeta.getClima());
			return ResponseEntity.ok(planetaDto);
		} else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError erro = new StandardError(String.valueOf(System.currentTimeMillis()), String.valueOf(status.value()), "Não Encontrado", "" ,"", "/nome/{nome}");
			return ResponseEntity.status(status).body(erro);
		}
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPorid(@PathVariable String id) {
		Planeta planeta = panetaService.buscarPorID(id);
		if(planeta!=null) {
			PlanetaDTO planetaDto = new PlanetaDTO(
				planeta.getNome(), 
				planeta.getTerreno(),
				planeta.getClima());
			return ResponseEntity.ok(planetaDto);
		} else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError erro = new StandardError(String.valueOf(System.currentTimeMillis()), 
					String.valueOf(status.value()), 
					"Não Encontrado", 
					"",
					"", "/id/{id}");
			return ResponseEntity.status(status).body(erro);
		}
	}
}

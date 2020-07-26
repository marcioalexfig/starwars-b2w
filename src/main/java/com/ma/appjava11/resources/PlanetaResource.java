package com.ma.appjava11.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ma.appjava11.domain.Planeta;
import com.ma.appjava11.dto.PlanetResultDTO;
import com.ma.appjava11.dto.PlanetaDTO;
import com.ma.appjava11.exception.StandardError;
import com.ma.appjava11.service.PlanetaService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService panetaService;

	@Value("${api.planetas}")
	public String url;
	
	@PostMapping("/")
	public ResponseEntity<PlanetaDTO> gravar(@RequestBody PlanetaDTO planetaDto) {
		
		WebClient webclient =
				WebClient.builder()
				.baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		Mono<PlanetResultDTO> monoPlaneta = webclient
		.method(HttpMethod.GET)
		.uri("/?search="+planetaDto.getNome())
		.retrieve()
		.bodyToMono(PlanetResultDTO.class);
		
		PlanetResultDTO planResult = monoPlaneta.block();
		
		int qtdFilmes = 0;
		if ( planResult!=null && planResult.getResults()!=null && planResult.getResults().get(0) !=null && planResult.getResults().get(0).getFilms() !=null && planResult.getResults().get(0).getFilms().size()>0 ) {
			qtdFilmes = planResult.getResults().get(0).getFilms().size();
		}
		
		Planeta planeta = new Planeta(planetaDto.getNome(), planetaDto.getClima(), planetaDto.getTerreno(), qtdFilmes);
		Planeta planetaNovo = panetaService.gravarPlaneta(planeta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/id").buildAndExpand(planetaNovo.getId()).toUri();
		PlanetaDTO resultado = new PlanetaDTO(planetaNovo.getNome(),
				planetaNovo.getTerreno(), 
				planetaNovo.getClima(),
				planetaNovo.getQtdFilmes(),
				uri.toString());
		return ResponseEntity.created(uri).body(resultado);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Planeta>> listar(){
		List<Planeta> lista = new ArrayList<>();
		panetaService.listarPlanetas().forEach(planeta -> {
			lista.add(planeta);
		});
		return ResponseEntity.ok(lista);
	}
	
	@DeleteMapping("/{id}/id")
	public ResponseEntity<?> deletar(@PathVariable String id) {
		try {
			panetaService.removerPlaneta(id);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build(); 
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping("/{nome}/nome")
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome) {
		Planeta planeta = panetaService.buscarPorNome(nome);
		if(planeta!=null) {
			PlanetaDTO planetaDto = new PlanetaDTO(
			planeta.getNome(), 
			planeta.getTerreno(),
			planeta.getClima(), 
			planeta.getQtdFilmes(), "");
			return ResponseEntity.ok(planetaDto);
		} else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError erro = new StandardError(String.valueOf(System.currentTimeMillis()), String.valueOf(status.value()), "Não Encontrado", "" ,"", "/nome/{nome}");
			return ResponseEntity.status(status).body(erro);
		}
		
	}
	
	@GetMapping("/{id}/id")
	public ResponseEntity<?> buscarPorid(@PathVariable String id) {
		Planeta planeta = panetaService.buscarPorID(id);
		if(planeta!=null) {
			PlanetaDTO planetaDto = new PlanetaDTO(
				planeta.getNome(), 
				planeta.getTerreno(),
				planeta.getClima(),
				planeta.getQtdFilmes(), "");
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

package com.nextask.nexTask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nextask.nexTask.model.Tarefas;
import com.nextask.nexTask.repository.TarefasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefaController {	
	
	@Autowired
	private TarefasRepository tarefasRepository;
	
	@GetMapping
	public ResponseEntity<List<Tarefas>> getAll(){
		return ResponseEntity.ok(tarefasRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarefas> getById(@PathVariable Long id){
		return tarefasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Tarefas>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(tarefasRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Tarefas> post(@Valid @RequestBody Tarefas tarefas){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(tarefasRepository.save(tarefas));
	}
	
	@PutMapping
	public ResponseEntity<Tarefas> put(@Valid @RequestBody Tarefas tarefas){
		return tarefasRepository.findById(tarefas.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(tarefasRepository.save(tarefas)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tarefas> tarefas = tarefasRepository.findById(id);
		
		if(tarefas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		tarefasRepository.deleteById(id);
	}
}

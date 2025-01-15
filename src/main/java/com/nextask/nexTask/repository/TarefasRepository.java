package com.nextask.nexTask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nextask.nexTask.model.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

	public List <Tarefas> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	
}

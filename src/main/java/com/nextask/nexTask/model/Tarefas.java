package com.nextask.nexTask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_tarefas")
public class Tarefas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O título da tarefa é obrigatório")
	@Size(min = 2, max = 100, message = "O atributo título deve conter no mínimo 2 e no máximo 100 caracteres")
	private String titulo;
	
	@NotBlank(message = "A descrição da tarefa é obrigatória")
	@Size(min = 2, max = 100, message = "O atributo descrição deve conter no mínimo 2 e no máximo 100 caracteres")
	private String descricao;
	
	@NotNull
	@Min(value = 0, message = "O status deve ser no mínimo 0")
    @Max(value = 2, message = "O status deve ser no máximo 2")
    private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

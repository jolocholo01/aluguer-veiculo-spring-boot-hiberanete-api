package com.aluguer.carros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aluguer.carros.model.Pessoa;
import com.aluguer.carros.service.PessoaService;

//http://localhost:8080/veiculos
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	// obter todas as pessoas
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaService.findAll();
	}

	// criar pessoa
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return pessoaService.save(pessoa);
	}

	// Atualizar pessoa
	@PutMapping("/{id}")
	public Pessoa atualizar(@PathVariable(value = "id") Long id, @RequestBody Pessoa pessoa) {
		Pessoa p = pessoaService.findById(id);
		p.setCpf(pessoa.getCpf());
		p.setEmail(pessoa.getEmail());
		p.setNomeCompleto(pessoa.getNomeCompleto());
		return pessoaService.save(p);
	}

	// Apagar pessoa
	@DeleteMapping("/{id}")
	public Map<String, Boolean> eliminar(@PathVariable(value = "id") Long id) {
		Pessoa p = pessoaService.findById(id);
		pessoaService.delete(p);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

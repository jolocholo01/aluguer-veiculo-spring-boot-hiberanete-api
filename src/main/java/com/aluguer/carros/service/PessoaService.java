package com.aluguer.carros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluguer.carros.exception.NegocioException;
import com.aluguer.carros.model.Pessoa;
import com.aluguer.carros.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new NegocioException("Pessoa não foi encontrada!"));
	}

	public void delete(Pessoa p) {
		pessoaRepository.delete(p);
	}
}

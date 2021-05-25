package com.aluguer.carros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluguer.carros.exception.NegocioException;
import com.aluguer.carros.model.PessoaVeiculo;
import com.aluguer.carros.repository.PessoaVeiculoRepository;

@Service
public class PessoaVeiculoService {

	@Autowired
	private PessoaVeiculoRepository pessoaVeiculoRepository;

	public PessoaVeiculo buscarPessoaVeiculoPorId(Long pessoa_veiculo_id) {
		return pessoaVeiculoRepository.findById(pessoa_veiculo_id)
				.orElseThrow(() -> new NegocioException("Nenhum individuo associado a este Veiculo!"));
	}

	public List<PessoaVeiculo> obterPessoasVeiculosPorIdVeiculo(Long veiculo_id) {
		return pessoaVeiculoRepository.obterPessoasVeiculosPorIdVeiculo(veiculo_id);
	}

	public PessoaVeiculo save(PessoaVeiculo pessoaVeiculo) {
		return pessoaVeiculoRepository.save(pessoaVeiculo);
	}
	
}

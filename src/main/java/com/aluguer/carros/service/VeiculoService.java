package com.aluguer.carros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluguer.carros.exception.NegocioException;
import com.aluguer.carros.model.Veiculo;
import com.aluguer.carros.repository.VeiculoRepository;

@Service
public class VeiculoService {
	@Autowired
	private VeiculoRepository veiculoRepository;

	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}
	public void eliminar(Veiculo veiculo) {
		 veiculoRepository.delete(veiculo);
	}

	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	public int quatidadeCarrosPorIsAlugado(boolean alugado) {
		return veiculoRepository.quatidadeCarrosPorIsAlugado(alugado);
	}

	public Veiculo findById(Long veiculo_id) {
		try {
			return veiculoRepository.findById(veiculo_id)
					.orElseThrow(() -> new NegocioException("Veiculo não foi encontrado!"));
		} catch (Exception e) {
			return null;
		}
	}

}

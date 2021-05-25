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

import com.aluguer.carros.exception.NegocioException;
import com.aluguer.carros.model.Veiculo;
import com.aluguer.carros.service.VeiculoService;

//http://localhost:8080/veiculos
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	@Autowired
	private VeiculoService veiculoService;

	// obter todos os veiculos
	@GetMapping
	public List<Veiculo> listar() {
		return veiculoService.findAll();
	}
	@GetMapping("/{id}")
	public Veiculo busvarPorId(@PathVariable(value = "id") Long id) {
		return veiculoService.findById(id);
	}

	// Controle de quantidade de carros na frota mostrando os disponíveis e alugados
	@GetMapping("/frota")
	public Map<String, Integer> listarDisponivelOuNaoDisponivel() {
		int qtdCarrosDisponiveis = veiculoService.quatidadeCarrosPorIsAlugado(false);
		int qtdCarrosAlugagos = veiculoService.quatidadeCarrosPorIsAlugado(true);
		Map<String, Integer> response = new HashMap<>();
		response.put("qtd_carros_disponiveis", qtdCarrosDisponiveis);
		response.put("qtd_carros_alugados", qtdCarrosAlugagos);
		return response;
	}

	// criar veiculo
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo adicionar(@RequestBody Veiculo veiculo) {
		return veiculoService.save(veiculo);
	}

	// Atualizar veiculo
	@PutMapping("/{id}")
	public Veiculo atualizar(@PathVariable(value = "id") Long id, @RequestBody Veiculo veiculo) {
		Veiculo v = veiculoService.findById(id);
		v.setCor(veiculo.getCor());
		v.setAlugado(veiculo.isAlugado());
		v.setMarca(veiculo.getMarca());
		v.setModelo(veiculo.getModelo());
		v.setPlaca(veiculo.getPlaca());
		v.setValorDiario(veiculo.getValorDiario());
		return veiculoService.save(veiculo);
	}

	// Apagar veiculo
	@DeleteMapping("/{id}")
	public Map<String, Boolean> eliminar(@PathVariable(value = "id") Long id) {
		Veiculo veiculo = veiculoService.findById(id);
		if (veiculo.isAlugado()) {
			throw new NegocioException("Impossível eliminar o veiculo, pois já foi alugado.");
		}
		veiculoService.eliminar(veiculo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

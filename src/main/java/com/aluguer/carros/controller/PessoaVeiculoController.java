package com.aluguer.carros.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aluguer.carros.exception.NegocioException;
import com.aluguer.carros.model.Pessoa;
import com.aluguer.carros.model.PessoaVeiculo;
import com.aluguer.carros.model.Veiculo;
import com.aluguer.carros.service.PessoaService;
import com.aluguer.carros.service.PessoaVeiculoService;
import com.aluguer.carros.service.VeiculoService;
import com.aluguer.carros.util.Utilitario;

//http://localhost:8080/veiculos
@RestController
@RequestMapping("/aluguer")
public class PessoaVeiculoController {

//	@Autowired
	private PessoaVeiculoService pessoaVeiculoService;
	@Autowired
	private VeiculoService veiculoService;
	@Autowired
	private PessoaService pessoaService;
	

	// obter todas as pessoas por veiculo
	@GetMapping("/{id}")
	public List<PessoaVeiculo> listar(@PathVariable(value = "id") Long veiculo_id) {
		return pessoaVeiculoService.obterPessoasVeiculosPorIdVeiculo(veiculo_id);
	}

	@GetMapping("/total/{id}")
	public List<PessoaVeiculo> valorTotalDoDiasAlugudoDoVeiculo(@PathVariable(value = "id") Long veiculo_id) {
		List<PessoaVeiculo> pessoaVeiculos = pessoaVeiculoService.obterPessoasVeiculosPorIdVeiculo(veiculo_id);
		int tamanho = pessoaVeiculos.size();
		if (tamanho == 0) {
			throw new NegocioException("Aluguer deste veiculo não encontrado!");
		}
		List<PessoaVeiculo> pessoaVeiculos1 = new ArrayList<>();
		for (PessoaVeiculo pessoaVeiculo : pessoaVeiculos) {
			// obter total de valor por veiculo
			if (pessoaVeiculo.getDataPrevisaoChegada() != null) {
				int qtdDias = (int) Utilitario.calcularDataBanco(pessoaVeiculo.getDataRetirada(),
						pessoaVeiculo.getDataPrevisaoChegada());
				pessoaVeiculo
						.setTotalValorDaViagem((qtdDias * pessoaVeiculo.getVeiculo().getValorDiario().doubleValue()));
			}
			// obter total de valor por veiculo com multas
			if (pessoaVeiculo.getDataDevolucao() != null) {
				int qtdDiasComMultas = (int) Utilitario.calcularDataBanco(pessoaVeiculo.getDataRetirada(),
						pessoaVeiculo.getDataDevolucao());
				pessoaVeiculo.setDataDevolucao(pessoaVeiculo.getDataDevolucao());
				pessoaVeiculo.setTotalValorDaViagemComMultas(
						(qtdDiasComMultas * pessoaVeiculo.getVeiculo().getValorDiario().doubleValue()));
			}
			pessoaVeiculos1.add(pessoaVeiculo);

		}

		return pessoaVeiculos1;
	}

	// obter total de valor por veiculo com multas
	@GetMapping("/pagamento/{id}")
	public PessoaVeiculo receberPagamento(@PathVariable(value = "id") Long pessoa_veiculo_id,
			@RequestBody PessoaVeiculo pessoaVeiculo) {
		System.out.println(" \n\n\n\n id:" +pessoa_veiculo_id);
		PessoaVeiculo pessoaVeiculo_ = pessoaVeiculoService.buscarPessoaVeiculoPorId(pessoa_veiculo_id);
		if (pessoaVeiculo.getDataPrevisaoChegada() != null)
			pessoaVeiculo_.setDataPrevisaoChegada(pessoaVeiculo.getDataPrevisaoChegada());
		pessoaVeiculo_.setDataDevolucao(pessoaVeiculo.getDataDevolucao());
		pessoaVeiculo_ = pessoaVeiculoService.save(pessoaVeiculo_);
		Veiculo veiculo = veiculoService.findById(pessoaVeiculo_.getVeiculo().getId());
		veiculo.setAlugado(false);
		veiculoService.save(veiculo);
		
		if (pessoaVeiculo_.getDataPrevisaoChegada() != null) {
			int qtdDias = (int) Utilitario.calcularDataBanco(pessoaVeiculo_.getDataRetirada(),
					pessoaVeiculo_.getDataPrevisaoChegada());
			pessoaVeiculo_
					.setTotalValorDaViagem((qtdDias * pessoaVeiculo_.getVeiculo().getValorDiario().doubleValue()));
		}
		// obter total de valor por veiculo com multas
		if (pessoaVeiculo_.getDataDevolucao() != null) {
			int qtdDiasComMultas = (int) Utilitario.calcularDataBanco(pessoaVeiculo_.getDataRetirada(),
					pessoaVeiculo_.getDataDevolucao());
			pessoaVeiculo_.setDataDevolucao(pessoaVeiculo.getDataDevolucao());
			pessoaVeiculo_.setTotalValorDaViagemComMultas(
					(qtdDiasComMultas * pessoaVeiculo_.getVeiculo().getValorDiario().doubleValue()));
		}
		
		return pessoaVeiculo_;
	}

	// Adicionar
	@PostMapping("/{pessoa_id}/{veiculo_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaVeiculo adicionar(@PathVariable(value = "pessoa_id") Long pessoa_id,
			@PathVariable("veiculo_id") Long veiculo_id, @RequestBody PessoaVeiculo pessoaVeiculo) {

		Veiculo veiculo = veiculoService.findById(veiculo_id);
		if (veiculo.isAlugado()) {
			throw new NegocioException("O veiculo já foi alugado!");
		}
		Pessoa pessoa = pessoaService.findById(pessoa_id);
		PessoaVeiculo pessoaVeiculo_ = new PessoaVeiculo();
		pessoaVeiculo_.setDataRetirada(pessoaVeiculo.getDataRetirada());
		pessoaVeiculo_.setDataPrevisaoChegada(pessoaVeiculo.getDataPrevisaoChegada());
		pessoaVeiculo_.setPessoa(pessoa);
		pessoaVeiculo_.setVeiculo(veiculo);
		pessoaVeiculo_ = pessoaVeiculoService.save(pessoaVeiculo_);
		veiculo.setAlugado(true);
		veiculoService.save(veiculo);
		return pessoaVeiculo_;
	}

}

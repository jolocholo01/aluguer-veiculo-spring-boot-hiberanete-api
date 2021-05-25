package com.aluguer.carros.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class PessoaVeiculo {
	@Id // É chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // É auto incremento
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	private Date dataRetirada;
	private Date dataPrevisaoChegada;
	private Date dataDevolucao;
	
	/// dados nao criado na base de dados
	@Transient
	private double totalValorDaViagem;
	@Transient
	private double totalValorDaViagemComMultas;
	
	
	public PessoaVeiculo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	

	public Date getDataRetirada() {
		return dataRetirada;
	}


	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}


	public Date getDataPrevisaoChegada() {
		return dataPrevisaoChegada;
	}


	public void setDataPrevisaoChegada(Date dataPrevisaoChegada) {
		this.dataPrevisaoChegada = dataPrevisaoChegada;
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public double getTotalValorDaViagem() {
		return totalValorDaViagem;
	}

	public void setTotalValorDaViagem(double totalValorDaViagem) {
		this.totalValorDaViagem = totalValorDaViagem;
	}

	public double getTotalValorDaViagemComMultas() {
		return totalValorDaViagemComMultas;
	}

	public void setTotalValorDaViagemComMultas(double totalValorDaViagemComMultas) {
		this.totalValorDaViagemComMultas = totalValorDaViagemComMultas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaVeiculo other = (PessoaVeiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
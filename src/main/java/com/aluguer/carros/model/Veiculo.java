package com.aluguer.carros.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Estou pedir para que transforme esta classe em uma tabela na base de dados
public class Veiculo {
	@Id // É chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // É auto incremento
	private Long id;
	private String marca;
	private String modelo;
	private String cor;
	private String placa;
	private BigDecimal valorDiario;
	private boolean alugado = false;

	
	public Veiculo(Long id, String marca, String modelo, String cor, String placa, BigDecimal valorDiario,
			boolean alugado) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.valorDiario = valorDiario;
		this.alugado = alugado;
	}

	public Veiculo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public BigDecimal getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(BigDecimal valorDiario) {
		this.valorDiario = valorDiario;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

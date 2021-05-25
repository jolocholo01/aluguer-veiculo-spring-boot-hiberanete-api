package com.aluguer.carros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluguer.carros.model.PessoaVeiculo;
@Repository
public  interface PessoaVeiculoRepository extends JpaRepository<PessoaVeiculo, Long> {
	@Query("FROM PessoaVeiculo WHERE veiculo_id=:veiculo_id")
	public List<PessoaVeiculo> obterPessoasVeiculosPorIdVeiculo(@Param(value="veiculo_id") Long veiculo_id);
}

package com.aluguer.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluguer.carros.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	@Query("SELECT COUNT(id) FROM Veiculo WHERE alugado=:alugado")
	public int quatidadeCarrosPorIsAlugado(@Param(value="alugado") boolean alugado);

}

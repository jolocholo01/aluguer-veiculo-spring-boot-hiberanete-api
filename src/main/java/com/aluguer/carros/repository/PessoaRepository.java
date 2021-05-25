package com.aluguer.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluguer.carros.model.Pessoa;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

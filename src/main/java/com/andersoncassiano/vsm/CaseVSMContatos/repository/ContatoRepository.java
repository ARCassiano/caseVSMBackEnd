package com.andersoncassiano.vsm.CaseVSMContatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andersoncassiano.vsm.CaseVSMContatos.domain.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	@Query("select c from Contato c WHERE c.cpf = ?1")
	List<Contato> findByCPF(String cpf);
}

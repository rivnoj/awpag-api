package com.rivnoj.awpag.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivnoj.awpag.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  List<Cliente> findByNome(String nome);

  List<Cliente> findByNomeContaining(String nome);

  Optional<Cliente> findByEmail(String email);
}

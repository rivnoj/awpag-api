package com.rivnoj.awpag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivnoj.awpag.domain.model.Parcelamento;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {

}

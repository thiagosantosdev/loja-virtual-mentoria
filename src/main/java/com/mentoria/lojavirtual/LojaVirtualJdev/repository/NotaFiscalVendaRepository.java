package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaFiscalVenda;

@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {

}

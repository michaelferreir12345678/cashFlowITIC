package com.servico.backservico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.servico.backservico.entity.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
    
    @Query("select s from Servico s where s.status = 'cancelado'")
    List<Despesa> buscarServicosCancelados();
}

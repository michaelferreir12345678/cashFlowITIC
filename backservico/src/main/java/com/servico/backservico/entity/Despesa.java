package com.servico.backservico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

// import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "despesa")
@Data


public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricaoDespesa;

    @Temporal(TemporalType.DATE)
    private Date dataDespesa=new Date();

    private String descricaoServico;
    private Double valorDespesa;

    private String status; //"pendente", "realizado" ou "cancelado"
         
}

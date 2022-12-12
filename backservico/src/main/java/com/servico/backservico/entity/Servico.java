package com.servico.backservico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "receitas")
@Data

// classe das receitas 'serviço'

public class Servico{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricaoReceita;

    @Temporal(TemporalType.DATE)
    private Date dataReceita=new Date();

    private String descricaoServico;
    private Double valorReceita;


    private String status; //"Pendente", "Realizado" ou "Cancelado"

    public Servico(long id, String descricaoReceita, Date dataReceita, Double valorReceita) {
		super();
		this.id = id;
		this.descricaoReceita = descricaoReceita;
		this.dataReceita = dataReceita;
		this.valorReceita = valorReceita;
	}

    public Servico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricaoReceita() {
		return descricaoReceita;
	}
	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}
	public Date getDataReceita() {
		return dataReceita;
	}
	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}
	public Double getValorReceita() {
		return valorReceita;
	}
	public void setValorReceita(Double valorReceita) {
		this.valorReceita = valorReceita;
	}
	
};


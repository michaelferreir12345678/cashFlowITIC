package com.servico.backservico.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servico.backservico.entity.Despesa;
import com.servico.backservico.repository.DespesaRepository;
import com.servico.backservico.service.DespesaService;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> buscarTodos(){
         return despesaRepository.findAll();
    }


    public List<Despesa> buscarServicosCancelados(){
        return despesaRepository.buscarServicosCancelados();
    };

    public Despesa inserir(Despesa despesa){
        Despesa despesaBanco = despesaRepository.save(despesa);
        return despesaBanco;         
    }

    public Despesa alterar(Despesa despesa){
        return despesaRepository.saveAndFlush(despesa);
    }

    public void cancelarServico(Long id){
        Despesa despesa = despesaRepository.findById(id).get();
        despesa.setStatus("cancelado");
        despesaRepository.save(despesa);
    }

    public void excluir(Long id){
        Despesa despesa = despesaRepository.findById(id).get();
        despesaRepository.delete(despesa);
    }    
};

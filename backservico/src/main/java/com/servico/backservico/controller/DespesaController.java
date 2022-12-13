package com.servico.backservico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servico.backservico.entity.Despesa;
import com.servico.backservico.service.DespesaService;


@RestController
@RequestMapping("/api/despesas")
public class DespesaController {
    

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/") 
    @CrossOrigin("http://localhost:3000")
    public List<Despesa> buscarTodos(){
        return despesaService.buscarTodos();
    }
    
    @GetMapping("/despesaCancelados")
    public List<Despesa> buscarServicosCancelados(){
        return despesaService.buscarServicosCancelados();
    };

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Despesa inserir(@RequestBody Despesa despesa){
        return despesaService.inserir(despesa);
    }

    @PostMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity <Void> cancelar (@PathVariable("id") Long id){
        despesaService.cancelarServico(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Despesa alterar(@RequestBody Despesa despesa){
        return despesaService.alterar(despesa);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> remover(@PathVariable("id")Long id){
        despesaService.excluir(id);
        return ResponseEntity.ok().build();
    };          
}

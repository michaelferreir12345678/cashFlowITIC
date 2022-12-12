package com.servico.backservico.controller;

// import java.io.IOException;
import java.util.List;

// import java.util.List;

import org.springframework.stereotype.Service;
import org.apache.catalina.connector.Response;
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
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
// import com.servico.backservico.entity.UserExcelImport;
// import com.servico.backservico.repository.ServicoRepository;

@Service
@RestController
@RequestMapping("/api/receitas")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;
    
    @GetMapping("/") 
    @CrossOrigin("http://localhost:3000")
    public List<Servico> buscarTodos(){
        return servicoService.buscarTodos();
    }

    // @CrossOrigin("http://localhost:3000")
    // @ResponseBody
    // @RequestMapping ("/import/excel")
    // private String importFromExcel(){
    //     UserExcelImport excelImport = new UserExcelImport();
    //     List<Servico> listServico = excelImport.excelImport();

    //     return "Import Successfully";        
    //  }
    
    @GetMapping("/receitaCancelados")
    public List<Servico> buscarServicosCancelados(){
        return servicoService.buscarServicosCancelados();
    };

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico inserir(@RequestBody Servico servico){
        return servicoService.inserir(servico);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> remover(@PathVariable("id")Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    };

};

//  response.setContentType("application/octet-stream");
//  String headerKey = "Content-Disposition";
//  String headervalue = "attachment; filename=receitas.xlsx";

//  response.setHeader(headerKey, headervalue);
//  List<Servico> listServicos = servicoService.findAll();
//  UserExcelImport exp = new UserExcelImport(listServicos);
//  exp.export(response);
//  return "Export Successufully";

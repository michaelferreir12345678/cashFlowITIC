package com.servico.backservico.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.excel.UserExcelImport;
import com.servico.backservico.repository.ServicoRepository;


@Controller
@RequestMapping("/api/excel")
public class ExcelController {
    
    @Autowired
    ServicoRepository fileService;
    
    @PostMapping("/upload")
    @CrossOrigin("http://localhost:3000")
    public String importFromExcel() {
        UserExcelImport excelImport=new UserExcelImport();
        List<Servico> listServico = excelImport.excelImport();
        fileService.saveAll(listServico);
        return "Import Successfully";        
    };
}

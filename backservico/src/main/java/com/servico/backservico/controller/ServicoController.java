package com.servico.backservico.controller;

import java.util.ArrayList;
// import java.io.IOException;
import java.util.List;

// import org.springframework.stereotype.Controller;

// import java.util.List;

import org.springframework.stereotype.Service;
// import org.apache.catalina.connector.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.servico.backservico.entity.Servico;
// import com.servico.backservico.entity.UserExcelImport;
import com.servico.backservico.service.ServicoService;
// import com.servico.backservico.entity.UserExcelImport;
// import com.servico.backservico.repository.ServicoRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RestController
@RequestMapping("/api/receitas")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Servico> buscarTodos() {
        return servicoService.buscarTodos();
    }

    @GetMapping("/receitaCancelados")
    public List<Servico> buscarServicosCancelados() {
        return servicoService.buscarServicosCancelados();
    };

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico inserir(@RequestBody Servico servico) {
        return servicoService.inserir(servico);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico alterar(@RequestBody Servico servico) {
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    };

    @RequestMapping(value = "/import/excel", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:3000")
    private List<Servico> processarArquivo(String pathFile) {
        XSSFWorkbook wb = null;
        List<Servico> lista = new ArrayList<Servico>();

        try {
            // Leitura
            FileInputStream fi = new FileInputStream(new File(pathFile));
            // Carregando workbook
            wb = new XSSFWorkbook(fi);
            // Selecionando a primeira aba
            XSSFSheet s = wb.getSheetAt(0);
            int i = 1;
            // Para cada linha da planilha
            for (Row rowFor : s) {
                if (i <= 1) {// Primeira linha com o header
                    i++;
                    continue;
                };
                
                String descricao = rowFor.getCell(0).getStringCellValue();
                java.util.Date data = rowFor.getCell(1).getDateCellValue();
                double valor = rowFor.getCell(2).getNumericCellValue();

                Servico r = new Servico();
                r.setDescricaoReceita(descricao);
                r.setDataReceita(data);
                r.setValorReceita(valor);
                lista.add(r);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    };
};

// response.setContentType("application/octet-stream");
// String headerKey = "Content-Disposition";
// String headervalue = "attachment; filename=receitas.xlsx";

// response.setHeader(headerKey, headervalue);
// List<Servico> listServicos = servicoService.findAll();
// UserExcelImport exp = new UserExcelImport(listServicos);
// exp.export(response);
// return "Export Successufully";

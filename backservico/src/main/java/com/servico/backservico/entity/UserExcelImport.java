package com.servico.backservico.entity;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExcelImport {
	public List<Servico> excelImport(){
		List<Servico> listServico=new ArrayList<>();
		long id=0;
		String descricaoReceita="";
		Date dataReceita=new Date();
		Double valorReceita=0.0;
		
		String excelFilePath="C:\\testingImportFromExcel.xlsx";
		
		long start = System.currentTimeMillis();
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(excelFilePath);
			Workbook workbook=new XSSFWorkbook(inputStream);
			Sheet firstSheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator=nextRow.cellIterator();
				while(cellIterator.hasNext()) {
					Cell nextCell=cellIterator.next();
					int columnIndex=nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						id=(long)nextCell.getNumericCellValue();
						System.out.println(id);
						break;
					case 1:
                        descricaoReceita=nextCell.getStringCellValue();
						System.out.println(descricaoReceita);
						break;
					case 2:
                        dataReceita=nextCell.getDateCellValue();
						System.out.println(dataReceita);
						break;
					case 3:
                        valorReceita=nextCell.getNumericCellValue();
						System.out.println(valorReceita);
						break;				
					}
					listServico.add(new Servico(id, descricaoReceita, dataReceita, valorReceita));
				}
			}
			
			workbook.close();
			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		return listServico;
	};

};













// public class UserExcelImport {    
//     private List<Servico> processarArquivo(String pathFile){
//     	XSSFWorkbook wb = null;
//     	List<Servico> lista = new ArrayList<Servico>();
        
//     	try {    		
//         	// Leitura
//     		FileInputStream fi = new FileInputStream(new File(pathFile));

//     		// Carregando workbook
//     		wb = new XSSFWorkbook(fi);

//     		// Selecionando a primeira aba
//     		XSSFSheet s = wb.getSheetAt(0);
    		    		
//     		int i = 1;
//     		//Para cada linha da planilha
//     		for (Row rowFor : s) {
//     			if(i <= 1){//Primeira linha com o header
//     				i++;
//     				continue;
//     			} 

//     			String descricao = rowFor.getCell(0).getStringCellValue();
//     			java.util.Date data = rowFor.getCell(1).getDateCellValue();
//     			double valor = rowFor.getCell(2).getNumericCellValue();
    			
//     			Servico r = new Servico();
//     			r.setDescricaoReceita(descricao);
//     			r.setDataReceita(data);
//     			r.setValorReceita(valor);
//     			lista.add(r);
//     		}  				    	
//     	}catch(IOException ex) {
//     		ex.printStackTrace();
//     	}finally {
//     		if(wb != null) {
// 				try {
// 					wb.close();
// 				} catch (IOException e) {
// 					e.printStackTrace();
// 				}
//     		}
// 		}	
//     	return lista;
//     }    
// }



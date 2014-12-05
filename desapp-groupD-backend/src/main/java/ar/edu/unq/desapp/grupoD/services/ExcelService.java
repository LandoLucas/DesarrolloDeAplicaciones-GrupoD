package ar.edu.unq.desapp.grupoD.services;

import ar.edu.unq.desapp.grupoD.model.excel.ExcelReader;
import ar.edu.unq.desapp.grupoD.model.excel.ExcelWriter;


public class ExcelService {
	
	private ExcelWriter excelWriter;
	private ExcelReader excelReader;
	
	public void setExcelWriter(ExcelWriter excelWriter) {
		this.excelWriter = excelWriter;
	}
	public void setExcelReader(ExcelReader excelReader) {
		this.excelReader = excelReader;
	}

	public void readExcel(String fileName){
		excelReader.readExcelFile(fileName);
	}
	
	public void writeExcel(){
		excelWriter.writeExcelFile();
	}
	
}

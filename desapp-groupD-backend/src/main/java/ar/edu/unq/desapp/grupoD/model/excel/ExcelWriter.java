package ar.edu.unq.desapp.grupoD.model.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.services.OperationService;

public class ExcelWriter {
	
	private OperationService operationService;
	
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}
	
	public void writeExcelFile(){
		try {
			FileOutputStream fileOut = new FileOutputStream("src/test/resources/exportedTransactions.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Transactions");
			
			//Set Column Widths
			worksheet.setColumnWidth(0, 3000); 
			worksheet.setColumnWidth(1, 3000);
			worksheet.setColumnWidth(2, 6000);
			worksheet.setColumnWidth(3, 7000);
			worksheet.setColumnWidth(4, 3000);
			worksheet.setColumnWidth(5, 3000);
			worksheet.setColumnWidth(6, 3000);
			worksheet.setColumnWidth(7, 3000);
			worksheet.setColumnWidth(8, 4000);
			worksheet.setColumnWidth(9, 4000);
			worksheet.setColumnWidth(10, 3000);
			worksheet.setColumnWidth(11, 3000);
			worksheet.setColumnWidth(12, 4000);
			
			createHeaders(worksheet, workbook);
			List<Operation> report = operationService.findAll();
			
			for(int opNumber=0; opNumber<report.size(); opNumber++){
				// index from 0,0... cell A1 is cell(0,0)
				HSSFRow row1 = worksheet.createRow(opNumber+1);
		
				HSSFCell cellA1 = row1.createCell(0);
				cellA1.setCellValue(report.get(opNumber).getDate().toDate());
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle = workbook.createCellStyle();
				cellStyle.setDataFormat(HSSFDataFormat
						.getBuiltinFormat("m/d/yy"));
				cellStyle.setShrinkToFit(true);
				cellA1.setCellStyle(cellStyle);
				
				HSSFCell cellB1 = row1.createCell(1);
				cellB1.setCellValue(report.get(opNumber).getCategory());
		
				HSSFCell cellC1 = row1.createCell(2);
				cellC1.setCellValue(report.get(opNumber).getSubcategory());
				cellStyle.setWrapText(true);
				cellC1.setCellStyle(cellStyle);
				
				HSSFCell cellD1 = row1.createCell(3);
				cellD1.setCellValue(report.get(opNumber).getConcept());
				cellD1.setCellStyle(cellStyle);
				
				HSSFCell cellE1 = row1.createCell(4);
				cellE1.setCellValue(report.get(opNumber).getShift());
				
				HSSFCell cellF1 = row1.createCell(5);
				cellF1.setCellValue(report.get(opNumber).getPaymentTypes().get(0).getAmount());
				
				HSSFCell cellG1 = row1.createCell(6);
				cellG1.setCellValue(report.get(opNumber).getPaymentTypes().get(1).getAmount());
				
				HSSFCell cellH1 = row1.createCell(7);
				cellH1.setCellValue(report.get(opNumber).getPaymentTypes().get(2).getAmount());
				
				HSSFCell cellI1 = row1.createCell(8);
				cellI1.setCellValue(report.get(opNumber).getTotalInPettyCash());
				
				HSSFCell cellJ1 = row1.createCell(9);
				cellJ1.setCellValue(report.get(opNumber).getTotalInBank());
				
				HSSFCell cellK1 = row1.createCell(10);
				cellK1.setCellValue(report.get(opNumber).getAvailable());
				
				HSSFCell cellL1 = row1.createCell(11);
				cellL1.setCellValue(report.get(opNumber).getDevengado());
				
				HSSFCell cellM1 = row1.createCell(12);
				cellM1.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
				cellM1.setCellValue(report.get(opNumber).isIncome());
			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createHeaders(HSSFSheet worksheet, HSSFWorkbook workbook) {
		HSSFRow row1 = worksheet.createRow(0);
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle = workbook.createCellStyle();
		cellStyle.setShrinkToFit(true);
		
		HSSFCell cellA1 = row1.createCell(0);
		cellA1.setCellValue("FECHA");
		cellA1.setCellStyle(cellStyle);
		
		HSSFCell cellB1 = row1.createCell(1);
		cellB1.setCellValue("CATEGORIA");
		cellB1.setCellStyle(cellStyle);
			
		HSSFCell cellC1 = row1.createCell(2);
		cellC1.setCellValue("SUBCATEGORIA");
		cellC1.setCellStyle(cellStyle);
			
		HSSFCell cellD1 = row1.createCell(3);
		cellD1.setCellValue("CONCEPTO");
		cellD1.setCellStyle(cellStyle);
		
		HSSFCell cellE1 = row1.createCell(4);
		cellE1.setCellValue("TURNO");
		cellE1.setCellStyle(cellStyle);
					
		HSSFCell cellF1 = row1.createCell(5);
		cellF1.setCellValue("EFECTIVO");
		cellF1.setCellStyle(cellStyle);
		
		HSSFCell cellG1 = row1.createCell(6);
		cellG1.setCellValue("CREDITO");
		cellG1.setCellStyle(cellStyle);
				
		HSSFCell cellH1 = row1.createCell(7);
		cellH1.setCellValue("DEBITO");
		cellH1.setCellStyle(cellStyle);
					
		HSSFCell cellI1 = row1.createCell(8);
		cellI1.setCellValue("TOTAL EFECTIVO");
		cellI1.setCellStyle(cellStyle);
				
		HSSFCell cellJ1 = row1.createCell(9);
		cellJ1.setCellValue("TOTAL EN BANCO");
		cellJ1.setCellStyle(cellStyle);
					
		HSSFCell cellK1 = row1.createCell(10);
		cellK1.setCellValue("DISPONIBLE");
		cellK1.setCellStyle(cellStyle);
		
		HSSFCell cellL1 = row1.createCell(11);
		cellL1.setCellValue("DEVENGADO");
		cellL1.setCellStyle(cellStyle);
		
		HSSFCell cellM1 = row1.createCell(12);
		cellM1.setCellValue("ES INGRESO");
		cellM1.setCellStyle(cellStyle);
	}
}

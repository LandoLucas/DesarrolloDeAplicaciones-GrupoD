package ar.edu.unq.desapp.grupoD.model.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.services.OperationService;

public class ExcelReader {

	private OperationService operationService;
	
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	/**
	* This method is used to read the data's from an excel file.
	* @param fileName - Name of the excel file.
	*/
	public void readExcelFile(String fileName)
	{
		/**
		* Create a new instance for cellDataList
		*/
		List cellDataList = new ArrayList();
		try
		{
			/**
			* Create a new instance for FileInputStream class
			*/
			FileInputStream fileInputStream = new FileInputStream(fileName);
			/**
			* Create a new instance for POIFSFileSystem class
			*/
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
			/*
			* Create a new instance for HSSFWorkBook Class
			*/
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheetAt(0);
			/**
			* Iterate the rows and parse cells of the spreadsheet
			* to get all the datas.
			*/
			Iterator<Row> rowIterator = hssfSheet.rowIterator();
			rowIterator.next();
			List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
			while (rowIterator.hasNext())
			{
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
				HSSFCell cellA = hssfRow.getCell(0);
				Date date = cellA.getDateCellValue();
				DateTime parsedDate = new DateTime(date);
				
				
				HSSFCell cellB = hssfRow.getCell(1);
				String category = cellB.getStringCellValue();
				
				HSSFCell cellC = hssfRow.getCell(2);
				String subcategory = cellC.getStringCellValue();
				
				HSSFCell cellD = hssfRow.getCell(3);
				String concept = cellD.getStringCellValue();
				
				HSSFCell cellE = hssfRow.getCell(4);
				String shift = cellE.getStringCellValue();
				
				HSSFCell cellF = hssfRow.getCell(5);
				double cash = cellF.getNumericCellValue();
				paymentTypes.add( new PettyCash(cash));
				
				HSSFCell cellG = hssfRow.getCell(6);
				double credit = cellG.getNumericCellValue();
				paymentTypes.add( new DebitCard(credit) );
				
				HSSFCell cellH = hssfRow.getCell(7);
				double debit = cellH.getNumericCellValue();
				paymentTypes.add( new CreditCard(debit) );
				
				//Valores calculados
				HSSFCell cellI = hssfRow.getCell(8);
				double cashTotal = cellI.getNumericCellValue();
				
				HSSFCell cellJ = hssfRow.getCell(9);
				double bankTotal = cellJ.getNumericCellValue();
				
				HSSFCell cellK = hssfRow.getCell(10);
				double avilable = cellK.getNumericCellValue();
				
				HSSFCell cellL = hssfRow.getCell(11);
				double yield = cellL.getNumericCellValue();
				
				//Should be loaded from the file
				boolean isIncome = true;
				
//				System.out.print(parsedDate + "\t");
//				System.out.print(category + "\t");
//				System.out.print(subcategory + "\t");
//				System.out.print(concept + "\t");
//				System.out.print(shift + "\t");
//				System.out.print(cash + "\t");
//				System.out.print(credit + "\t");
//				System.out.print(debit + "\t");
//				System.out.print(cashTotal + "\t");
//				System.out.print(bankTotal + "\t");
//				System.out.print(avilable + "\t");
//				System.out.print(yield + "\t");
//				System.out.println();
				
				operationService.saveOperation(parsedDate, paymentTypes, isIncome, shift, category, subcategory, concept);
				paymentTypes = new ArrayList<PaymentType>();
				
//				while (iterator.hasNext()){
//					HSSFCell hssfCell = (HSSFCell) iterator.next();
//					cellTempList.add(hssfCell);
//				}
//				cellDataList.add(cellTempList);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		/**
		* Call the printToConsole method to print the cell data in the
		* console.
		*/
//		printToConsole(cellDataList);
	}
	/**
	* This method is used to print the cell data to the console.
	* @param cellDataList - List of the data's in the spreadsheet.
	*/
//	private void printToConsole(List cellDataList)
//	{
//		for (int i = 0; i < cellDataList.size(); i++)
//		{
//			List cellTempList = (List) cellDataList.get(i);
//			for (int j = 0; j < cellTempList.size(); j++)
//			{
//				HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
//				String stringCellValue = hssfCell.toString();
//				System.out.print(stringCellValue + "\t");
//			}
//			System.out.println();
//		}
//	}
	
//	public static void main(String[] args)
//	{
////		String fileName = "C:" + File.separator + "Users" +
////		File.separator + "Giftsam" + File.separator + "Desktop" +
////		File.separator + "sampleexcel.xls";
//		String fileName = "C:/Users/JulianV/Desktop/excelOneLine.xls";
//		new ExcelReader().readExcelFile(fileName);
//	}
}

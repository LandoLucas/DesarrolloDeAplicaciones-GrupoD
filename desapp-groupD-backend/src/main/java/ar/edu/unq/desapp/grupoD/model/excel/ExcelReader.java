package ar.edu.unq.desapp.grupoD.model.excel;

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

	public void readExcelFile(String fileName)
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(fileName);
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheetAt(0);
			
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
				
				HSSFCell cellM = hssfRow.getCell(12);
				boolean isIncome = cellM.getBooleanCellValue();
				
				operationService.saveOperation(parsedDate, paymentTypes, isIncome, shift, category, subcategory, concept);
				paymentTypes = new ArrayList<PaymentType>();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

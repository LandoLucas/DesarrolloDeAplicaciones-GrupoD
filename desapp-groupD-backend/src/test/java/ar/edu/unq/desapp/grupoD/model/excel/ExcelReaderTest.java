package ar.edu.unq.desapp.grupoD.model.excel;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.services.OperationService;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ExcelReaderTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	ExcelReader reader;
	
	@Autowired
	OperationService operationService;
	
	@Test
	public void newExcelReaderTest(){
		String fileName = "src/test/resources/excelLineas.xls";
		
		List<Operation> operationsPrevias = operationService.findAll();
		assertEquals(0, operationsPrevias.size());
		
		reader.readExcelFile(fileName);
		
		List<Operation> operations = operationService.findAll();
		
		assertEquals(operationsPrevias.size()+3 , operations.size());
	}
}
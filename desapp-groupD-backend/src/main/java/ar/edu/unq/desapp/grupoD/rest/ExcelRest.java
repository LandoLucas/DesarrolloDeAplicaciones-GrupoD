package ar.edu.unq.desapp.grupoD.rest;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.services.ExcelService;
import ar.edu.unq.desapp.grupoD.services.OperationService;

@Service
@Path("/excel")
public class ExcelRest {

	private OperationService operationService;
	private ExcelService excelService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}

	@GET
	@Path("/descarga")
	@Produces("application/vnd.ms-excel")
	public Response downloadFile() {
		excelService.writeExcel();
		File file = new File("src/test/resources/exportedTransactions.xls");
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=RegistroTransacciones.xls");
		return response.build();
	}

	@POST
	@Path("/carga")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(List<Attachment> attachments,
			@Context HttpServletRequest request) {
		for (Attachment attr : attachments) {
			DataHandler handler = attr.getDataHandler();
			try {
				InputStream stream = handler.getInputStream();
				OutputStream out = new FileOutputStream(new File(
						"src/test/resources/data.xls"));

				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = stream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				stream.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		excelService.readExcel("src/test/resources/data.xls");
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}

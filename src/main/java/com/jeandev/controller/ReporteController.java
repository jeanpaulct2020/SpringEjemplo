package com.jeandev.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeandev.model.Empleado;
import com.jeandev.service.EmpleadoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
	
	@Autowired
	private EmpleadoService serviceEmpleado;
	
	@GetMapping()
	public String reportesView() {
		return "reportes";
	}
	
	@GetMapping("/reporte01")
	public void resporte01(HttpServletResponse response) throws IOException, JRException {
		
		//llamar al que crea el reporte metiendo el parametro de la lista de parametro
		crearPDF(serviceEmpleado.getAll());
		
		// lo convierto el pdf generado en byte
		byte[] data = Files.readAllBytes(Paths.get("src/main/resources/reportes/empleados.pdf"));
		
		//y le damos una respuesta usando los parametro del response
		streamReport(response, data , "empleados.pdf");
	}

	//Este metodo me permite hacer la respuesta de un metodo
	protected void streamReport(HttpServletResponse response, byte[] data, String name) throws IOException {
		response.setContentType("application/pdf");
		//en nuevo tab o en el mismo
		//response.setHeader("Content-disposition","inline; filename= " + name);
		//Director para descargar
		response.setHeader("Content-disposition","attachment; filename= " + name);
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
	}
	
	
	private void crearPDF(List<Empleado> empleados) throws JRException {
		
		//siempre del src hacia abajo
		JasperReport report = JasperCompileManager.compileReport("src/main/resources/reportes/Reporte01.jrxml");
		
		//datasource es la colleccion con la cual yo voy a enviar la informacion
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empleados);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("parametro1", "hola mundo");
		
		//construccion del reporte
		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
		
		//situar el reporte(el pdf) que se genere en el mismo directorio
		String path = "src/main/resources/reportes/";
		
		
		JasperExportManager.exportReportToPdfFile(print, path + "empleados.pdf");
	}
	
	
	
}

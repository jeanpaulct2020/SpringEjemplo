package com.jeandev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.jeandev.model.Empleado;
import com.jeandev.model.TipoEmpleado;
import com.jeandev.service.EmpleadoService;

@SpringBootTest
@ContextConfiguration //permite leer archivos de configuracion
public class EmpleadoServiceTest {

	
	private static final Logger log = LoggerFactory.getLogger(EmpleadoServiceTest.class);

	
	@Autowired
	private EmpleadoService serviceEmpleado;
	
	//me va apermitir cifrar la clave
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void insert() {
		Empleado empleado = new Empleado();
		
		empleado.setNombres("Javier Elias");
		empleado.setApellidos("Mendoza Huaman");
		empleado.setDocumento("12345678");
		
		TipoEmpleado tipoEmpleado = new TipoEmpleado();
		tipoEmpleado.setIdTipoEmpleado(3);
		
		empleado.setTipoEmpleado(tipoEmpleado);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "13/10/1995";
		/*ingresasmos la fecha con el formato que queremos y el formato que hemos elegido*/
		LocalDate localDate = LocalDate.parse(date, formatter);
		empleado.setFechaNacimiento(localDate);
		
		//empleado.setFechaNacimiento(LocalDate.now());
		empleado.setSueldo(8000);
		empleado.setNumeroHijos(3);
		
		empleado.setUsuario("admin");
		empleado.setClave("javi");
		
		int status = serviceEmpleado.insert(empleado);
		Assert.state(status==1, "Insert failed");

		//Assert.state(empleado.getIdEmpleado() == 4 , "Insert failed 2");
	}
	
	@Test
	public void update() {
		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(2);
		empleado.setNombres("Mirella");
		empleado.setApellidos("Arrunategui Rodriguez");
		empleado.setDocumento("72012365");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "13/10/1997";
		
		/*ingresasmos la fecha con el formato que queremos y el formato que hemos elegido*/
		LocalDate localDate = LocalDate.parse(date, formatter);
		empleado.setFechaNacimiento(localDate);
		
		//empleado.setFechaNacimiento(LocalDate.now());
		empleado.setSueldo(1500);
		empleado.setNumeroHijos(0);
		
		empleado.setUsuario("mire");
		empleado.setClave("mire");
		
		int status = serviceEmpleado.update(empleado);
		Assert.state(status==1, "update fallido");
		
	}
	
	
	@Test
	public void delete() {
		Integer idEmplado =4;	
		int status = serviceEmpleado.delete(idEmplado);				
		Assert.state(status==1, "delete fallido");
	}
	
	@Test
	public void list() {
		List<Empleado> lista = serviceEmpleado.getAll();
		Assert.notNull(lista, "list is null");
		Assert.notEmpty(lista, "List is empty");
	}
	
	
	@Test
	public void testLogin() {
		Empleado empleado = serviceEmpleado.login("mire", "123456");
		log.info(empleado.toString()); //no es necesario, pero cuando ni con las pruebas aun estas seguro xdxdxd
		Assert.notNull(empleado, "Esmpleado is null");
		
	}
	
	@Test
	public void insertWithSecurity() {
		Empleado empleado = new Empleado();
		
		empleado.setNombres("Jean Paul");
		empleado.setApellidos("Cochachin Torres");
		empleado.setDocumento("12345678");
		
		TipoEmpleado tipoEmpleado = new TipoEmpleado();
		tipoEmpleado.setIdTipoEmpleado(2);
		
		empleado.setTipoEmpleado(tipoEmpleado);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "13/10/1992";
		/*ingresasmos la fecha con el formato que queremos y el formato que hemos elegido*/
		LocalDate localDate = LocalDate.parse(date, formatter);
		empleado.setFechaNacimiento(localDate);
		
		//empleado.setFechaNacimiento(LocalDate.now());
		empleado.setSueldo(8500);
		empleado.setNumeroHijos(2);
		
		empleado.setUsuario("jean");
		empleado.setClave(encoder.encode("jean"));
		
		int status = serviceEmpleado.insert(empleado);
		Assert.state(status==1, "Insert failed");

		//Assert.state(empleado.getIdEmpleado() == 4 , "Insert failed 2");
	}
}

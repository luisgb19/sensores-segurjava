package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Alarma;
import model.Persona;
import model.Sensor;
import service.ServiceInformes;
import service.ServicePersonas;

@Controller
public class AdminController {

	@Autowired
	ServicePersonas sPersonas;
	@Autowired
	ServiceInformes sInformes;

	@RequestMapping(value="/toAdminInformeFullCliente",method = {RequestMethod.GET,RequestMethod.POST})
	public String recuperarFullClientes(HttpServletRequest request) {
		request.setAttribute("clientes",sPersonas.getClientes());
		return "admin-informeFullCliente";
	}
	
	@PostMapping(value = "/doAdminInformeFullCliente")
	public String informeFullCliente(@RequestParam("dni") String dni,
			HttpServletRequest request){
		List<Sensor> sensores = sInformes.getInformeFullActividad(dni);
		request.setAttribute("sensores", sensores);
		request.setAttribute("valorSeleccionado", dni);
		return "forward:/toAdminInformeFullCliente";
	}
	
	
	@PostMapping(value = "/doAdminInformeTemporal")
	public String informeTemporal(@RequestParam("fecIni") String fecIniJ,
			@RequestParam("fecFin") String fecFinJ,
			HttpServletRequest request){
		System.out.println("*********************/doAdminInformeTemporal***********************************");
		System.out.println("fecIniJ = "+fecIniJ);
		System.out.println("fecFinJ = "+fecFinJ);
		String fecIniO = fecIniJ.replaceAll("-", "");
		String fecFinO = fecFinJ.replaceAll("-", "");
		System.out.println("fecIniO = "+fecIniO);
		System.out.println("fecFinO = "+fecFinO);
		
			List<Object[]> informe = sInformes.getInformeFullTemporal(fecIniO, fecFinO);
			request.setAttribute("informe", informe);
			request.setAttribute("valorFecIni", fecIniJ);
			request.setAttribute("valorFecFin", fecFinJ);
			return "admin-informeTemporal";
	}

	@RequestMapping(value="/toAdminAltaCliente",method = {RequestMethod.GET,RequestMethod.POST})
	public String recuperarAltaClientes(HttpServletRequest request) {
		request.setAttribute("clientes",sPersonas.getUsuarios());
		return "admin-altaCliente";
	}
	
	@PostMapping(value = "/doAdminAltaCliente")
	public String altaCliente(@RequestParam("dni") String dni,
							@RequestParam("usuario") String usuario,
							@RequestParam("password") String password,
							@RequestParam("rol") String rol,
							@RequestParam("nombre") String nombre,
							@RequestParam("direccion") String direccion,
							@RequestParam("email") String email,
							@RequestParam("cuenta") String cuenta,
							@RequestParam("avisoPolicia") String avisoPolicia,
							@RequestParam("habilitado") String habilitado,
			HttpServletRequest request){
			sPersonas.actualizarUsuario(new Persona(dni, Boolean.valueOf(avisoPolicia), cuenta, direccion, email, Boolean.valueOf(habilitado), nombre, password, rol, usuario));
			return "forward:/toAdminAltaCliente";
	}
	
// Codifica la BBDD con sus respectivas pwd http://localhost:8080/proyecto/doEncode
	@RequestMapping(value="/doEncode",method = {RequestMethod.GET,RequestMethod.POST})
	public String codificarBBDD(HttpServletRequest request) {
		sPersonas.codificarBBDD();
		request.setAttribute("clientes",sPersonas.getUsuarios());
		return "admin-altaCliente";
	}

// Codifica la BBDD con su pwd generica o admin	url "/doPwd?pwd=xxxxx" http://localhost:8080/proyecto/doPwd?pwd=admin
	@RequestMapping(value="/doPwd",method = {RequestMethod.GET,RequestMethod.POST})
	public String codificarBBDDPwd(@RequestParam("pwd") String password,
									HttpServletRequest request) {
		sPersonas.codificarBBDDPwd(password);
		request.setAttribute("clientes",sPersonas.getUsuarios());
		return "admin-altaCliente";
	}
}

package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Alarma;
import service.ServiceInformes;
import service.ServicePersonas;

@Controller
public class InformesController {

	@Autowired
	ServicePersonas sPersonas;
	@Autowired
	ServiceInformes sInformes;
	@RequestMapping(value="/toInformeCliente",method = {RequestMethod.GET,RequestMethod.POST})
	public String recuperarClientes(HttpServletRequest request) {
//		List<Persona> clientes = sPersonas.getClientes();
//		request.setAttribute("clientes", clientes);
		request.setAttribute("clientes",sPersonas.getClientes());
		return "admin-informeCliente";
	}
	
	
	@PostMapping(value = "/doInformeCliente")
	public String login(@RequestParam("dni") String dni,
						HttpSession sesion){
		
		List<Alarma> alarmas = sInformes.getInformeActividad(dni);
		sesion.setAttribute("alarmas", alarmas);
		return "admin-menu";
	}
}
	

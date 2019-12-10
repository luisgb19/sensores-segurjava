package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import model.Persona;
import service.ServicePersonas;

@Controller
public class LoginController {

	@Autowired
	ServicePersonas sPersonas;
	@PostMapping(value = "/toLogin")
	public String login(@RequestParam("user") String user, 
						@RequestParam("pwd") String pwd,
						HttpSession sesion){
		Persona persona=sPersonas.getPersona(user, pwd);
		if(persona!=null) {
			sesion.setAttribute("persona", persona);
			if(persona.getRol().equalsIgnoreCase("ROLE_ADMIN")) {
				return "admin-menu";
			}else {
				return "cliente-menu";	
			}
		}else {
			return "error";
		}
	}
}
	

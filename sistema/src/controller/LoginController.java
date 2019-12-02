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
	@PostMapping(value = "/login")
	public String login(@RequestParam("user") String user, 
						@RequestParam("pwd") String pwd,
						HttpSession sesion){
		Persona persona=sPersonas.getPersona(user, pwd);
		if(persona!=null) {
			sesion.setAttribute("persona", persona);
			return "menu.jsp";
		}else {
			return "error.jsp";
		}
	}
}
	

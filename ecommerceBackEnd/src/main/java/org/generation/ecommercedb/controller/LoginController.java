
package org.generation.ecommercedb.controller;

import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/login/")
@CrossOrigin(origins="*")

public class LoginController {
	
	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}//constructor
	@PostMapping
	public String Login(@RequestBody User user) { //pasamos parametros 
		String resultado = "Nombre de usuario o contrasenia incorrectos"; //resultado si no es correcto
		if(userService.login(user.getUsername(), user.getPassword())){ //if para validar username y password
			resultado = "Ok, las contrasenias coinciden, bienvenid@"; //resultado si es correcto
		}//if
		return resultado;
	}//Login
	

}//class UserController

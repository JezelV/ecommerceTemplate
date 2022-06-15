package org.generation.ecommercedb.controller;

import java.util.List;

import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api/users/")
@CrossOrigin(origins="*")

public class UserController {
	
	// Constante
	public final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	} // Fin del constructor
	
	
	@GetMapping
	// Método para obtener todos los usuarios
	public List<User> getUsuarios(){
		return userService.getUsers();
	} // Fin del método GET
	
	// Método para obtener un usuario por su id
	public User getUser(@PathVariable("userId") Long userId) {
		return userService.getUser(userId);
	} // Fin del método GET
	
	// Método para borrar usuarios mediante su Id
	@DeleteMapping(path="{userId}")
	public void deleteUsuario(@PathVariable ("userId") Long userId) {
		userService.deleteUser(userId);
	} // Fin del método DELETE
	
	// Método para agregar nuevos usuarios mediante un JSON
	@PostMapping
	public void addUsuario(@RequestBody User user) {
		userService.addUser(user);
	} // Fin del método POST
	
	// Método para actualizar usuarios ya registrados
	@PutMapping(path= "userId")
	public void updateUsuario(@PathVariable("userId") Long userId, @RequestParam(required = false) String currentPassword, @RequestParam (required = false) String newPassword) {
		this.userService.updateUser(userId, currentPassword, newPassword);
	}
	
}

package org.generation.ecommercedb.service;
import java.util.List;
import java.util.Optional;
import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
3.- Creamos clase UserService
	3.1 Agregamos anotacion @Service
	3.2 Definimos nuestra constante UserRepository
	3.3 Creamos constructor usando los campos y le agregamos la anotacion @Autowired
	3.4 Creamos un metodo para validar el registro del usuario (si existe o no)
*/

@Service
public class UserService {
	
	//constante
	private final UserRepository userRepository;
	
	@Autowired
	//constructor
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}//constructor
	
	//metodo para el login (validamos si existe o no)
		public boolean login(String username, String password) { //parametros
			boolean resultado = false; //boolean para saber si existe o no
			Optional <User> user = userRepository.findByUsername(username);//buscamos el user y password en la BD (por username), nos regresa un optional
			if (user.isPresent()) { //si el usuario esta presente
				//System.out.println("Password SHA: " + SHAUtil.createHash(password));
				if (user.get().getPassword().equals(password)){ //comparamos el usuario con su contrasena
					resultado = true; //si user y password conciden, es true
				}//if password
			}//if isPresent
			return resultado; //retornamos el resultado
		}//login

		public List<User> getUsers() {
			return userRepository.findAll();
		} // Fin getUsers()

		public User getUser(Long userId) {
			return userRepository.findById(userId).orElseThrow(
					()-> new IllegalStateException("El usuario con el id "+ userId + " no existe")
					);
		} // Fin del método getUser()

		public void deleteUser(Long userId) {
				if(userRepository.findById(userId) != null) {
					userRepository.deleteById(userId);
				} else {
					throw new IllegalStateException("El usuario con el id "+ userId +" no existe");
				} // Fin else
			} // Fin método deleteUser()

		// Método addUser() crea un usuario nuevo en caso de que éste no exista, y devuelve un error en caso de que éste si exista
		public void addUser(User user) {
			Optional<User> userByName = userRepository.findByUsername(user.getUsername());
			
			// Verificar si el usuario existe o no
			if(userByName.isPresent()) {
				throw new IllegalStateException("El usuario con el nombre " + user.getUsername()+ " ya existe"); // Lanza error en caso de que exista
			} else {
				userRepository.save(user); // Guarda los datos en caso de que no exista
			}// Fin if-else
		}// Fin método addUsuario()		
		
		// El método updateUser() cambia la contraseña de un usuario ya existente, y arroja un error en caso de que éste no exista

		public void updateUser(Long userId, String currentPassword, String newPassword) { //pedimos id, contrasenia actual y nueva contrasenia
			if (userRepository.existsById(userId)) { //si el usuario existe...
				User user = userRepository.getById(userId); //traemos al usuario por id
				if ((newPassword !=null) && (currentPassword !=null)) { //si las dos contrasenias son diferentes de null...
					//si el usuario existe, y las contrasenias son distintas de nullo, entonces encriptamos
					if ( (SHAUtil.verifyHash(currentPassword, user.getPassword() ) ) &&  //contrasenia actual (viene BD)
						(! SHAUtil.verifyHash(newPassword, user.getPassword()) ) ) { //nueva contrasenia (input del formulario)
						user.setPassword(SHAUtil.createHash(newPassword));	
						userRepository.save(user); //guardo la nueva contrasenia
					} else {
						throw new IllegalStateException("Contraseña incorrecta");	
					}//else // if equals
				}else {
					throw new IllegalStateException("Contraseñas nulas");	
				}//else  // !=null
			}else {
				throw new IllegalStateException("Usuario no encontrado " + userId);	
			}//else //if existsById
		} // Fin método updateUser()
		
	}// Fin Clase UserService

//2. Interface

/*
 * 2.- Creamos interface UserRepository.java
	2.1 Extendemos desde JpaRepository
	2.2 Le pasamos como parametro T=User y ID=Long (cuidar importar User desde el model)
	2.3 Creamos el query para buscar Username a nuestros usuarios
 */

package org.generation.ecommercedb.service;
import java.util.Optional;
import org.generation.ecommercedb.model.User;//importamos la clase de nuestro model (user)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> { //Extendemos de JPA para poder hacer nuestra tabla.
	@Query("SELECT u FROM User u WHERE u.username=?1")
	Optional<User> findByUsername(String username); 
	

}//interface

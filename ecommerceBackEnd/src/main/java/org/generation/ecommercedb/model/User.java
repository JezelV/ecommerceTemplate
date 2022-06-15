package org.generation.ecommercedb.model;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 
 * Notas

1.- Creamos clase user.java
	1.1 Definimos los parametros del usuario
	1.2 Creamos constructor, constructor por defecto, getters/setters y toString
	1.3 Para poder relacionarlo con BD, creamos el @Entity
	1.4 Especificamos la tabla con @Table y la llamamos "usuarios"
	1.5 Creamos las anotaciones ID, GeneratedValue y Column}

 */


	@Entity
	@Table (name="usuarios")
	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id", unique = true, nullable = false)
		
		//Parametros del usuario
		private Long id;
		private String username;
		private String password;
		
		//1. constructor
		//1.1 constructor vacio
		//2. getters y setters
		//3. toString
		//constructor
				public User(Long id, String username, String password) {
					super();
					this.id = id;
					this.username = username;
					this.password = password;
				}//constructor

				//constructor vacio o por default
				public User() {
					super();
				}//constructor vacio
				
				
				public Long getId() {
					return id;
				}
				public void setId(Long id) {
					this.id = id;
				}
				public String getUsername() {
					return username;
				}
				public void setUsername(String username) {
					this.username = username;
				}
				public String getPassword() {
					return password;
				}
				public void setPassword(String password) {
					this.password = password;
				}

				
				//toString
				@Override
				public String toString() {
					return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
				}//toString
}
package com.tsti.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {


	@Id
	private Long dni;
	@NotNull
	@Size(min = 1,max = 100, message = "Debe completar el apellido")
	private String apellido;
	
	private String nombre;
	

	@Email(message = "El e-mail ingresado no es valido")
	private String email;
	
	@ManyToOne
	private Ciudad ciudad;
	
	
	
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", email=" + email
				+ ", ciudad=" + ciudad + "]";
	}
	
	
	
}

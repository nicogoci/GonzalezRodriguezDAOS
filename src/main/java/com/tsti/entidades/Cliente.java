package com.tsti.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Cliente extends Persona{
	@Id
	@NotNull(message = "El DNI no puede ser nulo")
	private Long dni;
	
	@NotNull
	@Size(min = 1,max = 100, message = "Debe completar el apellido")
	private String apellido;
	
	@NotNull
	@Size(min = 1,max = 100, message = "Debe completar el nombre")
	private String nombre;
	
	@Email(message = "El e-mail ingresado no es valido")
	private String email;
	
	@ManyToOne
	private Ciudad Domicilio;
	
	private LocalDateTime FechaDeNacimiento;
	
	private Long NroPasaporte;
	
	private LocalDateTime FechaVencimientoPasaporte;
	
	
	
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
	public Ciudad getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(Ciudad domicilio) {
		Domicilio = domicilio;
	}
	public LocalDateTime getFechaDeNacimiento() {
		return FechaDeNacimiento;
	}
	public void setFechaDeNacimiento(LocalDateTime fechaDeNacimiento) {
		FechaDeNacimiento = fechaDeNacimiento;
	}
	public Long getNroPasaporte() {
		return NroPasaporte;
	}
	public void setNroPasaporte(Long nroPasaporte) {
		NroPasaporte = nroPasaporte;
	}
	public LocalDateTime getFechaVencimientoPasaporte() {
		return FechaVencimientoPasaporte;
	}
	public void setFechaVencimientoPasaporte(LocalDateTime fechaVencimientoPasaporte) {
		FechaVencimientoPasaporte = fechaVencimientoPasaporte;
	}
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", email=" + email
				+ ", Domicilio=" + Domicilio + ", FechaDeNacimiento=" + FechaDeNacimiento + ", NroPasaporte="
				+ NroPasaporte + ", FechaVencimientoPasaporte=" + FechaVencimientoPasaporte + "]";
	}
	
	
	
	

	
	
}



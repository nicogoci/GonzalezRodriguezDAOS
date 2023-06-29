package com.tsti.presentacion;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.tsti.entidades.Cliente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar o eliminar un Cliente. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class ClienteForm {
	

    
	@Id
	@Size(min = 7000000,max = 1000000000, message = "El DNI no puede ser nulo")
	private Long dni;
	@NotNull(message = "El apellido no puede ser nulo")
	@Size(min=2, max=30)
	private String apellido;
	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min=2, max=30)
	private String nombre;
	@NotNull(message = "El domicilio no puede ser nulo")
	private Long idDomicilio;
	@NotNull(message = "El email no puede ser nulo")
	private String email;
	@NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDateTime FechaDeNacimiento;
	
	@NotNull
	private Long idCiudad;
	
	private Long NroPasaporte;
	
	private LocalDateTime FechaVencimientoPasaporte;
	
	public Long getDni() {
		return dni;
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
	
	public Long getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(Long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	public Cliente toPojo()
	{
		Cliente c = new Cliente();
		c.setDni(this.getDni());
		c.setApellido(this.getApellido());
		c.setNombre(this.getNombre());
		c.setEmail(this.getEmail());
		c.setFechaDeNacimiento(this.getFechaDeNacimiento());
		
		return c;
	}
	
}


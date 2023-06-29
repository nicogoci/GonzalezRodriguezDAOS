package com.tsti.presentacion;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.tsti.entidades.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar o eliminar un Cliente. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class ClienteForm {
	

    
	@Id
	@NotNull(message = "El DNI no puede ser nulo")
	@Min( message = "Debe ser mayor a 7.000.000", value = 7000000)
	private Long dni;
	@NotNull(message = "El apellido no puede ser nulo")
	@Size(min=2, max=30)
	private String apellido;
	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min=2, max=30)
	private String nombre;
	@NotNull(message = "El domicilio no puede ser nulo")
	private String domicilio;
	@NotNull(message = "El email no puede ser nulo")
	@Email(message = "Debe ser un email")
	private String email;
	@NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDateTime fechaDeNacimiento;
	@NotNull
	private Long idCiudad;
	
	private Long nroPasaporte;
	
	private LocalDateTime fechaVencimientoPasaporte;
	
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDateTime getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDateTime fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Long getNroPasaporte() {
		return nroPasaporte;
	}

	public void setNroPasaporte(Long nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}

	public LocalDateTime getFechaVencimientoPasaporte() {
		return fechaVencimientoPasaporte;
	}

	public void setFechaVencimientoPasaporte(LocalDateTime fechaVencimientoPasaporte) {
		this.fechaVencimientoPasaporte = fechaVencimientoPasaporte;
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
		c.setDomicilio(this.getDomicilio());
		c.setEmail(this.getEmail());
		c.setFechaDeNacimiento(this.getFechaDeNacimiento());
		c.setNroPasaporte(this.getNroPasaporte());
		c.setFechaVencimientoPasaporte(this.getFechaVencimientoPasaporte());
		
		return c;
	}
	
}


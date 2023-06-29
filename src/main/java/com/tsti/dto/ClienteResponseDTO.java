package com.tsti.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.tsti.entidades.Cliente;


/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {
	

	private Long dni;
	private String apellido;
	private String nombre;
	private String domicilio;
	private String email;
	private LocalDateTime fechaDeNacimiento;
	private Long nroPasaporte;
	private LocalDateTime fechaVencimientoPasaporte;

	
	
	
	
	
	
	public ClienteResponseDTO(Cliente pojo) {
		super();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		this.domicilio=pojo.getDomicilio();
		this.email=pojo.getEmail();
		this.fechaDeNacimiento=pojo.getFechaDeNacimiento();
		this.nroPasaporte=pojo.getNroPasaporte();
		this.fechaVencimientoPasaporte=pojo.getFechaVencimientoPasaporte();
	}
	
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
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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

	@Override
	public String toString() {
		return "ClienteResponseDTO [dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", domicilio="
				+ domicilio + ", email=" + email + ", fechaDeNacimiento=" + fechaDeNacimiento + ", nroPasaporte="
				+ nroPasaporte + ", fechaVencimientoPasaporte=" + fechaVencimientoPasaporte + "]";
	}


	
}

package com.tsti.dto;

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
	
	
	
	
	public ClienteResponseDTO(Cliente pojo) {
		super();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		
		
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
	
	
	
	@Override
	public String toString() {
		return dni+" - "+ nombre +" "+ apellido;
	}
	
}

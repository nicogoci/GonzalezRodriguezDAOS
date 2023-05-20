package com.tsti.presentacion;

import com.tsti.entidades.Persona;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar o eliminar una persona. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class PersonaForm {


	@NotNull(message = "el dni no puede ser nulo")
	@Min(7000000)
	private Long dni;
	@NotNull
	@Size(min=2, max=30)
	private String apellido;
	@NotNull
	@Size(min=2, max=30)
	private String nombre;
	@NotNull
	private Long idCiudad;
	
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
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public Persona toPojo()
	{
		Persona p = new Persona();
		p.setDni(this.getDni());
		p.setApellido(this.getApellido());
		p.setNombre(this.getNombre());
		p.setDni(this.getDni());
		return p;
	}
	
}

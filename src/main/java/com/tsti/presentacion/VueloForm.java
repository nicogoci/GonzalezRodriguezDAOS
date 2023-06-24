package com.tsti.presentacion;

import java.time.LocalDateTime;

import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Persona;
import com.tsti.entidades.Vuelo;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar o eliminar una persona. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class VueloForm {
	@Id
	@NotNull(message = "No puede ser un numero nulo")
	private Long nroVuelo;
	
	@NotNull(message = "La fecha no puede ser nula")
	private LocalDateTime  timeVuelo;
	
	@NotNull
	@Min( message = "Debe ser mayor a 0", value = 1)
	private int nroFilas;
	
	@NotNull
	@Min( message = "Debe ser mayor a 0", value = 1)
	private int nroAsientos;
	
	@NotNull(message = "Debe contener tipo de vuelo")
	private String tipoVuelo;
	
	@NotNull(message = "Debe contener ciudad de destino")
	private Long idCiudadDestino;
	
	@NotNull(message = "Debe contener ciudad de origen")
	private Long idCiudadOrigen;
	
	private String estadoVuelo;
	
	
	
	public Long getNroVuelo() {
		return nroVuelo;
	}
	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}
	public LocalDateTime getTimeVuelo() {
		return timeVuelo;
	}
	public void setTimeVuelo(LocalDateTime timeVuelo) {
		this.timeVuelo = timeVuelo;
	}
	public int getNroFilas() {
		return nroFilas;
	}
	public void setNroFilas(int nroFilas) {
		this.nroFilas = nroFilas;
	}
	public int getNroAsientos() {
		return nroAsientos;
	}
	public void setNroAsientos(int nroAsientos) {
		this.nroAsientos = nroAsientos;
	}
	public String getTipoVuelo() {
		return tipoVuelo;
	}
	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}
	public Long getIdCiudadDestino() {
		return idCiudadDestino;
	}
	public void setIdCiudadDestino(Long idCiudadDestino) {
		this.idCiudadDestino = idCiudadDestino;
	}
	public Long getIdCiudadOrigen() {
		return idCiudadOrigen;
	}
	public void setIdCiudadOrigen(Long idCiudadOrigen) {
		this.idCiudadOrigen = idCiudadOrigen;
	}
	public String getEstadoVuelo() {
		return estadoVuelo;
	}
	public void setEstadoVuelo(String estadoVuelo) {
		this.estadoVuelo = estadoVuelo;
	}
	
	
	public Vuelo toPojo()
	{
		Vuelo v = new Vuelo();
		v.setNroVuelo(this.getNroVuelo());
		v.setTimeVuelo(this.getTimeVuelo());
		v.setNroFilas(this.getNroFilas());
		v.setNroAsientos(this.getNroAsientos());
		v.setTipoVuelo(this.getTipoVuelo());
		return v;
	}
	
}

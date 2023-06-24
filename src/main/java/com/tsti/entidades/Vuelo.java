package com.tsti.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vuelo {
	@Id
	@NotNull(message = "No puede ser un numero nulo")
	private Long nroVuelo;
	
	@NotNull(message = "La fecha no puede ser nula")
	private LocalDateTime timeVuelo;
	
	@NotNull
	@Min( message = "Debe ser mayor a 0", value = 1)
	private int nroFilas;
	
	@NotNull
	@Min( message = "Debe ser mayor a 0", value = 1)
	private int nroAsientos;
	
	private String tipoVuelo;
	
	@ManyToOne
	private Ciudad ciudadDestino;
	
	@ManyToOne
	private Ciudad ciudadOrigen;
	
	private String estadoVuelo;
	
	
	//Constructores
	public Vuelo() {
		super();
	}
	
	public Vuelo(@NotNull(message = "No puede ser un numero nulo") Long nroVuelo,
			@NotNull(message = "La fecha no puede ser nula") LocalDateTime timeVuelo,
			@NotNull @Min(message = "Debe ser mayor a 0", value = 1) int nroFilas,
			@NotNull @Min(message = "Debe ser mayor a 0", value = 1) int nroAsientos, String tipoVuelo,
			Ciudad ciudadDestino, Ciudad ciudadOrigen, String estadoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.timeVuelo = timeVuelo;
		this.nroFilas = nroFilas;
		this.nroAsientos = nroAsientos;
		this.tipoVuelo = tipoVuelo;
		this.ciudadDestino = ciudadDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.estadoVuelo = estadoVuelo;
	}

	
	
	//Getter & Setter
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
		
	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getEstadoVuelo() {
		return estadoVuelo;
	}
	
	public void setEstadoVuelo(String estadoVuelo) {
		this.estadoVuelo = estadoVuelo;
	}

	
	
	//To String
	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", time=" + timeVuelo + ", nroFilas=" + nroFilas + ", nroAsientos="
				+ nroAsientos + ", tipoVuelo=" + tipoVuelo + ", CiudadDestino=" + ciudadDestino + ", CiudadOrigen="
				+ ciudadOrigen + ", estadoVuelo=" + estadoVuelo + "]";
	}
}

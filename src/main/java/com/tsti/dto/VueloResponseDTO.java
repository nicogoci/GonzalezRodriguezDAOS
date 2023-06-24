package com.tsti.dto;


import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.tsti.entidades.Vuelo;


/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class VueloResponseDTO extends RepresentationModel<VueloResponseDTO> {

	private Long nroVuelo;
	private LocalDateTime fechaVuelo;
	private int nroFilas;
	private int nroAsientos;
	private String tipoVuelo;
	private String estadoVuelo;
	
	
	
	
	public VueloResponseDTO(Vuelo pojo) {
		super();
		this.nroVuelo = pojo.getNroVuelo();
		this.fechaVuelo = pojo.getTimeVuelo();
		this.nroFilas = pojo.getNroFilas();
		this.nroAsientos = pojo.getNroAsientos();
		this.tipoVuelo = pojo.getTipoVuelo();
		this.estadoVuelo = pojo.getEstadoVuelo();
	}



	//Getter & Setter
	public Long getNroVuelo() {
		return nroVuelo;
	}
	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}
	public LocalDateTime getFechaVuelo() {
		return fechaVuelo;
	}
	public void setFechaVuelo(LocalDateTime fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
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
	public String getEstadoVuelo() {
		return estadoVuelo;
	}
	public void setEstadoVuelo(String estadoVuelo) {
		this.estadoVuelo = estadoVuelo;
	}


	//ToString
	@Override
	public String toString() {
		return "VueloResponseDTO [nroVuelo=" + nroVuelo + ", fechaVuelo=" + fechaVuelo + ", nroFilas=" + nroFilas
				+ ", nroAsientos=" + nroAsientos + ", tipoVuelo=" + tipoVuelo + ", estadoVuelo=" + estadoVuelo + "]";
	}
}

package com.tsti.entidades;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente extends Persona{
	
	private String domicilio;
	
	private LocalDateTime FechaDeNacimiento;
	
	private Long NroPasaporte;
	
	private LocalDateTime FechaVencimientoPasaporte;
	


	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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
		return "Cliente [Domicilio=" + domicilio + ", FechaDeNacimiento=" + FechaDeNacimiento + ", NroPasaporte="
				+ NroPasaporte + ", FechaVencimientoPasaporte=" + FechaVencimientoPasaporte + "]";
	}
	

	
}



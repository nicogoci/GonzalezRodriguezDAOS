package com.tsti.presentacion.error;

import java.util.List;
import java.util.Map;

public class MensajeError {
	private String codigo;
	private List<Map<String , String>> mensajes;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Map<String, String>> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Map<String, String>> mensajes) {
		this.mensajes = mensajes;
	}
	
}

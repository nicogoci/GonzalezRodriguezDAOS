package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;


public interface VueloService {
	
	/**
	 * Devuelve la lista completa de vuelos
	 * @return Lista de vuelos
	 */
	public List<Vuelo> getAll();

	/**
	 * Obtiene un Vuelo a partir de su identidicador
	 * @param idVuelo
	 * @return
	 */
	public Optional<Vuelo> getById(Long idVuelo);

	/**
	 * Actualiza datos de un Vuelo
	 * @param v
	 */
	public void update(Vuelo v);

	/**
	 * Inserta un nuevo vuelo
	 * @param v
	 * @throws Exception
	 */
	public void insert(Vuelo v) throws Exception;

	/**
	 * Elimina un vuelo del sistema
	 * @param id del Vuelo a eliminar
	 */
	public void delete(Long idVuelo);

	public List<Vuelo> filtrar(Long idCiudadDestino, Long idCiudadOrigen);

}

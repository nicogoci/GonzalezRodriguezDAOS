package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Persona;


public interface PersonaService {
	
	/**
	 * Devuelve la lista completa de personas
	 * @return Lista de personas
	 */
	public List<Persona> getAll();

	/**
	 * Obtiene una persona a partir de su identidicador
	 * @param id
	 * @return
	 */
	public Optional<Persona> getById(Long id);

	/**
	 * Actualiza datos de una persona
	 * @param p
	 */
	public void update(Persona p);

	/**
	 * Inserta una nueva persona
	 * @param p
	 * @throws Exception
	 */
	public void insert(Persona p) throws Exception;

	/**
	 * Elimina una persona del sistema
	 * @param id dni de la persona a eliminar
	 */
	public void delete(Long id);

	public List<Persona> filtrar(String apellido, String nombre);

}

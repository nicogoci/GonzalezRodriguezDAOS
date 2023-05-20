package com.tsti.accesoADatos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsti.entidades.Persona;

public interface PersonaDao extends JpaRepository<Persona, Long>{
	
	@Query("SELECT p FROM Persona p WHERE p.nombre like '%?1%'")
	Collection<Persona> findPersonasLike(String parte);
	
	public List<Persona> findByApellidoOrNombre(String apellido, String nombre);
}

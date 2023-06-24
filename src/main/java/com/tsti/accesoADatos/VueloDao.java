package com.tsti.accesoADatos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;

public interface VueloDao extends JpaRepository<Vuelo, Long>{
	

	public List<Vuelo> findByCiudadDestinoIdOrCiudadOrigenId(Long idCiudadDestino, Long idCiudadOrigen);
}

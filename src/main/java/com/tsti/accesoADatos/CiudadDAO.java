package com.tsti.accesoADatos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.entidades.Ciudad;

public interface CiudadDAO extends JpaRepository<Ciudad, Long>{

}

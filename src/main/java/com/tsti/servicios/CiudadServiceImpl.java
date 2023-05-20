package com.tsti.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.accesoADatos.CiudadDAO;
import com.tsti.entidades.Ciudad;
@Service
public class CiudadServiceImpl implements CiudadService{

	@Autowired
	private CiudadDAO dao;
	
	@Override
	public java.util.List<Ciudad> findAll()
	{
		return dao.findAll();
	}

	@Override
	public Optional<Ciudad> getById(Long id) {
		return  dao.findById(id);
		
	}
}

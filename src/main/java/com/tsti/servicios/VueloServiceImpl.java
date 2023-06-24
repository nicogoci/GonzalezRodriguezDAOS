package com.tsti.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.accesoADatos.VueloDao;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.exception.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Service
public class VueloServiceImpl implements VueloService {
	@Autowired
	private  Validator validator;
	
	@Autowired
	private VueloDao dao;
	@Override
	public List<Vuelo> getAll() {
		return dao.findAll();
	}
	@Override
	public Optional<Vuelo> getById(Long idVuelo) {
		return  dao.findById(idVuelo);
		
	}
	@Override
	public void update(Vuelo v) {
		dao.save(v);
	}
	@Override
	public void insert(Vuelo v) throws Exception {
		Set<ConstraintViolation<Vuelo>> cv = validator.validate(v);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Vuelo> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else
			dao.save(v);
	}
	@Override
	public void delete(Long idVuelo) {
		dao.deleteById(idVuelo);
	}
	@Override
	public List<Vuelo> filtrar(Long idCiudadDestino, Long idCiudadOrigen) {
		if(idCiudadDestino==null && idCiudadOrigen==null)
			return dao.findAll();
		else
			return dao.findByCiudadDestinoIdOrCiudadOrigenId(idCiudadDestino, idCiudadOrigen);
	}

}

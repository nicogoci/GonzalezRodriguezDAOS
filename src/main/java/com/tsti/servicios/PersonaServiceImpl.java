package com.tsti.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.accesoADatos.PersonaDao;
import com.tsti.entidades.Persona;
import com.tsti.exception.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private  Validator validator;
	
	@Autowired
	private PersonaDao dao;
	@Override
	public List<Persona> getAll() {
		return dao.findAll();
	}
	@Override
	public Optional<Persona> getById(Long id) {
		return  dao.findById(id);
		
	}
	@Override
	public void update(Persona p) {
		dao.save(p);
	}
	@Override
	public void insert(Persona p) throws Exception {
		Set<ConstraintViolation<Persona>> cv = validator.validate(p);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Persona> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else
			dao.save(p);
	}
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	@Override
	public List<Persona> filtrar(String apellido, String nombre) {
		if(apellido==null && nombre==null)
			return dao.findAll();
		else
			return dao.findByApellidoOrNombre(apellido, nombre);
	}

}

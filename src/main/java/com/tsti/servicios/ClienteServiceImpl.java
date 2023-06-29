package com.tsti.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.accesoADatos.ClienteDao;
import com.tsti.entidades.Cliente;
import com.tsti.exception.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private  Validator validator;
	
	@Autowired
	private ClienteDao dao;
	
	@Override
	public List<Cliente> getAll() {
		return dao.findAll();
	}
	@Override
	public Optional<Cliente> getById(Long id) {
		return  dao.findById(id);
		
	}
	@Override
	public void update(Cliente c) {
		dao.save(c);
	}
	@Override
	public void insert(Cliente c) throws Exception {
		Set<ConstraintViolation<Cliente>> cv = validator.validate(c);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Cliente> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else
			dao.save(c);
	}
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	@Override
	public List<Cliente> filtrar(String apellido, String nombre) {
		if(apellido==null && nombre==null)
			return dao.findAll();
		else
			return dao.findByApellidoOrNombre(apellido, nombre);
	}

}

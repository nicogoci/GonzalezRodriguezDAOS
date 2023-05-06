package com.tsti.presentacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.entidades.Persona;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Persona>> getAll() throws Exception
	{
		
		Persona p1= new Persona(27837170L,"juan","perez");
		Persona p2= new Persona(27837171L,"fede","martinez");
		Persona p3= new Persona(27837172L,"jose","ramirez");
		List<Persona> rta=new ArrayList<Persona>();
		rta.add(p1);
		rta.add(p2);
		rta.add(p3);
		
		return new ResponseEntity<List<Persona>>(rta,HttpStatus.OK);
	}
	
	
}

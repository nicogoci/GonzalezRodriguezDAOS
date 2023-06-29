package com.tsti.presentacion;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsti.dto.ClienteResponseDTO;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Cliente;
import com.tsti.exception.Excepcion;
import com.tsti.presentacion.error.MensajeError;
import com.tsti.servicios.CiudadService;
import com.tsti.servicios.ClienteService;

import jakarta.validation.Valid;
/**
 * Recurso Cliente
 * @author AleAristides
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	@Autowired
	private ClienteService service; 
	@Autowired
	private CiudadService ciudadService;
	
	
	/**
	 * Permite filtrar clientes. 
	 * Ej1 curl --location --request GET 'http://localhost:8081/clientes?apellido=Perez&&nombre=Juan' Lista los clientes llamados Perez, Juan
	 * Ej2 curl --location --request GET 'http://localhost:8081/clientes?apellido=Perez' Lista aquellos clientes de apellido Perez
	 * Ej3 curl --location --request GET 'http://localhost:8081/clientes'   Lista todas los clientes
	 * @param apellido
	 * @param nombre
	 * @return
	 * @throws Excepcion 
	 */
	@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE})
	public List<ClienteResponseDTO> filtrarClientes(@RequestParam(name = "apellido",required = false) String apellido 
			, @RequestParam(name = "nombre",required = false)  @jakarta.validation.constraints.Size(min = 1, max = 20) String nombre) throws Excepcion {
		
		List<Cliente> clientes = service.filtrar(apellido,nombre);
		List<ClienteResponseDTO> dtos=new ArrayList<ClienteResponseDTO>();
		for (Cliente pojo : clientes) {
			
	        dtos.add(buildResponse(pojo));
		}
		return dtos;

	}
	
	


	/**
	 * Busca un cliente a partir de su dni
	 * 	curl --location --request GET 'http://localhost:8081/cliente/27837171'
	 * @param id DNI de el cliente buscado
	 * @return Cliente encontrado o Not found en otro caso
	 * @throws Excepcion 
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) throws Excepcion
	{
		Optional<Cliente> rta = service.getById(id);
		if(rta.isPresent())
		{
			Cliente pojo=rta.get();
			return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	
	/**
	 * Inserta un nuevo cliente en la base de datos
	 * 			curl --location --request POST 'http://localhost:8081/cliente' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *			    "dni": 27837171,
	 *			    "apellido": "perez",
	 *			    "nombre": "juan",
	 *			    "idCiudad": 2
	 *			}'
	 * @param c Cliente  a insertar
	 * @return Cliente insertado o error en otro caso
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Object> guardar( @Valid @RequestBody ClienteForm form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
		}
		
		
//		if(form.getDni()==null)
//			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe indicar un dni");
//		else if(service.getById(form.getDni()).isPresent())
//			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un cliente con ese dni");
//		else
//		{
			Cliente cl = form.toPojo();
			Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
			if(c.isPresent())
				cl.setCiudad(c.get());
			else
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
//				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");
			}
			
			
			
				service.insert(cl);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
						.buildAndExpand(cl.getDni()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

				return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
		
//		}

	}
	
	/**
	 * Modifica un cliente existente en la base de datos:
	 * 			curl --location --request PUT 'http://localhost:8081/cliente/27837176' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *			    "apellido": "Perez",
	 *			    "nombre": "Juan Martin"
	 *			    "idCiudad": 1
	 *			}'
	 * @param cl Cliente a modificar
	 * @return Cliente Editado o error en otro caso
	 * @throws Excepcion 
	 */
	@PutMapping("/{dni}")
	public ResponseEntity<Object>  actualizar(@RequestBody ClienteForm form, @PathVariable long dni) throws Excepcion
	{
		Optional<Cliente> rta = service.getById(dni);
		if(!rta.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el cliente que desea modificar.");
			
		else
		{
			Cliente cl = form.toPojo();
			Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
			if(c.isPresent())
				cl.setCiudad(c.get());
			else
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");
			
			cl.setDni(dni);  //El dni es el identificador, con lo cual es el único dato que no permito modificar
			service.update(cl);
			return ResponseEntity.ok(buildResponse(cl));
		}
		

	}
	/**
	 * Borra el Cliente con el dni indicado
	 * 	  curl --location --request DELETE 'http://localhost:8081/personas/27837176'
	 * @param dni Dni del Cliente a borrar
	 * @return ok en caso de borrar exitosamente al Cliente, error en otro caso
	 */
	@DeleteMapping("/{dni}")
	public ResponseEntity<String> eliminar(@PathVariable Long dni)
	{
		if(!service.getById(dni).isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe un Cliente con ese dni");
		service.delete(dni);
		
		return ResponseEntity.ok().build();
		
		
	}
	
	
	
	
	/**
	 * Métdo auxiliar que toma los datos del pojo y construye el objeto a devolver en la response, con su hipervinculos
	 * @param pojo
	 * @return
	 * @throws Excepcion 
	 */
	private ClienteResponseDTO buildResponse(Cliente pojo) throws Excepcion {
		try {
			ClienteResponseDTO dto = new ClienteResponseDTO(pojo);
			 //Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(ClienteRestController.class)
										.slash(pojo.getDni())                
										.withSelfRel();
			//Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada al Cliente
			Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
			       													.getById(pojo.getCiudad().getId()))
			       													.withRel("ciudad");
			dto.add(selfLink);
			dto.add(ciudadLink);
			return dto;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}
	
	
	private String formatearError(BindingResult result) throws JsonProcessingException
	{
//		primero transformamos la lista de errores devuelta por Java Bean Validation
		List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
															Map<String, String> error= new HashMap<>();
															error.put(err.getField(),err.getDefaultMessage() );
															return error;
														}).collect(Collectors.toList());
		MensajeError e1=new MensajeError();
		e1.setCodigo("01");
		e1.setMensajes(errores);
		
		//ahora usamos la librería Jackson para pasar el objeto a json
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
	}

	private String getError(String code, String err, String descr) throws JsonProcessingException
	{
		MensajeError e1=new MensajeError();
		e1.setCodigo(code);
		ArrayList<Map<String,String>> errores=new ArrayList<>();
		Map<String, String> error=new HashMap<String, String>();
		error.put(err, descr);
		errores.add(error);
		e1.setMensajes(errores);
		
		//ahora usamos la librería Jackson para pasar el objeto a json
				ObjectMapper maper = new ObjectMapper();
				String json = maper.writeValueAsString(err);
				return json;
	}
	
	

}


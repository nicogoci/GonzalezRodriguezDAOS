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
import com.tsti.dto.VueloResponseDTO;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.exception.Excepcion;
import com.tsti.presentacion.error.MensajeError;
import com.tsti.servicios.CiudadService;
import com.tsti.servicios.VueloService;

import jakarta.validation.Valid;
/**
 * Recurso Vuelos
 * @author nico9
 *
 */
@RestController
@RequestMapping("/vuelos")
public class VueloRestController {
	
	@Autowired
	private VueloService service; 
	@Autowired
	private CiudadService ciudadService;
	
	
	
	/**
	 * Permite filtrar vuelos. 
	 * Ej1 curl --location --request GET 'http://localhost:8081/vuelos?ciudadDestino=2&&ciudadOrigen=1' Lista los vuelos del destino con ID 2 y origen de ID 1.
	 * Ej2 curl --location --request GET 'http://localhost:8081/vuelos?ciudadOrigen=1' Lista aquellos vuelos que salgan del origen de ID 1
	 * Ej3 curl --location --request GET 'http://localhost:8081/vuelos'   Lista todos los vuelos
	 * @param ciudadDestino
	 * @param ciudadOrigen
	 * @return
	 * @throws Excepcion 
	 */
	@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE})
	public List<VueloResponseDTO> filtrarVuelos(@RequestParam(name = "idCiudadDestino",required = false) Long idCiudadDestino 
			, @RequestParam(name = "idCiudadOrigen",required = false)  @jakarta.validation.constraints.Min(1) Long idCiudadOrigen) throws Excepcion {
		
		List<Vuelo> vuelos = service.filtrar(idCiudadDestino,idCiudadOrigen);
		List<VueloResponseDTO> dtos=new ArrayList<VueloResponseDTO>();
		for (Vuelo pojo : vuelos) {
			
	        dtos.add(buildResponse(pojo));
		}
		return dtos;

	}
	


	/**
	 * Busca un vuelo a partir de su nroVuelo
	 * 	curl --location --request GET 'http://localhost:8081/vuelos/7778'
	 * @param nroVuelo Numero de Vuelo
	 * @return Vuelo encontrado o Not found en otro caso
	 * @throws Excepcion 
	 */
	@GetMapping(value = "/{nroVuelo}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<VueloResponseDTO> getById(@PathVariable Long nroVuelo) throws Excepcion
	{
		Optional<Vuelo> rta = service.getById(nroVuelo);
		if(rta.isPresent())
		{
			Vuelo pojo=rta.get();
			return new ResponseEntity<VueloResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	

	
	/**
	 * Inserta un nuevo Vuelo en la base de datos
	  			curl --location --request POST 'http://localhost:8081/vuelos' 
	 			--header 'Accept: application/json' 
	  			--header 'Content-Type: application/json' 
	 			--data-raw '{
	 			    "nroVuelo": 1212,
	 			    "timeVuelo": "1900-01-01T15:00",
	 			    "nroFilas": 10,
	 				"nroAsientos": 2,
	 				"tipoVuelo": "Local",
	 				"idCiudadDestino": 2,
	 			    "idCiudadOrigen": 1
	 			}'
	 * @param v Vuelo  a insertar
	 * @return Vuelo insertado o error en otro caso
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Object> guardar( @Valid @RequestBody VueloForm form, BindingResult result) throws Exception
	{
		if(result.hasErrors())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
		}
			Optional<Vuelo> rta = service.getById(form.getNroVuelo());
			if(rta.isPresent())
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getError("01", "Ya existe un vuelo con dicho numero.","Se encontro en la BD un vuelo con mismo Nro."));
			}
			else
			{
				Vuelo v = form.toPojo();
				Optional<Ciudad> cDestino = ciudadService.getById(form.getIdCiudadDestino());
				if(cDestino.isPresent())
				{
					v.setCiudadDestino(cDestino.get());
				}
				else
				{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Destino Requerida", "La ciudad indicada no se encuentra en la base de datos."));
				}
				Optional<Ciudad> cOrigen = ciudadService.getById(form.getIdCiudadOrigen());
				if(cOrigen.isPresent())
				{
					v.setCiudadOrigen(cOrigen.get());
					v.setEstadoVuelo("registrado");
					service.insert(v);
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nroVuelo}")
							.buildAndExpand(v.getNroVuelo()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado
					return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
				}
				else			
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Origen Requerida", "La ciudad indicada no se encuentra en la base de datos."));
			}
	}
	
	
	
	/**
	 * Modifica un vuelo existente en la base de datos:
	  			curl --location --request PUT 'http://localhost:8081/vuelos/1212' 
	 			--header 'Accept: application/json' 
	 			--header 'Content-Type: application/json' 
	 			--data-raw '{
	 			    "timeVuelo": "1950-01-01T18:00"
	 			}'
	 * @param v Vuelo a modificar
	 * @return Vuelo Editado o error en otro caso
	 * @throws Excepcion 
	 */
	@PutMapping("/{nroVuelo}")
	public ResponseEntity<Object>  actualizar(@RequestBody VueloForm form, @PathVariable Long nroVuelo) throws Excepcion
	{
		Optional<Vuelo> rta = service.getById(nroVuelo);
		if(!rta.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el vuelo que desea modificar.");
		else
		{
			Vuelo v = rta.get();
			v.setTimeVuelo(form.getTimeVuelo()); 
			v.setEstadoVuelo("reprogramado"); 
			System.out.print("Notificar a los usuarios que pertenecen a dicho vuelo");
			service.update(v);
			return ResponseEntity.ok(buildResponse(v));
		}
	}
	
	
	
	/**
	 * Cancelar el vuelo con el nroVuelo indicado
	 * 	  curl --location --request DELETE 'http://localhost:8081/1212'
	 * @param nroVuelo Nro de vuelo a cancelar
	 * @return ok en caso de cancelar exitosamente el vuelo, error en otro caso
	 */
	@DeleteMapping("/{nroVuelo}")
	public ResponseEntity<String> cancelar(@RequestBody VueloForm form, @PathVariable Long nroVuelo) throws Excepcion
	{
		Optional<Vuelo> rta = service.getById(nroVuelo);
		if(!rta.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el vuelo que desea modificar.");
		else
		{
			Vuelo v = form.toPojo();
			v.setEstadoVuelo("cancelado"); 
			System.out.print("Notificar a los usuarios que pertenecen a dicho vuelo");
			service.update(v);
			return ResponseEntity.ok().build();
		}
	}
	
	
	
	
	/**
	 * Métdo auxiliar que toma los datos del pojo y construye el objeto a devolver en la response, con su hipervinculos
	 * @param pojo
	 * @return
	 * @throws Excepcion 
	 */
	private VueloResponseDTO buildResponse(Vuelo pojo) throws Excepcion {
		try {
			VueloResponseDTO dto = new VueloResponseDTO(pojo);
			 //Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(VueloRestController.class)
										.slash(pojo.getNroVuelo())                
										.withSelfRel();
			//Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada al vuelo
			Link ciudadDestinoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
			       													.getById(pojo.getCiudadDestino().getId()))
			       													.withRel("Ciudad Destino");
			Link ciudadOrigenLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
						.getById(pojo.getCiudadOrigen().getId()))
						.withRel("Ciudad Origen");
			dto.add(selfLink);
			dto.add(ciudadDestinoLink);
			dto.add(ciudadOrigenLink);
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

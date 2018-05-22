package cl.citiaps.spring.backend.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.entities.FilmActor;
import cl.citiaps.spring.backend.entities.FilmActorPK;
import cl.citiaps.spring.backend.entities.Stadistic;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;
import cl.citiaps.spring.backend.repository.StadisticRepository;

@CrossOrigin
@RestController  
@RequestMapping("/stadistics")
public class StadisticService {
	
	@Autowired
	private StadisticRepository stadisticRepository;
	

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Stadistic> getAllStadistic() {
		return stadisticRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Stadistic findOne(@PathVariable("id") Integer id) {
		return stadisticRepository.findOne(id);
	}
	

	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Stadistic> create(@RequestBody Stadistic resource) {
		if (resource.getName_stadistic().isEmpty() || resource.getApproval_stadistic() < 0 || resource.getApproval_stadistic() >= 100){
			return new ResponseEntity<Stadistic>(HttpStatus.BAD_REQUEST);
		}else{
			
			return new ResponseEntity<Stadistic>(stadisticRepository.save(resource), HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "setApproval/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Stadistic updateApproval(@PathVariable("id") Integer id, @RequestBody Stadistic resource) {
        Stadistic stadistic = stadisticRepository.findOne(id);
        stadistic.setApproval_stadistic(resource.getApproval_stadistic());
        stadisticRepository.save(stadistic);
        return stadistic; 
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) { 
		stadisticRepository.delete(id);
	}
	
}
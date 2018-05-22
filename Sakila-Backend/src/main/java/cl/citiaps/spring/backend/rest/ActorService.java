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
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@CrossOrigin
@RestController  
@RequestMapping("/actors")
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmActorRepository filmActorRepository;
	
	@Autowired
	private FilmRepository filmRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Actor findOne(@PathVariable("id") Integer id) {
		return actorRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
	@ResponseBody
	public  Collection<Film> findFilm(@PathVariable("id") Integer id) {
		Actor actor = actorRepository.findOne(id);
        Iterator<FilmActor> iter = actor.getFilmActor().iterator();
        FilmActor filmActor;
        Film film;
        Collection<Film> films = new ArrayList<Film>();
        while(iter.hasNext()) {
        	filmActor = (FilmActor)iter.next();
        	film = filmActor.getFilm();
        	films.add(film);
        }
        
        return films;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Actor> create(@RequestBody Actor resource) {
		if (resource.getFirstName().isEmpty() || resource.getLastName().isEmpty()){
			return new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
		}else{
			
			return new ResponseEntity<Actor>(actorRepository.save(resource), HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/{actor_id}/films/{film_id}",method = RequestMethod.POST) 
	@ResponseBody 
	public ResponseEntity<FilmActor> vincula(@PathVariable("actor_id") Integer actor_id, @PathVariable("film_id") Integer film_id){ 
		
		if( filmRepository.exists(film_id)) {
			if(actorRepository.exists(actor_id)) {
				
				Film film = filmRepository.findOne(film_id); 
				Actor actor = actorRepository.findOne(actor_id);
				
				//no entra por resource, lo creo aca
				FilmActor filmActor = new FilmActor();
			    
				//seteo los actores y los cositos
				filmActor.setFilm(film); 
			    filmActor.setActor(actor); 
			    
			    //creo la llave de dicha tabla (a nivel logico)
			    FilmActorPK filmActorPK = new FilmActorPK();
			    filmActorPK.setActor_id(actor_id);
			    filmActorPK.setFilm_id(film_id);
			    
			    //enlazo la llave a filmActor
			    filmActor.setId(filmActorPK);
			    
			    //retorno y guardo                                GUARDO!!!                       
			    return new ResponseEntity<FilmActor>(filmActorRepository.save(filmActor), HttpStatus.CREATED);
			}
				//SI ES QUE NO EXISTE ALGUNO     RETORNO ERROR
			return new ResponseEntity<FilmActor>(HttpStatus.BAD_REQUEST);
		}
		 //SI ES QUE NO EXISTE ALGUNO RETORNO ERROR
		return new ResponseEntity<FilmActor>(HttpStatus.BAD_REQUEST);
		

	 } 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Actor update(@PathVariable("id") Integer id, @RequestBody Actor resource) {
        Actor actor = actorRepository.findOne(id);
        actor.setFirstName(resource.getFirstName());
        actor.setLastName(resource.getLastName());
        actorRepository.save(actor);
        return actor; 
	} 
	
	//SOLO USAR CON OBJETOS CREADOS POR UNO!
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) { 
		System.out.println("CHUPAMELA CON MAYO");
		actorRepository.delete(id);
	}
	
}
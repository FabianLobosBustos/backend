package cl.citiaps.spring.backend.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.entities.FilmActor;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@CrossOrigin
@RestController  
@RequestMapping("/films")
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Film> getAllFilms() {
		return filmRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Film findOne(@PathVariable("id") Integer id) {
		return filmRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	@ResponseBody
	public  Collection<Actor> findActor(@PathVariable("id") Integer id) {
		Film film = filmRepository.findOne(id);
        Iterator<FilmActor> iter = film.getFilmActor().iterator();
        FilmActor filmActor;
        Actor actor;
        Collection<Actor> actors = new ArrayList<Actor>();
        while(iter.hasNext()) {
        	filmActor = (FilmActor)iter.next();
        	actor= filmActor.getActor();
        	actors.add(actor);
        }
        
        System.out.println("EN LA NUEVA RUTA!!!");
        return actors;
		
	}

	/*
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Actor> create(@RequestBody Actor resource) {
		if (resource.getFirstName().isEmpty() || resource.getLastName().isEmpty()){
			return new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
		}else{
			
			return new ResponseEntity<Actor>(actorRepository.save(resource), HttpStatus.CREATED);
		}
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

	*/
}

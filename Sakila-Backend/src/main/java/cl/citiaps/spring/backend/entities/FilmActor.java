package cl.citiaps.spring.backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private FilmActorPK id;

    @ManyToOne
    @MapsId("film_id") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "film_id")
    @JsonBackReference
    private Film film;

    @ManyToOne
    @MapsId("actor_id")
    @JoinColumn(name = "actor_id")
    @JsonBackReference
    private Actor actor;

	public FilmActorPK getId() {
		return id;
	}

	public void setId(FilmActorPK id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
    
    
}

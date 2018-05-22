package cl.citiaps.spring.backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FilmActorPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "film_id")
    private int film_id;

    @Column(name = "actor_id")
    private int actor_id;

	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public int getActor_id() {
		return actor_id;
	}

	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}
    
    
	
}
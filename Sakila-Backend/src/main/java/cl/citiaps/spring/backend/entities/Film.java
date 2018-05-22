package cl.citiaps.spring.backend.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"filmActor"})
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT a FROM Film a")
public class Film {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="film_id", unique=true, nullable=false)
	private int film_id; // A surrogate primary key used to uniquely identify each film in the table.

	@Column(name="title", nullable=false)
	private String title; // The title of the film.

	@Column(name="description", nullable=false)
	private String description; //A short description or plot summary of the film.
	
	@OneToMany(mappedBy = "film")
	@JsonManagedReference
    private Set<FilmActor> filmActor = new HashSet<FilmActor>();
	
	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Set<FilmActor> getFilmActor() {
		return filmActor;
	}

	public void setFilmActor(Set<FilmActor> filmActor) {
		this.filmActor = filmActor;
	}
	
   

}

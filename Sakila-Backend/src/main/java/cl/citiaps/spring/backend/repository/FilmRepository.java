package cl.citiaps.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.citiaps.spring.backend.entities.Film;



public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {
	
}

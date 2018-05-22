package cl.citiaps.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.citiaps.spring.backend.entities.Actor;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Integer> {
	

}

package cl.citiaps.spring.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.citiaps.spring.backend.entities.Stadistic;

public interface StadisticRepository extends PagingAndSortingRepository<Stadistic, Integer> {
	

}

package com.epicode.Spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Hall;

public interface HallRepository extends CrudRepository<Hall, Long>{
	@Query("SELECT h FROM Hall h WHERE h.status=AVAILABLE")
	public List<Hall> findAvailableHalls();
}

package com.der.mchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Stars;

@Repository
public interface CastRepository extends JpaRepository<Stars, Integer> {
	
	@Query(value = "SELECT s FROM Stars s WHERE s.name=:name")
	public Stars findStarsByName(@Param("name")String name);

}

package com.der.mchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Generes;
import com.der.mchannel.entity.Stars;

@Repository
public interface GeneresRepository extends JpaRepository<Generes, Integer> {

}

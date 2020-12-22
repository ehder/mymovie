package com.der.mchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Directors;

@Repository
public interface DirectorsRepository extends JpaRepository<Directors, Integer> {

}

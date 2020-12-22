package com.der.mchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query(value = "SELECT r FROM Role r WHERE name=:name")
	public Role findRoleByName(@Param("name")String name);

}

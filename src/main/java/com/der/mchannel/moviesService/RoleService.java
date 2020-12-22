package com.der.mchannel.moviesService;

import java.util.List;

import com.der.mchannel.entity.Role;

public interface RoleService {
	
	public Role save(Role role);
	
	public List<Role> findAll();
	
	public void findById(Integer id);
	
	public Role findByName(String name);
}

package com.der.mchannel.moviesServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.der.mchannel.entity.Role;
import com.der.mchannel.moviesService.RoleService;
import com.der.mchannel.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repo;

	@Override
	public Role save(Role role) {
		return repo.save(role);
	}

	@Override
	public void findById(Integer id) {
		repo.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return repo.findAll();
	}

	@Override
	public Role findByName(String name) {
		return repo.findRoleByName(name);
	}

}

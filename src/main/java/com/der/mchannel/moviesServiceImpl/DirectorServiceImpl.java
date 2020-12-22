package com.der.mchannel.moviesServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.der.mchannel.entity.Directors;
import com.der.mchannel.moviesService.DirectorsService;
import com.der.mchannel.repository.DirectorsRepository;

@Service
public class DirectorServiceImpl implements DirectorsService {

	@Autowired
	private DirectorsRepository dRepo;

	@Override
	public void addDirectors(Directors directors) {
		dRepo.save(directors);
	}

	@Override
	public Optional<Directors> findDirectors(Integer id) {
		return dRepo.findById(id);
	}

	@Override
	public List<Directors> findAllDirector() {
		return dRepo.findAll();
	}

	@Override
	public void deleteDirectorById(Integer id) {
		dRepo.deleteById(id);
		
	}

}

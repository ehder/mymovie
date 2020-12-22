package com.der.mchannel.moviesServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.der.mchannel.entity.Generes;
import com.der.mchannel.moviesService.GeneresService;
import com.der.mchannel.repository.GeneresRepository;

@Service
public class GeneresServiceImpl implements GeneresService {

	@Autowired
	private GeneresRepository gRepo;

	@Override
	public void addGeneres(Generes g) {
		gRepo.save(g);
	}

	@Override
	public Optional<Generes> findGeneres(Integer id) {

		return gRepo.findById(id);
	}

	@Override
	public List<Generes> findAllGeneres() {
		return gRepo.findAll();
	}

	@Override
	public void deleteGeneresById(Integer id) {
		gRepo.deleteById(id);
	}

}

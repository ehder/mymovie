package com.der.mchannel.moviesServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.der.mchannel.entity.Stars;
import com.der.mchannel.moviesService.CastService;
import com.der.mchannel.repository.CastRepository;

@Service
public class CastServiceImpl implements CastService {
	
	@Autowired
	private CastRepository castRepo;

	@Override
	public void addStar(Stars s) {
		castRepo.save(s);
	}

	@Override
	public Optional<Stars> findStars(Integer id) {
		return castRepo.findById(id);
	}
	
	@Override
	public List<Stars> findAllStar(){
		return castRepo.findAll();
	}

	@Override
	public void deleteCast(Integer id) {
		castRepo.deleteById(id);
		
	}

	@Override
	public Stars findStarByName(String name) {
		return castRepo.findStarsByName(name);
	}

}

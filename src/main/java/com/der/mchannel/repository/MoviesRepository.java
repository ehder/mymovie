package com.der.mchannel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.der.mchannel.entity.Movies;

//to retrieve @lob you will need @Transactional otherwise you will get error
@Transactional
@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer>{
	
	@Query(value = "SELECT m FROM Movies m  where m.code = :code")
	public Movies findMoviesByCode(@Param("code")String code);
	
	@Query(value = "SELECT m FROM Movies m WHERE CONCAT(m.title,' ', m.year) LIKE %?1%")
	public List<Movies> search(String keyword);
	
	/*@Query(value = "SELECT m FROM Movies m limit %s = :ps offset %s = :pos")
	public List<Movies> findAllMovies(@Param("ps")int pageSize,@Param("pos")int pageOffset);*/
	
	@Query(value = "SELECT count(*) FROM Movies m")
	public int findMovieCount();
	
	public Optional<Movies> getByCode(String code);

}

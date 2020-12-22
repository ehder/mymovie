package com.der.mchannel.moviesServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.der.mchannel.entity.Movies;
import com.der.mchannel.form.MoviesForm;
import com.der.mchannel.mapper.MoviesMapper;
import com.der.mchannel.moviesService.MoviesService;
import com.der.mchannel.repository.MoviesRepository;

@Service
// @Transactional
public class MoviesServiceImpl implements MoviesService {

	// private final String SQL_FIND_ALL_MOVIES = "select * from movies order by id
	// on desc";
	private final String SQL_FIND_MOVIES_WITH_PAGINATION = "select * from movies order by id desc limit %s offset %s ";
	private final String SQL_MOVIES_COUNT = "select count(*) from movies";
	// private final String SQL_MOVIE_BY_CODE = "select * from movies where code =
	// ?";

	@Autowired
	private MoviesRepository movieRepo;// order by m.code on desc

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void saveMovie(Movies movie) {
		movieRepo.save(movie);
	}

	@Override
	public void save(MoviesForm moviesForm) {
		String code = moviesForm.getCode();

		Movies movie = null;

		if (code != null) {
			movie = findMovieByCode(code);
		}

		if (movie == null) {
			movie = new Movies();
		}

		movie.setCode(code);
		movie.setTitle(moviesForm.getTitle());
		movie.setYear(moviesForm.getYear());
		movie.setRuntime(moviesForm.getRuntime());
		movie.setImdbRating(moviesForm.getImdbRating());
		movie.setRottenTomatoes(moviesForm.getRottenTomatoes());
		movie.setRottenAudience(moviesForm.getRottenAudience());
		movie.setDescription(moviesForm.getDescription());
		movie.setMovieLink(moviesForm.getMovieLink());
		movie.setMovieTrailer(moviesForm.getMovieTrailer());
		movie.setDirector(moviesForm.getDirectors());
		movie.setGeneres(moviesForm.getGeneres());
		movie.setStars(moviesForm.getStars());

		if (moviesForm.getFileData() != null) {
			byte[] image = null;
			try {
				image = moviesForm.getFileData().getBytes();
			} catch (IOException e) {

			}
			if (image != null && image.length > 0) {
				movie.setImage(image);
			}
		}

		movieRepo.save(movie);
	}

	// find with @query
	@Override
	public Movies findMovieByCode(String code) {
		return movieRepo.findMoviesByCode(code);
	}

	@Override
	// @Transactional(readOnly = true)
	public List<Movies> findAllMovies(int pageSize, int pageOffset) {
		return jdbcTemplate.query(String.format(SQL_FIND_MOVIES_WITH_PAGINATION, pageSize, pageOffset),
				new MoviesMapper());
		/* return movieRepo.findAllMovies(pageSize, pageOffset); */
	}

	@Override
	public int getMoviesCount() {
		return jdbcTemplate.queryForObject(SQL_MOVIES_COUNT, Integer.class);
	}

	@Override
	public List<Movies> findAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public void deleteMovieById(Integer id) {
		movieRepo.deleteById(id);

	}

	@Override
	public Movies findMovie(String code) {

		String sql = "Select m from " + Movies.class.getName() + " m where m.code =:code ";
		// Query query = jdbcTemplate.query(sql, Movies.class);
		return null;
	}

	@Override
	public void deleteMovie(Movies movie) {
		movieRepo.delete(movie);

	}

	@Override
	public List<Movies> search(String keyword) {
		if (keyword != null) {
			return movieRepo.search(keyword);
		}
		return movieRepo.findAll();
	}

	/*
	 * @Override public List<Movies> findMoviesByDirectors(String name) { return
	 * movieRepo.findMovideByDirectors(name); }
	 */

}

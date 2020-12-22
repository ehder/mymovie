package com.der.mchannel.mapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import com.der.mchannel.entity.Movies;

public class MoviesMapper implements RowMapper<Movies> {

	public MultipartFile movieImage;

	@Override
	public Movies mapRow(ResultSet resultSet, int i) throws SQLException {
		Movies movie = new Movies();

		movie.setTitle(resultSet.getString("title"));
		movie.setCode(resultSet.getString("code"));
		
/*		movie.setYear(resultSet.getDate("year"));
		movie.setRuntime(resultSet.getInt("runtime"));
		movie.setImdbRating(resultSet.getFloat("imdb_rating"));
		movie.setRottenTomatoes(resultSet.getInt("rotten_tomatoes"));
		movie.setDescription(resultSet.getString("description"));*/

		if (movieImage != null) {
			byte[] image = null;
			try {
				image = getMovieImage().getBytes();
			} catch (IOException e) {
				// TODO: handle exception
			}
			if (image != null && image.length > 0) {
				movie.setImage(resultSet.getBytes("image"));
			}
		}
		return movie;
	}

	public MultipartFile getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(MultipartFile movieImage) {
		this.movieImage = movieImage;
	}

}

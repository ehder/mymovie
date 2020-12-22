package com.der.mchannel.form;

import java.util.Date;
import java.util.List;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.der.mchannel.entity.Directors;
import com.der.mchannel.entity.Generes;
import com.der.mchannel.entity.Movies;
import com.der.mchannel.entity.Stars;

public class MoviesForm {

	private String code;
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date year;
	
	@NotBlank(message = "run time is require")
	private int runtime;
	
	private float imdbRating;
	
	private int rottenTomatoes;
	
	private int rottenAudience;
	
	private String description;
	
	private String movieLink;
	
	private String movieTrailer;
	
	private List<Directors> directors;
	
	private List<Generes> generes;
	
	private List<Stars> stars;

	private boolean newMovie = false;

	// upload file
	@Lob
	private MultipartFile fileData;

	public MoviesForm() {
		this.newMovie = true;
	}

	public MoviesForm(Movies movie) {
		this.code = movie.getCode();
		this.title = movie.getTitle();
		this.year = movie.getYear();
		this.runtime = movie.getRuntime();
		this.imdbRating = movie.getImdbRating();
		this.rottenTomatoes = movie.getRottenTomatoes();
		this.description = movie.getDescription();
		this.movieLink = movie.getMovieLink();
		this.movieTrailer = movie.getMovieTrailer();
		this.generes = movie.getGeneres();
		this.directors = movie.getDirector();
		this.stars = movie.getStar();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public int getRottenTomatoes() {
		return rottenTomatoes;
	}

	public void setRottenTomatoes(int rottenTomatoes) {
		this.rottenTomatoes = rottenTomatoes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Directors> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Directors> directors) {
		this.directors = directors;
	}

	public List<Generes> getGeneres() {
		return generes;
	}

	public void setGeneres(List<Generes> generes) {
		this.generes = generes;
	}

	public List<Stars> getStars() {
		return stars;
	}

	public void setStars(List<Stars> stars) {
		this.stars = stars;
	}

	public boolean isNewMovie() {
		return newMovie;
	}

	public void setNewMovie(boolean newMovie) {
		this.newMovie = newMovie;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}

	public String getMovieTrailer() {
		return movieTrailer;
	}

	public void setMovieTrailer(String movieTrailer) {
		this.movieTrailer = movieTrailer;
	}

	public int getRottenAudience() {
		return rottenAudience;
	}

	public void setRottenAudience(int rottenAudience) {
		this.rottenAudience = rottenAudience;
	}
	
	

}

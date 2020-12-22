package com.der.mchannel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movies")
public class Movies implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6633758746151614848L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "code", length = 20, nullable = false)
	//@NotEmpty(message = "enter movie code")
	private String code;

	@Column(name = "title", length = 255, nullable = false)
	//@NotEmpty(message = "enter movie name")
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	/* @Column(name = "year", nullable = false) */
	private Date year;

	// add @Type Or Error will get-> ERROR: column "image" is of type bytea but
	// expression is of type bigint
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	@Column(name = "runtime", nullable = false)
	private int runtime;

	@Column(name = "imdb_rating", nullable = false)
	private Float imdbRating;

	@Column(name = "rotten_tomatoes", nullable = false)
	private int rottenTomatoes;
	
	@Column(name = "rotten_audience" , nullable = false)
	private int rottenAudience;

	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "movie_link")
	private String movieLink;
	
	@Column(name = "movie_trailer")
	private String movieTrailer;
	
	
	@ManyToMany
	private List<Directors> director;

	@ManyToMany
	private List<Stars> star;

	@ManyToMany
	private List<Generes> generes;
	
	@ManyToOne
	private Account user;
	
	@OneToMany(mappedBy = "movie")
	private List<Comment> comments;

	public Movies() {
	}

	public List<Generes> getGeneres() {
		return generes;
	}

	public void setGeneres(List<Generes> generes) {
		this.generes = generes;
	}

	public List<Stars> getStars() {
		return star;
	}

	public void setStars(List<Stars> stars) {
		this.star = stars;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Float imdbRating) {
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

	public List<Directors> getDirector() {
		return director;
	}

	public void setDirector(List<Directors> director) {
		this.director = director;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}

	public List<Stars> getStar() {
		return star;
	}

	public void setStar(List<Stars> star) {
		this.star = star;
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

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	

}

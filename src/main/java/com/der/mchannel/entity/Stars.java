package com.der.mchannel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "stars")
public class Stars implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3477936865858044098L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 50)
	private Integer id;

	@Column(name = "code", length = 50)
	@NotEmpty(message = "enter star code")
	private String code;

	@Column(name = "name", length = 255, nullable = false)
	@NotEmpty(message = "enter star name")
	private String name;

	@ManyToMany(mappedBy = "star")
	private List<Movies> movies;

	public Stars() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movies> getMovies() {
		return movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

}

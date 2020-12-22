package com.der.mchannel.model;

import com.der.mchannel.entity.Movies;

public class MovieInfo {

	private String code;
	private String title;

	public MovieInfo() {
	}

	public MovieInfo(Movies movies) {
		super();
		this.code = movies.getCode();
		this.title = movies.getTitle();
	}

	public MovieInfo(String code, String title) {
		super();
		this.code = code;
		this.title = title;
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

}

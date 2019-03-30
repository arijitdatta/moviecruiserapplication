package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "overview", length=10000)
	private String overview;
	@Column(name = "poster_path", length=10000)
	private String poster_path;
	@Column(name="release_date")
	private String release_date;
	
	public Movie() {
		
	}

	public Movie(int id, String title, String overview, String poster_path, String release_date) {
		super();
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.poster_path = poster_path;
		this.release_date = release_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	
	
	
	
	

	

	

}

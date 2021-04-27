package com.tsis.movieyeme.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.tsis.movieyeme.dto.Movie;
import com.tsis.movieyeme.dto.Show;
import com.tsis.movieyeme.dto.Theater;
import com.tsis.movieyeme.dto.Ticket;

public interface YemeService {
	public List<Movie> getAllMovies();
	public List<Theater> getAllTheaters();
	public List<Show> getShows();
	public Show getShow(int showId);
	public List<Ticket> getAllTickets(int showId);
	public List<Ticket> getTickets(String phone);
	public void setTickets();
	public Theater getTheater(Integer tid);
	public void reserve(int showid, String phone, JsonNode seats);
}

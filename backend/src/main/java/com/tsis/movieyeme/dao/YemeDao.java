package com.tsis.movieyeme.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tsis.movieyeme.dto.Movie;
import com.tsis.movieyeme.dto.Show;
import com.tsis.movieyeme.dto.Theater;
import com.tsis.movieyeme.dto.Ticket;

@Mapper
public interface YemeDao {
	public List<Movie> getMovies();
	public List<Theater> getTheaters();
	public List<Show> getShows();
	public Show getShow(int showId);
	public List<Ticket> getAllTickets(int show_id);
	public List<Ticket> getTickets(String phone);
	public int reserve(int showid, String phone, int width, int height);
	public void setTicket(int show_id, int width, int height);
}
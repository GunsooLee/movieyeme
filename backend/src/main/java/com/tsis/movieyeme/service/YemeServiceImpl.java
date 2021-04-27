package com.tsis.movieyeme.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tsis.movieyeme.dao.YemeDao;
import com.tsis.movieyeme.dto.Movie;
import com.tsis.movieyeme.dto.Show;
import com.tsis.movieyeme.dto.Theater;
import com.tsis.movieyeme.dto.Ticket;

@Service
public class YemeServiceImpl implements YemeService{

	@Autowired
	private YemeDao yemeDao;
	
	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		movies = yemeDao.getMovies();
		return movies;
	}

	@Override
	public List<Theater> getAllTheaters() {
		return yemeDao.getTheaters();
	}

	@Override
	public List<Show> getShows() {
		return yemeDao.getShows();
	}

	@Override
	public List<Ticket> getAllTickets(int showId) {
		return yemeDao.getAllTickets(showId);
	}

	@Override
	public List<Ticket> getTickets(String phone) {
		return yemeDao.getTickets(phone);
	}

	@Override
	public void setTickets() {
		// TODO Auto-generated method stub
		
		List<Show> shows = yemeDao.getShows();
		List<Theater> theaters = yemeDao.getTheaters();
		for (Show show : shows) {
			for (Theater theater : theaters) {
				if(theater.getId() == show.getTheater_id()) {
					for(int i = 1; i <= theater.getHeight(); i++) {
						for(int j = 1; j <= theater.getWidth(); j++) {
							yemeDao.setTicket(show.getId(), i, j);
						}
					}
				}
			}
		}
	}

	@Override
	public Show getShow(int showId) {
		return yemeDao.getShow(showId);
	}

	@Override
	public Theater getTheater(Integer tid) {
		List<Theater> theaters = yemeDao.getTheaters();
		
		for (Theater theater : theaters) {
			if(theater.getId() == tid) {
				return theater;
			}
		}
		return null;
	}

	@Override
	public void reserve(int showid, String phone, JsonNode seats) {
		for (JsonNode jsonNode : seats) {
			yemeDao.reserve(showid, phone, Integer.parseInt(jsonNode.toString())/100, Integer.parseInt(jsonNode.toString())%100);
		}
	}
}

package com.tsis.movieyeme.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsis.movieyeme.dto.Movie;
import com.tsis.movieyeme.dto.Show;
import com.tsis.movieyeme.dto.Theater;
import com.tsis.movieyeme.dto.Ticket;
import com.tsis.movieyeme.service.YemeService;

import io.swagger.annotations.ApiOperation;


//http://localhost:8443/swagger-ui.html#/
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/yeme")
public class YemeController {
//	private static final Logger logger = LoggerFactory.getLogger(YemeController.class);
    
	@Autowired
	private YemeService yemeService;

	@ApiOperation(value = "모든 영화 목록", notes = "모든 영화 목록을 불러온다")
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMoives() throws Exception {
		return new ResponseEntity<List<Movie>>(yemeService.getAllMovies(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "모든 상영 목록", notes = "모든 상영 목록을 불러온다. ")
	@GetMapping("/shows")
	public ResponseEntity<List<Show>> getMoives() throws Exception {
		ResponseEntity<List<Show>> result = new ResponseEntity<List<Show>>(yemeService.getShows(), HttpStatus.OK);
		return result;
	}
	
	@ApiOperation(value = "상영정보", notes = "아이디로 해당 상영 정보를 불러온다. ")
	@GetMapping("/show")
	public ResponseEntity<Show> getShow(
			@RequestParam(value = "showid") Integer showid
			) throws Exception {
		ResponseEntity<Show> result = new ResponseEntity<Show>(yemeService.getShow(showid), HttpStatus.OK);
		return result;
		
	}
	
	@ApiOperation(value = "상영정보", notes = "아이디로 해당 상영 정보를 불러온다. ")
	@GetMapping("/theater")
	public ResponseEntity<Theater> getTheater(
			@RequestParam(value = "tid") Integer tid
			) throws Exception {
		ResponseEntity<Theater> result = new ResponseEntity<Theater>(yemeService.getTheater(tid), HttpStatus.OK);
		return result;
	}
	
	@ApiOperation(value = "티켓 목록", notes = "해당 상영 아이디의 모든 티켓 목록을 불러온다.")
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getTickets(
			@RequestParam(value = "showid") Integer showid
			) throws Exception {
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(yemeService.getAllTickets(showid), HttpStatus.OK);
		return result;
	}
	


	@ApiOperation(value = "예매하기", notes = "(전화번호, 좌석 번호) 리스트를 받아서 DB에 추가한다.")
	@PostMapping("/yeme")
	public ResponseEntity<HashMap<String, Object>> addTicket(
			@Param(value = "showid") Integer showid,
			@Param(value = "phone") String phone,
			@Param(value = "seats") String seats,
			HttpServletRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(URLDecoder.decode(seats));
		System.out.println(json.size());

		
		yemeService.reserve(showid, phone, json);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String result = "success";
		map.put("result", result);
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "전화번호 검색", notes = "전화번호로 예매된 좌석이 있는지 검색한다.")
	@GetMapping("/yemes")
	public ResponseEntity<List<Ticket>> getAllYemes(
			@RequestParam(value = "phone") String phone) throws Exception {
		return new ResponseEntity<List<Ticket>>(yemeService.getTickets(phone), HttpStatus.OK);
	}
	
	@GetMapping("/ttt")
	public ResponseEntity<Integer> setTickets() throws Exception {
		yemeService.setTickets();
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
	
}
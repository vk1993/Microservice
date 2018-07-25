package com.springRest.demo.SpringRest.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springRest.demo.SpringRest.service.RestServiceEnd;
import com.springRest.demo.SpringRest.utility.BadRequest;

@RestController
@RequestMapping(RestData.BASE_URL)
public class RestData {

	public static final String BASE_URL = "/api";
	@Autowired
	private RestServiceEnd restServiceEnd;

	@GetMapping(value = "/Fibonacci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public int getNthFibonacci(@PathParam("n") Long n, HttpServletResponse response) throws BadRequest {
		if (n instanceof Long && n > 0) {
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-Control", "no-cache");
			response.setHeader("expires", "-1");
			response.setContentLength(122);

			return restServiceEnd.getNthFebbonic(n);
		} else {
			throw new BadRequest("enter valid url");
		}
	}

	@GetMapping(value = "/ReverseWords")
	@ResponseStatus(value = HttpStatus.OK)
	public String getReverse(@PathParam("sentence") String sentence, HttpServletResponse response) throws BadRequest {
		if (sentence instanceof String && sentence != "") {
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-Control", "no-cache");
			response.setHeader("expires", "-1");
			response.setContentLength(131);

			return restServiceEnd.getReverse(sentence);
		} else {
			throw new BadRequest("Bad Request");
		}
	}

	@GetMapping(value = "/TriangleType",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public String getTriangle(@PathParam("a") int a, @PathParam("b") int b, @PathParam("c") int c,
			HttpServletResponse response) throws BadRequest {
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new BadRequest("enter valied url");
		}
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-Control", "no-cache");
		response.setHeader("expires", "-1");
		response.setContentLength(131);
		return restServiceEnd.getTriangle(a, b, c);
	}

	@PostMapping(value = "/makeonearray",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Integer[]> makeOneArray(@RequestBody Map<String, Integer[]> array, HttpServletResponse response)
			throws BadRequest {

		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("expires", "-1");
		response.setContentLength(131);

		return restServiceEnd.getArray(array);
	}

}

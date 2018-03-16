package com.kamijou.controllers;

import com.kamijou.entities.GameResult;
import com.kamijou.entities.Joke;
import com.kamijou.services.HttpService;
import com.kamijou.services.TellJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class TellJokeController {
	@Autowired
	private TellJokeService tellJokeService;
	@Autowired
	private HttpService httpService;
	private RestTemplate restTemplate;
	@Value("${rest.username}")
	String username;
	@Value("${rest.password}")
	String password;
	public TellJokeController(TellJokeService tellJokeService) {
		restTemplate = new RestTemplate();
	}
	@RequestMapping({"/",""})
	public String getJoke(Model model) {
		System.out.println("getJoke called");
		model.addAttribute("joke", tellJokeService.tellRandomJoke());
		return "joke";
	}
	@PostMapping({"/", ""})
	@ResponseBody
	public String getJoke(Model model, @RequestBody Joke newJoke){
		System.out.println("new joke added");
		tellJokeService.addJoke(newJoke.getJokeString());
		return newJoke.getJokeString();
	}
	@GetMapping("/remoteJoke")
	public String getRemoteJoke(Model model){
		System.out.println("getRemoteJoke called, with user: " + username +" and password: " + password);
		HttpHeaders header = httpService.createHeaders(username, password);
		try {
			ResponseEntity<Joke> joke = restTemplate.exchange("http://localhost:8183/randomJoke", HttpMethod.GET, new HttpEntity<Joke>(header), Joke.class);
			model.addAttribute("joke", joke.getBody().getJokeString());
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("wrong credentials");
			model.addAttribute("error", "wrong credential");
		}
		return "joke";
	}
	@GetMapping("/dotaMatch/{id}")
	public GameResult getDotaMatch(@PathVariable("id") String id){
		try {
			URI uri = new URI("http://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?match_id=3779813526&key=CE3CED7F622A884B1A50FE904250D0C3");
			System.out.println("here");
			ResponseEntity<GameResult> gameResultResponseEntity = restTemplate.getForEntity(uri, GameResult.class);
			return gameResultResponseEntity.getBody();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}

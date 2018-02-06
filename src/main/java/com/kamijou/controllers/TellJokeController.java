package com.kamijou.controllers;

import com.kamijou.entities.Joke;
import com.kamijou.services.TellJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TellJokeController {
	private TellJokeService tellJokeService;
	@Autowired
	public TellJokeController(TellJokeService tellJokeService) {
		this.tellJokeService = tellJokeService;
	}
	@RequestMapping({"/",""})
	public String getJoke(Model model) {
		System.out.println("getJoke called");
		model.addAttribute("joke", tellJokeService.tellRandomJoke());
		return "joke";
	}
	@PostMapping({"/", ""})
	public String getJoke(Model model, @RequestBody Joke newJoke){
		System.out.println("new joke added");
		tellJokeService.addJoke(newJoke.getJoke());
		return newJoke.getJoke();
	}
}

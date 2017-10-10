package com.kamijou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kamijou.services.TellJokeService;

@Controller
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
}

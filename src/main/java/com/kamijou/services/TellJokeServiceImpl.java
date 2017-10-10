package com.kamijou.services;

import org.springframework.stereotype.Service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
@Service
public class TellJokeServiceImpl extends ChuckNorrisQuotes implements TellJokeService {
	@Override
	public String tellRandomJoke() {
		return getRandomQuote();
	}

}

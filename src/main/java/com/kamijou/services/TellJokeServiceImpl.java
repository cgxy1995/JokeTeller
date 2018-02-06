package com.kamijou.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TellJokeServiceImpl extends ChuckNorrisQuotes implements TellJokeService {
	List<String> moreJokes = new ArrayList<>();
	Integer jokeOriginalSize = 123;
	@Override
	public String tellRandomJoke() {
		Random rand = new Random();
		int ran = rand.nextInt(jokeOriginalSize + moreJokes.size() + 1);
		if(ran < jokeOriginalSize)
			return getRandomQuote();
		else return moreJokes.get(ran - jokeOriginalSize);
	}
	@Override
	public void addJoke(String joke){
		moreJokes.add(joke);
	}
}

package com.prsquared.fixtures.model;

import java.util.List;

public class Round{

	private final List<Match> matches;
	private final int round;
	public Round(List<Match> matches, int round) {
		this.matches = matches;
		this.round = round;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public int getRound() {
		return round;
	}
	@Override
	public String toString() {
		return  matches.toString();
	}
	
}

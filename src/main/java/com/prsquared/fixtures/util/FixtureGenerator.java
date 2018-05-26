package com.prsquared.fixtures.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.prsquared.fixtures.model.Match;
import com.prsquared.fixtures.model.Round;

public class FixtureGenerator {

	public static List<Round> createFixtures(String[] teamNames, DateUtil dateUtil, int fixtureCount) throws ParseException {
		List<String> shuffledNameList = new ArrayList<>(Arrays.asList(teamNames));
		Collections.shuffle(shuffledNameList);
		String[] shuffledNames = shuffledNameList.toArray(new String[0]);
		
		List<Round> roundList = createFixtureUtil(shuffledNames, dateUtil,0);
		if(fixtureCount > 1) {
			for(int i=1;i<fixtureCount;i++) {
				dateUtil.setDateStr(dateUtil.getFixtureDate(i*(roundList.size()-1)+1));
				roundList.addAll(getReturnFixtures(roundList, dateUtil,i*(roundList.size()-1)+1));
			}
		}
		return roundList;
	}
	
	private static List<Round> createFixtureUtil(String[] teamNames, DateUtil dateUtil, int roundOffset) throws ParseException {
		int teams = teamNames.length;
		boolean ghost = false;
		if (teams % 2 == 1) {
			teams++;
			ghost = true;
		}
		
		teamNames = Arrays.copyOf(teamNames, teams);
		int totalRounds = teams - 1;
		int matchesPerRound = teams / 2;
		Match[][] rounds = new Match[totalRounds][matchesPerRound];

		for (int round = 0; round < totalRounds; round++) {
			for (int match = 0; match < matchesPerRound; match++) {
				int home = (match + round) % (teams - 1);//
				int away = ((teams - 1 - match) + round) % (teams - 1);
				// Last team stays in the same place while the others
				// rotate around it.
				if (match == 0) {
					away = teams - 1;
				}
				rounds[round][match] = new Match(null, teamNames[home], teamNames[away]);
			}
		}
		Match[][] interleaved = new Match[totalRounds][matchesPerRound];

		int evn = 0;
		int odd = (teams / 2);
		for (int i = 0; i < rounds.length; i++) {
			if (i % 2 == 0) {
				interleaved[i] = rounds[evn++];
			} else {
				interleaved[i] = rounds[odd++];
			}
		}
		rounds = interleaved;
		for (int round = 0; round < rounds.length; round++) {
			if (Math.random() <= 0.5) {
				rounds[round][0] = flip(rounds[round][0]);
			}
		}
		List<Round> fixtureList = new ArrayList<>();
		for (int i = 0; i < rounds.length; i++) {
			String date = dateUtil.getFixtureDate(i);
			List<Match> roundFixtures = new ArrayList<>();
			for (int j = 0; j < matchesPerRound; j++) {
				if (ghost && j == 0)
					continue;
				roundFixtures.add(new Match(date, rounds[i][j].getHomeClubName(), rounds[i][j].getAwayClubName()));
			}
			Round roundObj = new Round(roundFixtures, roundOffset + i + 1);
			fixtureList.add(roundObj);
		}
		return fixtureList;
	}
	
	private static List<Round> getReturnFixtures(List<Round> roundList, DateUtil dateUtil, int roundOffset) throws ParseException{
		List<Round> returnRoundList =  new ArrayList<>();
		int i=roundOffset;
		for(Round round:roundList) {
			i++;
			String date = dateUtil.getFixtureDate(i);
			List<Match> matchList= new ArrayList<>();
			for(Match match:round.getMatches()) {
				Match flippedMatch = flip(match);
				Match returnMatch = new Match (date, flippedMatch.getHomeClubName(),flippedMatch.getAwayClubName());
				matchList.add(returnMatch);
			}
			Round flippedRound = new Round(matchList, round.getRound()+roundOffset);
			returnRoundList.add(flippedRound);
		}
		return returnRoundList;
	}
	private static Match flip(Match match) {
		return new Match(match.getDate(), match.getAwayClubName(), match.getHomeClubName());
	}
}

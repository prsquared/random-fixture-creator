package com.prsquared.fixtures.randomfixturecreator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.prsquared.fixtures.model.Match;
import com.prsquared.fixtures.model.Round;
import com.prsquared.fixtures.util.DateUtil;
import com.prsquared.fixtures.util.FixtureGenerator;

public class App {

	public static String[] teamNames= {"Man City","Man Utd","Spurs","Liverpool","Chelsea","Arsenal","Burnley",
			"Everton","Leicester", "Newcastle","Palace","Bournemouth","West Ham","Watford",
			"Brighton","Huddersfield","Southampton","Swansea","Stoke","West Brom"};
    public static void main(String[] args) throws ParseException {
    	List<Round> roundList = FixtureGenerator.createFixtures(teamNames,new DateUtil(new SimpleDateFormat("dd/MM/yyyy"), "23/05/2018", 7),2);
    	
    	System.out.println(roundList);
    	for(Round round: roundList) {
    		System.out.println("Matchday "+ round.getRound() + "\n");
    		for(Match match: round.getMatches()) {
    			System.out.println(match.getDate()+" : "+match.getHomeClubName()+" vs "+match.getAwayClubName());
    		}
    		System.out.println();
    	}
    }
}

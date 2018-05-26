package com.prsquared.fixtures.model;

public final class Match implements Comparable<Match>{

	private final String date;
	private final String homeClubName;
	private final String awayClubName;
	
	public Match(String date, String homeClubName, String awayClubName) {
		this.date = date;
		this.homeClubName = homeClubName;
		this.awayClubName = awayClubName;
	}


	public String getDate() {
		return date;
	}


	public String getHomeClubName() {
		return homeClubName;
	}


	public String getAwayClubName() {
		return awayClubName;
	}


	@Override
	public String toString() {
		return (date!=null?date.toString():"") + " : " + homeClubName + " vs " + awayClubName;
	}

	@Override
	public int compareTo(Match m) {
		return this.getHomeClubName().compareTo(m.getHomeClubName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayClubName == null) ? 0 : awayClubName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((homeClubName == null) ? 0 : homeClubName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (awayClubName == null) {
			if (other.awayClubName != null)
				return false;
		} else if (!awayClubName.equals(other.awayClubName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (homeClubName == null) {
			if (other.homeClubName != null)
				return false;
		} else if (!homeClubName.equals(other.homeClubName))
			return false;
		return true;
	}
	
}

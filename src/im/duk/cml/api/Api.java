/*******************************************************************************
 * Copyright 2014 Andreas Holley
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package im.duk.cml.api;

import im.duk.cml.util.SkillFunc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * A class for making requests to the CrystalMathLabs api.
 * 
 * Requests are returned in the same format as in a web browser.
 * 
 * @author Andreas Holley
 * 
 */
public class Api {

	/**
	 * Make a an update request, which attempts to update the player on
	 * crystalmathlabs.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the return code from the api: 1 = Success! 2 = Player not on
	 *         RuneScape hiscores. 3 = Negative XP gain detected. 4 = Unknown
	 *         error. 5 = This player has been updated within the last 60
	 *         seconds. 6 = The player name was invalid.
	 * 
	 */
	public static String updateReq(String player) {
		player = replaceSpaces(player);
		String req = "type=update&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Make a lastcheck request, which gets the time(seconds) since the most
	 * recent data point.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the time since the last data point, in seconds.
	 * 
	 */
	public static String lastCheckReq(String player) {
		player = replaceSpaces(player);
		String req = "type=lastcheck&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Make a lastchange request, which gets the time(seconds) since the most
	 * recent data point in which xp changed.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the time since the last data point, in seconds.
	 */
	public static String lastChangeReq(String player) {
		player = replaceSpaces(player);
		String req = "type=lastchange&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Make a stats request, updates the player, gets the time of the most
	 * recent datapoint and the xp and rank for each skill.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the time since the most recent datapoint, followed by the xp and
	 *         rank for each skill, in the format:<br/>
	 *         (Time of most recent cml datapoint)<br/>
	 *         (xp[0]),(rank[0])<br/>
	 *         (xp[1]),(rank[1])<br/>
	 *         ...<br/>
	 *         (xp[23]),(rank[23])
	 */
	public static String statsReq(String player) {
		player = replaceSpaces(player);
		String req = "type=stats&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Get the approximate number of hours the given needs to play to get max
	 * total.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the time remaning, in hours.
	 */
	public static String ttmReq(String player) {
		player = replaceSpaces(player);
		String req = "type=ttm&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Get the 'Time to max rank' of the player, what rank they are in terms of
	 * closeness to maxing of all the players in the crystalmathlabs database.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the rank they are.
	 */
	public static String ttmRankReq(String player) {
		player = replaceSpaces(player);
		String req = "type=ttmrank&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Get the players name as it is stored in the database.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the players name as formatted in the database.
	 */
	public static String formatNameReq(String player) {
		player = replaceSpaces(player);
		String req = "type=formatname&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Search the names of players in the database.
	 * 
	 * @param player
	 *            the part of name to search for.
	 * @return how many players found, followed by a comma separated list of
	 *         player names containing the search string.
	 */
	public static String searchReq(String player) {
		player = replaceSpaces(player);
		String req = "type=search&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Get the record experience gains of a given player.
	 * 
	 * @param player
	 *            the player to make the request for.
	 * @return the records, in the format: <br/>
	 *         OverallDayRecord,OverallDayRecordTime,OverallWeekRecord,
	 *         OverallWeekRecordTime,OverallMonthRecord,OverallMonthRecordTime<br/>
	 * <br/>
	 *         AttackDayRecord,AttackDayRecordTime,AttackWeekRecord,
	 *         AttackWeekRecordTime,AttackMonthRecord,AttackMonthRecordTime<br/>
	 * <br/>
	 *         ...<br/>
	 */
	public static String recordsOfPlayerReq(String player) {
		player = replaceSpaces(player);
		String req = "type=recordsofplayer&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Updates the player and gets their latest track info.
	 * 
	 * @param player
	 *            the player to track.
	 * @param time
	 *            the time period (backwards, starting from the present) to get
	 *            data from, in seconds. time=0 means all time.
	 * @return the xp change in the given time period, in the format: <br/>
	 *         (Time of earliest cml datapoint)<br/>
	 *         (xplatest[0]-xpearliest[0]),(ranklatest
	 *         [0]-ranklatest[0]),(xpearliest[0])<br/>
	 *         (xplatest[1]-xpearliest[1]),(ranklatest[1]-ranklatest[1]),(
	 *         xpearliest[1])<br/>
	 *         ...<br/>
	 */
	public static String trackReq(String player, int time) {
		player = replaceSpaces(player);
		String req = "type=track&player=" + player + "&time=" + time;
		return sendRequest(req);
	}

	/**
	 * Get the datapoints for the given player.
	 * 
	 * @param player
	 *            the player to get the datapoints for.
	 * @param time
	 *            the time peroid (backwards, starting from the present) to get
	 *            data from, in seconds. time=0 means all time.
	 * @return the datapoints within the given time, in the format: <br/>
	 *         timeOfUpdate
	 *         skillxp[0],skillxp[1],...skillrank[0],skillrank[1],...
	 */
	public static String dataPointsReq(String player, int time) {
		player = replaceSpaces(player);
		String req = "type=datapoints&player=" + player + "&time=" + time;
		return sendRequest(req);
	}

	private static String sendRequest(String req) {
		try {
			URLConnection conn = new URL("http://crystalmathlabs.com/tracker/api.php?" + req).openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result += line + "\n";
			}
			return result.trim();

		} catch (MalformedURLException e) {
			return "Malformed URL";
		} catch (IOException e) {
			return "Network error";
		}
	}

	private static String replaceSpaces(String player) {
		player = player.replace(' ', '_');
		return player;
	}

	public static void main(String[] args) {
		System.out.println(trackReq("fOoT", SkillFunc.SECONDS_IN_WEEK));

	}
}

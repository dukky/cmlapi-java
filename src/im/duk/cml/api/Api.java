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
		player = sanitize(player);
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
		player = sanitize(player);
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
		player = sanitize(player);
		String req = "type=lastchange&player=" + player;
		return sendRequest(req);
	}

	/**
	 * Make a stats request, which gets the time of the most recent datapoint
	 * and the xp and rank for each skill.
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
		player = sanitize(player);
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
		player = sanitize(player);
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
		player = sanitize(player);
		String req = "type=ttmrank&player=" + player;
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

	private static String sanitize(String player) {
		player = player.replace(' ', '_');
		return player;
	}

	public static void main(String[] args) {
		System.out.println(ttmRankReq("foot"));

	}
}

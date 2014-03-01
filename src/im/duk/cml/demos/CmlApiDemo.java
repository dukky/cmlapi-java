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
package im.duk.cml.demos;

import im.duk.cml.api.Api;
import im.duk.cml.util.SkillFunc;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * Demo class showing example usage of the cml api.
 * @author Andreas
 *
 */
public class CmlApiDemo {
	public static void main(String[] args) {
		try {
			
			// Get the number of players online
			String players = Api.playersReq();
			String[] playersSplit = players.split(",");
			String time = SkillFunc.timeToShortString(System.currentTimeMillis()/1000 - Integer.parseInt(playersSplit[0]));
			System.out.println("As of " + time + " ago, there were " + playersSplit[1] + " players online on Oldschool Runescape!");
			
			// Get Jebrim's Agility exp and ehp
			String exp = Api.statsReq("Jebrim");
			String[] expSplit = exp.split("\n");
			String[] agilitySplit = expSplit[SkillFunc.AGILITY + 1].split(",");
			String agilXp = NumberFormat.getInstance().format(Integer.parseInt(agilitySplit[0]));
			System.out.println("Jebrim's current agility xp is " + agilXp + ".");

			// Get all the players in the database with names containing 'duk'
			String names = Api.searchReq("duk");
			String[] namesNoSplit = names.split(" ");
			System.out.println(namesNoSplit[0] + " players have names containing 'duk': " + namesNoSplit[1]);

		} catch (IOException e) {
			System.err.println("Problem with the network: " + e.getMessage());
		}
	}
}

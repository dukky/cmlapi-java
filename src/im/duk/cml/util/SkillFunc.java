/*******************************************************************************
 * Copyright 2013-2014 Andreas Holley, Oscar McCully
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
package im.duk.cml.util;

/**
 * Utilities class for cml/runescape functions. Mostly ported from the php
 * version used by the site.
 * 
 * @author Andreas Holley, Oscar McCully
 * 
 */
public class SkillFunc {
	
	public static final int SECONDS_IN_DAY = 86400;
	public static final int SECONDS_IN_WEEK = SECONDS_IN_DAY * 7;
	// 'months' on CML are always 31 days, as they are a rolling month
	public static final int SECONDS_IN_MONTH = SECONDS_IN_DAY * 31;
	
	// Skill constants
	public static final int OVERALL = 0;
	public static final int ATTACK = 1;
	public static final int DEFENCE = 2;
	public static final int STRENGTH = 3;
	public static final int HITPOINTS = 4;
	public static final int RANGED = 5;
	public static final int PRAYER = 6;
	public static final int MAGIC = 7;
	public static final int COOKING = 8;
	public static final int WOODCUTTING = 9;
	public static final int FLETCHING = 10;
	public static final int FISHING = 11;
	public static final int FIREMAKING = 12;
	public static final int CRAFTING = 13;
	public static final int SMITHING = 14;
	public static final int MINING = 15;
	public static final int HERBLORE = 16;
	public static final int AGILITY = 17;
	public static final int THEIVING = 18;
	public static final int SLAYER = 19;
	public static final int FARMING = 20;
	public static final int RUNECRAFTING = 21;
	public static final int HUNTER = 22;
	public static final int CONSTRUCTION = 23;
	public static final int EHP = 24;

	/**
	 * Get the virtual level of a skill, given the xp.
	 * 
	 * @param xp
	 *            the xp to calculate the virtual level from.
	 * @return the virtual level that you would be at given the xp.
	 */
	public static int getVirtualLevel(int xp) {
		double points = 0;
		int lvlXP = 0;
		for (int lvl = 1; lvl < 127; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			lvlXP = (int) Math.floor(points / 4);

			if (lvlXP > xp) {
				return lvl;
			}
		}
		return 126;
	}

	/**
	 * Get the level of a skill, given the xp (capped at 99).
	 * 
	 * @param xp
	 *            the xp to calculate the level from.
	 * @return the calculated level.
	 */
	public static int getLevel(int xp) {
		return Math.min(99, getVirtualLevel(xp));
	}

	/**
	 * Converts a duration (given in seconds) to a short string in the form
	 * '2d8h3m5s'.
	 * 
	 * @param time the duration to convert, in seconds.
	 * @return the string representing the time.
	 */
	public static String timeToShortString(long time) {
		if (time == 0)
			return "0s";

		int days = (int) (time / 86400);
		time %= 86400;
		int hours = (int) (time / 3600);
		time %= 3600;
		int minutes = (int) (time / 60);
		time %= 60;

		String ret = "";
		if (days > 0)
			ret = ret + days + "d";
		if (hours > 0)
			ret = ret + hours + "h";
		if (minutes > 0)
			ret = ret + minutes + "m";
		if (time > 0)
			ret = ret + time + "s";
		return ret;
	}

	/**
	 * Get the name of a skill, given its index.
	 * @param i the index of the skill, from 0 to 23
	 * @return The name of the skill.
	 */
	public static String getSkillName(int i) {
		switch (i) {
		case 0:
			return "Overall";
		case 1:
			return "Attack";
		case 2:
			return "Defence";
		case 3:
			return "Strength";
		case 4:
			return "Hitpoints";
		case 5:
			return "Ranged";
		case 6:
			return "Prayer";
		case 7:
			return "Magic";
		case 8:
			return "Cooking";
		case 9:
			return "Woodcutting";
		case 10:
			return "Fletching";
		case 11:
			return "Fishing";
		case 12:
			return "Firemaking";
		case 13:
			return "Crafting";
		case 14:
			return "Smithing";
		case 15:
			return "Mining";
		case 16:
			return "Herblore";
		case 17:
			return "Agility";
		case 18:
			return "Thieving";
		case 19:
			return "Slayer";
		case 20:
			return "Farming";
		case 21:
			return "Runecrafting";
		case 22:
			return "Hunter";
		case 23:
			return "Construction";
		}
		return null;
	}
}

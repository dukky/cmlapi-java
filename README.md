cmlapi-java
==========

Java bindings for the [CrystalMathLabs api](http://crystalmathlabs.com/tracker/api.php).

###Get it

Grab the latest [release](http://github.com/dukky/cmlapi-java/releases).

###Examples


```java
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
			
			// Get Jebrim's Agility exp
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
```
#####Outputs:

    As of 45m40s ago, there were 18785 players online on Oldschool Runescape!
    Jebrim's current agility xp is 37,922,672.
    15 players have names containing 'duk': dukescape,dukwo_o,dukefreedomm,dukesusuck,duke_of_edge,dukelaze,duke_salad,dukky,duke_1120,duke_bound,duke_finance,filthy_dukes,produktion,los_dukes,psydukc

cmlapi-java
==========

Java bindings for the [CrystalMathLabs api](http://crystalmathlabs.com/tracker/api.php).

###Get it

Grab the latest [release](http://github.com/dukky/cmlapi-java/releases).

###Examples

```java
import im.duk.cml.api.Api;
import im.duk.cml.util.SkillFunc;

public class CmlApiTest {
	public static void main(String[] args) {
		try {
			String players = Api.playersReq();
			String[] playersSplit = players.split(",");
			String time = SkillFunc.timeToShortString(System.currentTimeMillis()/1000 - Integer.parseInt(playersSplit[0]));
			System.out.println("As of " + time + " ago, there were " + playersSplit[1] + " players online on Oldschool Runescape!");
		} catch (IOException e) {
			System.err.println("Problem with the network: " + e.getMessage());
		}
	}
}
```
#####Outputs:

    As of 1m7s ago, there were 18996 players online on Oldschool Runescape!


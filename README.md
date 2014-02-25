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
		String players = Api.playersReq();
		
		// Output is the same as the web api.
		String[] playersSplit = players.split(",");

		// Make sure the request went through ok.
		if (playersSplit.length == 2) {
			System.out.println("There are currently " + playersSplit[1] + " players online on Oldschool Runescape!");
		} else {
			System.out.println(players);
		}
	}
}
```

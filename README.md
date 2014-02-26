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

			// Make sure the request went through ok
			if (playersSplit.length == 2) {
				System.out
						.println("There are currently " + playersSplit[1] + " players online on Oldschool Runescape!");
			} else {
				System.out.println(players);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
#####Outputs:

    There are currently 20432 players online on Oldschool Runescape!


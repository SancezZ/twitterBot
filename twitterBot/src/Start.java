import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;



/**
 * Start Klasse
 * 
 * 
 * @author Andy Klay, Sebastian Minke
 *
 */
public class Start {
	
	static Timer timer = new Timer();

	/**
	 * Startet die Anwendung mit den Parameter fuer den Twitter Account
	 * @param 
	 * args[0]= consumerKey 
	 * args[1]= consumerSecret 
	 * args[2]= accessToken 
	 * args[3]= accessTokenSecret
	 */
	public static void main(String[] args){	
		
		//abfragen ob richtige anzahl an Argumenten mitgeliefert wurde
		if(!(args.length>=4)){
			System.out.println("Bitte geben Sie alle TwitterApp-Schluessel mit!");
		}
		
		//Twitterbot erstellen
		TwitterBot bot= new TwitterBot(args[0], args[1], args[2], args[3]);		
		//anmelden des eigenen Accounts
		bot.login();		
		timer.schedule(bot, 0, 20000);	
		menue(bot);
	}
	
	
	/**
	 * Zeigt das Menue an
	 */
	private static void showMenue(){
		System.out.println("-----------------------------------------------------------------");
		System.out.println("-----------Drücke 1 zum Folgen eines Users-----------------------");
		System.out.println("-----------Drücke 2 zum erneuten anzeigen des Menues. -----------");
		System.out.println("-----------Drücke 3 zum Schließen des Programms.-----------------");
		System.out.println("-----------Die Auswahl mit Enter bestätigen.---------------------");
		System.out.println("-----------------------------------------------------------------");
	}
	
	
	/**
	 * Einlesen und auswerten der Benutzereingabe
	 * 
	 * @param bot
	 */
	private static void menue(TwitterBot bot){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		showMenue();
		while(true){
			try {
				int auswahl = Integer.parseInt(br.readLine());
				switch(auswahl){
				case 1: System.out.println("Geben Sie den Names des zu Folgenden Users ein(@Username)");
					    bot.followTwitterUser(br.readLine());
					    showMenue();
						break;
				case 2: showMenue();
						break;
				case 3: System.exit(0);
						break;
				default:
					System.out.println("Falsche Eingabe");
				}				
			} catch (NumberFormatException e) { 
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} 
		}
	}

}

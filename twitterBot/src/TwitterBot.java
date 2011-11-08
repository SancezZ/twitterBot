import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * 
 * Diese Klasse implementiert einen Bot fuer Twitter der andern Usern
 * folgen kann und diesen automatisch antworten kann.
 * 
 * @author Andy Klay, Sebastian Minke
 *
 */
public class TwitterBot extends TimerTask  {

	protected ArrayList<Long> ids = new ArrayList<Long>();

	/**
	 * twitterschluessel consumerKey
	 */
	private String consumerKey;
	
	/**
	 * twitterschluessel consumerSecret
	 */
	private String consumerSecret;
	
	/**
	 * twitterschluessel accessToken
	 */
	private String accessToken;
	
	/**
	 * twitterschluessel accessTokenSecret
	 */
	private String accessTokenSecret;
	
	/**
	 * Nachrichtenzaehler
	 */
	private long messageCounter=0;
	
	/**
	 * Standardantwort
	 */
	private static String STANDARD_ANSWER = " The person you have talked is temporarily not available!";

	/**
	 * Twitterinstanz
	 */
	private Twitter twitter;

	/**
	 * Konstruktor
	 * @param consumerKey
	 * @param consumerSecret
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public TwitterBot(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		this.consumerKey=consumerKey;
		this.consumerSecret=consumerSecret;
		this.accessToken=accessToken;
		this.accessTokenSecret=accessTokenSecret;
	}

	/**
	 * Authentifiziert die Applikation mit Twitter
	 */
	public void login() {
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true);
		configBuilder.setOAuthConsumerKey(consumerKey);
		configBuilder.setOAuthConsumerSecret(consumerSecret);
		configBuilder.setOAuthAccessToken(accessToken);
		configBuilder.setOAuthAccessTokenSecret(accessTokenSecret);

		TwitterFactory twitterFactory = new TwitterFactory(configBuilder.build());
		this.twitter = twitterFactory.getInstance();
	}

	public void addToFollow(String name) {

		this.followTwitterUser(name);
	}

	/**
	 * wird alle 20 Sekunden aufgerufen
	 */
	public void run() {
		this.checkToAnswer();
	}

	/**
	 * checke Kontakte ab und antworte falls nötig.
	 */
	public void checkToAnswer() {
		
		try {
			List<Status> stats;
			stats = twitter.getMentions();
				
			for (Status status : stats) {
				if (isNotYetAnswered(ids, status.getId())) {
					messageCounter++;
					 update("@" + status.getUser().getScreenName() + STANDARD_ANSWER + " ("+ messageCounter +")");
				}
			}
		} catch (TwitterException te) {
			System.err.println(te.getMessage());
		}
	}

	/**
	 * Setzt angegegeben User auf Folgen.
	 * 
	 * @param name 
	 * 			(Name des zu folgenden Users )
	 */
	public void followTwitterUser(String name) {

		try {
			twitter.createFriendship(name);
			System.out.println("Folge Erfolgreich (" + name + ")");
		} catch (TwitterException te) {
//			System.err.println("Konnte nicht folgen!");
		}
	}

	/**
	 * 
	 * Überprüft ob die auf eine Nachricht schonmal geantwortet wurde.
	 * @param ids
	 * @param id
	 * @return
	 * 		(true, wenn schon auf die Nachricht geantwortet wurde)
	 */
	public boolean isNotYetAnswered(List<Long> ids, long id) {

		if (ids.contains(id)) {
			return false;
		} else {
			ids.add(id);
			return true;
		}
	}

	
	/**
	 * setzt einen neuen Status des Twitteraccounts
	 * 
	 * @param message
	 *        (Nachricht die im geschrieben werden soll)
	 */	
	public void update(String message) {

		try {
			Status status = twitter.updateStatus(message);
			System.out.println("Erfolgreich updated zu (" + status.getText()+ ")");
		} catch (TwitterException te) {	
			System.err.println("Konnte nicht updaten!");
		}
	}
}
package StatTracker;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author petteri
 * @version 22.4.2019
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * k�ytett�v� viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
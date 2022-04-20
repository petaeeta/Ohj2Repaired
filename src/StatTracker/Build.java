package StatTracker;

import java.io.PrintStream;

/**
 * Luokka build-olioiden luomiselle.
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Build {
    private int bid;
    private String nimi;
    private String kuvaus;
    private static int seuraava_bid = 1;
    
    /**
     * Konstruktori oletusbuildin luomiselle.
     */
    public Build() {
        bid = rekisteroi();
        nimi = "Pavillion";
        kuvaus = "Build, jossa k‰ytet‰‰n jokaista hahmoa jolla on luonnollista armoria";
    }
    
    /**
     * Palauttaa kyseenomaisen buildin id:n
     * @return buildin id-numero
     */
    public int getBid() {
        return bid;
    }
    
    /**
     * Palauttaa seuraavan build-id:n, kun uusi buildi luodaan
     * @return uuden build-id:n
     * @example
     * <pre name="test">
     * Build build1 = new Build();
     * build1.getBid() === 1;
     * Build build2 = new Build();
     * int n1 = build1.getBid();
     * int n2 = build2.getBid();
     * n1 === n2-1;
     * </pre>
     */
    private int rekisteroi() {
        bid = seuraava_bid;
        seuraava_bid++;
        return bid;
        
    }
    /**
     * Tulostaa haluttuun tietovirtaan itsens‰
     * @param os tietovirta johon haluttu build tulostetaan
     */
    public void tulosta(PrintStream os) {
        os.println("Build-id: " + bid + "\n" + "Buildin nimi: " + nimi + "\n---------------------------------------------\n" + kuvaus + "\n---------------------------------------------\n");
    }

}

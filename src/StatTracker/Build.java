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
     * Get-metodi seuraavan luotavan buildin id-numerolle. Luotu l‰hinn‰ Junit-testeille ettei tarvitse pit‰‰ seuraava_bid attribuuttia public n‰kyvyydell‰.
     * @return Seuraavan luotavan buildin id(Bid).
     */
    public int getSeuraavaBid() {
        return seuraava_bid;
    }
    
    /**
     * Palauttaa seuraavan build-id:n, kun uusi buildi luodaan
     * @return uuden build-id:n
     * @example
     * <pre name="test">
     * Build build1 = new Build();
     * build1.getBid() === build1.getSeuraavaBid()-1;
     * Build build2 = new Build();
     * build2.getBid() === build2.getSeuraavaBid()-1;
     * build1.getBid() === build2.getBid()-1;
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

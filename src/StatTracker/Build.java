package StatTracker;

import java.io.PrintStream;

/**
 * Luokka build-olioiden luomiselle.
 * @author petteri
 * @version 29.3.2019
 * @version 22.4.2022
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
        if (bid % 2 == 0) {
            nimi = "SneakyGank";
            kuvaus = "Lorem Ipsum";
        }
        else {
            nimi = "Pavillion";
            kuvaus = "Build, jossa k�ytet��n jokaista hahmoa jolla on luonnollista armoria";
        }
    }
    
    /**
     * Palauttaa kyseenomaisen buildin id:n
     * @return buildin id-numero
     */
    public int getBid() {
        return bid;
    }
    
    
    /**
     * Get-metodi seuraavan luotavan buildin id-numerolle. Luotu l�hinn� Junit-testeille ettei tarvitse pit�� seuraava_bid attribuuttia public n�kyvyydell�.
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
     * build1.getNimi() === "Pavillion";
     * build2.getNimi() === "SneakyGank";
     * build1.getKuvaus() === "Build, jossa k�ytet��n jokaista hahmoa jolla on luonnollista armoria";
     * build2.getKuvaus() === "Lorem Ipsum"
     * </pre>
     */
    private int rekisteroi() {
        bid = seuraava_bid;
        seuraava_bid++;
        return bid;
        
    }
    /**
     * Tulostaa haluttuun tietovirtaan itsens�
     * @param ps tietovirta johon haluttu build tulostetaan
     */
    public void tulosta(PrintStream ps) {
        ps.println("Build-id: " + bid + "\n" + "Buildin nimi: " + nimi + "\n---------------------------------------------\n" + kuvaus + "\n---------------------------------------------\n");
    }

    /**
     * Get-metodi buildin nimelle
     * @return buildin nimen
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Get-metodi buildin kuvaukselle
     * @return buildin kuvauksen
     */
    public String getKuvaus() {
        return kuvaus;
    }

}

package StatTracker;

import java.io.PrintStream;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmo {
    private int hid;
    private static int seuraava_hid = 1;
    private String nimi;
    private int voitot;
    private int haviot;
    private int tapot;
    private int kuolemat;
    
    
    
    /**
     * Luo oletushahmon ilman parametreja. Tehty vaihetta 5 varten.
     */
    public Hahmo() {
    hid = rekisteroi();
    nimi = "Widowmaker";
    tapot = 58;
    kuolemat = 12;
    voitot = 14;
    haviot = 14;
        
    }
    
    /**
     * Antaa hahmolle oikean id-numeron
     * @return hahmon uusi id
     * @example tämän ohjelman versiossa itse rekisteröinti tapahtuu jo hahmon luonnissa
     * <pre name="test">
     * Hahmo hahmo1 = new Hahmo();
     * hahmo1.getTunnusNro() === 1;
     * Hahmo hahmo2 = new Hahmo();
     * int n1 = hahmo1.getTunnusNro();
     * int n2 = hahmo2.getTunnusNro();
     * n1 === n2-1;
     * 
     * </pre>
     */
    public int rekisteroi() {
        hid = seuraava_hid;
        seuraava_hid++;
        return hid;
    }

    /**
     * Get-metodi olion nimen saamiseksi
     * @return Hahmon nimen
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Tulostetaan hahmon tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream os) {
        os.println(hid + "\n" + nimi + "\n" + tapot + "\n" + kuolemat + "\n" + voitot + "\n" + haviot + "\n");
        
    }
    

    /**
     * Get-metodi hahmon tunnusnumeron saamiseksi
     * @return hahmon tunnusnumeron
     */
    public int getTunnusNro() {
        return hid;
    }
    
}

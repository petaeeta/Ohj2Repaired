package StatTracker;

import java.io.PrintStream;
import java.util.Random;

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
     * Luo oletushahmon ilman parametreja randomisoimalla hahmon sisällön. Tehty testaamista varten.
     */
    public Hahmo() {
        Random rand = new Random();
        String[] hahmoLista = {"Sigma", "Roadhog", "Junkrat", "Widowmaker", "Ashe", "Bastion", "Cassidy"};
        nimi = hahmoLista[rand.nextInt(hahmoLista.length)];
        tapot = rand.nextInt(150);
        kuolemat = rand.nextInt(150);
        voitot = rand.nextInt(150);
        haviot = rand.nextInt(150);
        hid = rekisteroi();
    }
    
    /**
     * Luo hahmon annetuilla parametreilla
     * @param nimi Hahmon nimi
     * @param voitot Hahmon voitot
     * @param haviot Hahmon häviöt
     * @param tapot Hahmon tapot
     * @param kuolemat Hahmon kuolemat
     */
    public Hahmo(String nimi, int voitot, int haviot, int tapot, int kuolemat) {
        this.nimi = nimi;
        this.voitot = voitot;
        this.haviot = haviot;
        this.tapot = tapot;
        this.kuolemat = kuolemat;
        hid = rekisteroi();
        
    }
    
    /**
     * Antaa hahmolle oikean id-numeron
     * @return hahmon uusi id
     * @example tämän ohjelman versiossa itse rekisteröinti tapahtuu jo hahmon luonnissa
     * <pre name="test">
     * Hahmo hahmo1 = new Hahmo();
     * hahmo1.getHid() === hahmo1.getSeuraavaHid()-1;
     * Hahmo hahmo2 = new Hahmo();
     * hahmo2.getHid() === hahmo2.getSeuraavaHid()-1;
     * hahmo1.getHid() === hahmo2.getHid()-1;
     * 
     * </pre>
     */
    private int rekisteroi() {
        this.hid = seuraava_hid;
        seuraava_hid++;
        return this.hid;
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
    public int getHid() {
        return hid;
    }
    
    /**
     * Get-metodi seuraavan luotavan hahmon id-numerolle. Luotu lähinnä Junit-testeille ettei tarvitse pitää seuraava_hid attribuuttia public näkyvyydellä.
     * @return Seuraavan luotavan hahmon id(Hid).
     */
    public int getSeuraavaHid() {
        return seuraava_hid;
    }
    
}

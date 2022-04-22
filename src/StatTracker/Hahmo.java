package StatTracker;

import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmo {
    private int hid;
    private static int seuraava_hid = 1;
    private String nimi = "";
    private int voitot;
    private int haviot;
    private int tapot;
    private int kuolemat;  
    
    /**
     * Luo oletushahmon ilman parametreja randomisoimalla hahmon sis�ll�n. Tehty testaamista varten.
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
     * Konstruktori tyhj�n Hahmon luomiseen, joka voidaan halutessa lis�t� rekisteriin.
     * Tehty p��asiassa kuljettamaan hahmo-mallista dataa rekister�im�tt� hahmoa.
     * @param lisataanRekisteriin lis�t��nk� hahmo rekisteriin
     */
    public Hahmo(Boolean lisataanRekisteriin) {
        if(lisataanRekisteriin) hid = rekisteroi();
    }
    
    /**
     * Luo hahmon annetuilla parametreilla
     * @param nimi Hahmon nimi
     * @param voitot Hahmon voitot
     * @param haviot Hahmon h�vi�t
     * @param tapot Hahmon tapot
     * @param kuolemat Hahmon kuolemat
     * @example
     * <pre name="test">
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmo1.getNimi() === "Kalle";
     * hahmo1.getVoitot() === 3;
     * hahmo1.getHaviot() === 5;
     * hahmo1.getTapot() === 12;
     * hahmo1.getKuolemat() === 2;
     * hahmo2.getNimi() === "Ville";
     * hahmo2.getVoitot() === 1;
     * hahmo2.getHaviot() === 33;
     * hahmo2.getTapot() === 74;
     * hahmo2.getKuolemat() === 100;
     * </pre>
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
     * @example t�m�n ohjelman versiossa itse rekister�inti tapahtuu jo hahmon luonnissa
     * <pre name="test">
     * Hahmo hahmo1 = new Hahmo();
     * hahmo1.getHid() === hahmo1.getSeuraavaHid()-1;
     * Hahmo hahmo2 = new Hahmo();
     * hahmo2.getHid() === hahmo2.getSeuraavaHid()-1;
     * hahmo1.getHid() === hahmo2.getHid()-1;
     * 
     * </pre>
     */
    public int rekisteroi() {
        this.hid = seuraava_hid;
        seuraava_hid++;
        return this.hid;
    }
    
    /**
     * Palauttaa hahmon numeeriset tilastot yhten� listana
     * @return int-lista jossa hahmon statistiikat j�rjestyksess� {voitot, haviot, tapot, kuolemat}
     * @example
     * <pre name="test">
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmo1.getStats()[0] === 3;
     * hahmo1.getStats()[1] === 5;
     * hahmo1.getStats()[2] === 12;
     * hahmo1.getStats()[3] === 2;
     * hahmo2.getStats()[0] === 1;
     * hahmo2.getStats()[1] === 33;
     * hahmo2.getStats()[2] === 74;
     * hahmo2.getStats()[3] === 100;
     * </pre>
     */
    public int[] getStats() {
        return new int[]{voitot, haviot, tapot, kuolemat};
    }
    
    /**
     * Get-metodi olion nimen saamiselle
     * @return voitot
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Get-metodi voitoille
     * @return voitot
     */
    public int getVoitot() {
        return voitot;
    }
    
    /**
     * Get-metodi h�vi�ille
     * @return h�vi�t
     */
    public int getHaviot() {
        return haviot;
    }
    
    /**
     * Get-metodi tapoille
     * @return tapot
     */
    public int getTapot() {
        return tapot;
    }
    
    /**
     * Get-metodi kuolemille
     * @return kuolemat
     */
    public int getKuolemat() {
        return kuolemat;
    }
    
    /**
     * @param uusi p�ivitetty luku
     */
    public void setVoitot(int uusi) {
        voitot = uusi;
    }
    
    /**
     * @param uusi p�ivitetty luku
     */
    public void setHaviot(int uusi) {
       haviot = uusi;
    }

    
    /**
     * @param uusi p�ivitetty luku
     */
    public void setTapot(int uusi) {
        tapot = uusi;
    }
    
    
    /**
     * @param uusi p�ivitetty luku
     */
    public void setKuolemat(int uusi) {
        kuolemat = uusi;
    }

    /**
     * Tulostetaan hahmon tiedot
     * @param ps tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream ps) {
        ps.println(hid + "\n" + nimi + "\n" + tapot + "\n" + kuolemat + "\n" + voitot + "\n" + haviot + "\n");
    }
    

    /**
     * Get-metodi hahmon tunnusnumeron saamiseksi
     * @return hahmon tunnusnumeron
     */
    public int getHid() {
        return hid;
    }
    
    /**
     * Set-metodi hahmon tunnusnumeron muuttamiseksi
     * @param uusi arvo joka hahmo id:ksi asetetaan
     */
    public void setHid(int uusi) {
        hid = uusi;
        if (hid >= seuraava_hid) seuraava_hid = hid + 1;
    }
    
    
    /**
     * Get-metodi seuraavan luotavan hahmon id-numerolle. Luotu l�hinn� Junit-testeille ettei tarvitse pit�� seuraava_hid attribuuttia public n�kyvyydell�.
     * @return Seuraavan luotavan hahmon id(Hid).
     */
    public int getSeuraavaHid() {
        return seuraava_hid;
    }
    
    /**
     * Lukee tiedostosta rivin asettaen sielt� luetut arvot oikeille paikoillensa
     * @param rivi jota luetaan
     * @example
     * <pre name="test">
     * Hahmo hahmo = new Hahmo(false);
     * hahmo.parse("5  | Widowmaker         |  420");
     * hahmo.getHid() === 5;
     * hahmo.toString().startsWith("5|Widowmaker|420") === true;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setHid(Mjonot.erota(sb, '|', getHid()));
        nimi = Mjonot.erota(sb, '|', nimi);
        voitot = Mjonot.erota(sb, '|', voitot);
        haviot = Mjonot.erota(sb, '|', haviot);
        tapot = Mjonot.erota(sb, '|', tapot);
        kuolemat = Mjonot.erota(sb, '|', kuolemat);
        
    }
    
    
    @Override
    public String toString() {
        return "" + hid + "|" + nimi + "|" + voitot + "|" + haviot + "|" + tapot + "|" + kuolemat;
    }    
}

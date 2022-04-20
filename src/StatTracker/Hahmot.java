package StatTracker;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmot {
    private int lkm;
    private int maxlkm = 8; // k‰tet‰‰n vasta kun tietorakennetta aletaan kasvattamaan rajatta
    private Hahmo[] hahmot = new Hahmo[8];
    
    /**
     * Hahmot luokan konstruktori, joka luo taulukon, jonne voi
     * laittaa hahmo-olioita. Oletuksena luo taulukon kahdeksalle
     * alkiolle
     */
    public Hahmot() {
        
    }
    
    
    /**
     * Metodi hahmojen lis‰‰miseksi taulukkoon 
     * @param hahmo Hahmo, joka taulukkoon lis‰t‰‰n.
     * @throws SailoException jos tietorakenne on t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo(), hahmo2 = new Hahmo();
     * hahmot.getLkm() === 0;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 1;
     * hahmot.LisaaHahmo(hahmo2); hahmot.getLkm() === 2;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 3;
     * hahmot.anna(0) === hahmo1;
     * hahmot.anna(1) === hahmo2;
     * hahmot.anna(2) === hahmo1;
     * hahmot.anna(1) == hahmo1 === false;
     * hahmot.anna(1) == hahmo2 === true;
     * hahmot.anna(3) === hahmo1; #THROWS IndexOutOfBoundsException
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 4;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 5;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 6;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 7;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 8;
     * hahmot.LisaaHahmo(hahmo1); #THROWS SailoException
     * </pre>
     */
    public void LisaaHahmo(Hahmo hahmo) throws SailoException {
        if (lkm >= hahmot.length) throw new SailoException("Liikaa alkioita");
        hahmot[lkm] = hahmo;
        lkm++;
    }
    
    
    @Override
    public String toString(){
        StringBuilder hahmot_rivit = new StringBuilder();
        for (Hahmo i : hahmot) {
            if (i == null) break;
            hahmot_rivit.append(i.getNimi() + "\n");
        }
        
        return hahmot_rivit.toString();
    }

    /**
     * palauttaa hahmojen lukum‰‰r‰n
     * @return hahmojen lukum‰‰r‰
     */
    public int getLkm() {
        return lkm;
    }


    /**
     * Palauttaa viitteen i:teen hahmoon.
     * @param i monennenko hahmon viite halutaan
     * @return viite hahmoon, jonka indeksi on i
     * @throws IndexOutOfBoundsException mik‰li i ei ole sallitulla alueella
     */
    public Hahmo anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return hahmot[i];
    }
    
}

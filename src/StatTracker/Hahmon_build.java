package StatTracker;

/**
 * Luokka, johon tallennetaan alkioita merkkaamaan, mitä buildeja mikäkin hahmo käyttää
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmon_build {

    private int hid;
    private int bid;
    
    /**
     * Konstruktori hahmon buildien tallentamiseksi
     * @param hid hahmon id
     * @param bid buildin id
     * @example
     * <pre name="test">
     * Hahmon_build hb1 = new Hahmon_build(1, 2);
     * Hahmon_build hb2 = new Hahmon_build(5, 13);
     * 
     * hb1 == hb2 === false;
     * hb1.getHid() === 1;
     * hb1.getBid() === 2; 
     * hb2.getHid() === 5;
     * hb2.getBid() === 13; 
     * 
     * </pre>
     */
    public Hahmon_build(int hid, int bid) {
        this.hid = hid;
        this.bid = bid;
    }
    
    /**
     * 
     * @return palauttaa hahmon id:n
     */
    public int getHid() {
        return hid;
    }
    
    /**
     * 
     * @return palauttaa build id:n
     */
    public int getBid() {
        return bid;
    }
    
    /**
     * @param verrattava jota verrataan
     * @return löytyikö elementti
     */
    public boolean equals(Hahmon_build verrattava) {
        return (verrattava.getHid() == this.hid && verrattava.getBid() == this.bid);
    }
}

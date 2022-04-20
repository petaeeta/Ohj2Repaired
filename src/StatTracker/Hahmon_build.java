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
}

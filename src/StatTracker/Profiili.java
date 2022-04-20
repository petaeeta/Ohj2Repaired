package StatTracker;

import java.io.PrintStream;
import java.util.List;

/**
 * Profiili-luokka, joka yhdistelee buildien ja hahmojen tietoja.
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Profiili {
    private final Hahmot hahmot = new Hahmot();
    private final Hahmobuildit hahmobuildit = new Hahmobuildit();
    private final Buildit buildit = new Buildit();
    
    /**
     * Poistaa Hahmotaulukosta ja buildeista ne, jotka on merkitty id:ll‰ nro.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako hahmoa poistettiin
     */
    public int poista(int nro) {
        return 0;
        //TODO: Oikeasti poistaminen
    }
    
    /**
     * Palauttaa hahmojen m‰‰r‰n
     * @return hahmojen m‰‰r‰n
     */
    public int getHahmoja() {
        return hahmot.getLkm();
    }
    
    /**
     * Palauttaa i:n hahmon
     * @param i monesko hahmo palautetaan
     * @return viite i:nteen hahmoon
     * @throws IndexOutOfBoundsException jos ei ole i:tt‰ hahmoa
     */
    public Hahmo annaHahmo(int i) throws IndexOutOfBoundsException{
        return hahmot.anna(i);
    }

    /**
     * Lis‰‰ hahmon kyseenomaisen profiilin taulukkoon
     * @param hahmo joka lis‰t‰‰n
     * @throws SailoException jos lis‰yst‰ ei voida tehd‰
     */
    public void LisaaHahmo(Hahmo hahmo) throws SailoException {
        hahmot.LisaaHahmo(hahmo);
        
    }
    
    /**
     * Lis‰‰ buildin ja hahmon v‰lisen yhteyden
     * @param hid hahmon id
     * @param bid buildin id
     */
    public void LisaaHahmolleBuild(int hid, int bid) {
        hahmobuildit.LisaaHahmolleBuild(hid, bid);
    }
    
    /**
     * Palauttaa listan build-id:t‰, jotka on liitetty hahmoon
     * @param hid hahmon id johon yhdistetyt buildit palautetaan
     * @return listan hahmo-id:t‰
     */
    public List<Integer> annaHahmonBuildit(int hid) {
        return hahmobuildit.annaHahmonBuildit(hid);
    }

    /**
     * tulostaa halutut buildit halutulle tietovirralle. Tehty l‰hinn‰ vaihetta 5 varten, todellinen
     * toteutus voi viel‰ muuttua.
     * @param halutut lista build-id:st‰, jotka tulostetaan
     * @param os tietovirta johon tulostetaan
     */
    public void tulostaBuildit(List<Integer> halutut, PrintStream os) {
        buildit.tulostaBuildit(halutut, os);
    }

    
    /**
     * Lis‰‰ buildin listaan
     * @param build joka listaan lis‰t‰‰n
     * @throws SailoException poikkeus, jonka viesti on m‰‰ritelty alemmalla tasolla
     */
    public void LisaaBuild(Build build) throws SailoException {
        buildit.lisaaBuild(build);
    }
    
    /**
     * Metodi tulostaa tallennettujen hahmojen overall-tilastot halutuille paikoilleen
     */
    public void tulostaOverallTilastot() {
        //TODO: Overall tilastojen tulostus n‰yttˆˆn, toteutetaan kun tietoa oikeasti tallennetaan tiedostoihin.
    }



}

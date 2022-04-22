package StatTracker;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

/**
 * Profiili-luokka, joka yhdistelee buildien ja hahmojen tietoja.
 * @author petteri
 * @version 29.3.2019
 * @version 21.4.2022
 *
 */
public class Profiili {
    private String profiiliNimi = "Profiili";
    private Hahmot hahmot = new Hahmot();
    private Hahmobuildit hahmobuildit = new Hahmobuildit();
    private Buildit buildit = new Buildit();
    
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
    public int getHahmoMaara() {
        return hahmot.getLkm();
    }
    
    
    /**
     * Palauttaa buildien m‰‰r‰n
     * @return bulidien m‰‰r‰n
     */
    public int getBuildMaara() {
        return buildit.getLkm();
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
     * Antaa listasta i:nnen buildin, joka joko on tai ei ole olemassa
     * @param i monesko buildi palautetaan
     * @return viite i:nteen buildiin
     * @throws IndexOutOfBoundsException jos ei ole 
     */
    public Build annaBuild(int i) throws IndexOutOfBoundsException{
        return buildit.anna(i);
    }
    
    
    /**
     * Palauttaa id-numeroa vastaan viitteen buildiin, joka etsit‰‰n taulukosta
     * @param i id-numero jota vastaan build annetaan
     * @return viite buildiin, null jos ei olemassa
     * @throws SailoException jos ei lˆydy
     */
    public Build annaBuildViite(int i) throws SailoException {
        return buildit.annaBuild(i);
    }
    
    /**
     * @param i id-numero jota vastaavan buildin nimi palautetaan
     * @return build joka vastasi Id-numeroa
     */
    public String annaBuildNimi(int i) {
        return buildit.annaBuildNimi(i);
    }

    /**
     * Lis‰‰ hahmon kyseenomaisen profiilin taulukkoon
     * @param hahmo joka lis‰t‰‰n
     * @throws SailoException jos lis‰yst‰ ei voida tehd‰
     */
    public void LisaaHahmo(Hahmo hahmo) throws SailoException {
        hahmot.lisaaHahmo(hahmo);
        
    }
    
    /**
     * Lis‰‰ buildin ja hahmon v‰lisen yhteyden
     * @param hid hahmon id
     * @param bid buildin id
     */
    public void LisaaHahmolleBuild(int hid, int bid) {
        hahmobuildit.lisaaHahmolleBuild(hid, bid);
    }
    
    /**
     * Lis‰‰ buildin ja hahmon v‰lisen yhteyden
     * @param hb Lis‰tt‰v‰ Hahmon_build
     */
    public void LisaaHahmolleBuild(Hahmon_build hb) {
        hahmobuildit.lisaaHahmolleBuild(hb);
        
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
     * Asettaa k‰ytett‰v‰n profiilin
     * @param nimi profiilin nimi
     */
    public void setProfiili(String nimi) {
        File tied = new File("Profiilit\\" + nimi);
        tied.mkdirs();
        String hakemisto = "";
        if (!nimi.isEmpty()) hakemisto = "Profiilit\\" + nimi;
        setProfiiliNimi(nimi);
        hahmot.setProfiiliNimi(hakemisto);
        buildit.setProfiiliNimi(hakemisto);
        hahmobuildit.setProfiiliNimi(hakemisto);
    }
    
    /**
     * Metodi v‰litt‰‰ tallennettujen hahmojen yhteenlasketut tilastot halutuille paikoilleen
     * @return paketin jossa on kaikkien hahmojen yhteenlasketut tilastot. Palautetaan Hahmo-oliona sill‰ Hahmo-oliosta lˆytyy kaikki tarvittavat attribuutit, oikeata hahmoa ei kuitenkaan luoda.
     */
    /*
     * public Hahmo getOverallTilastot() { if (hahmot.getLkm() < 1) return new
     * Hahmo(false); return hahmot.getOverallTilastot(); }
     */
    
    /**
     * Metodi v‰litt‰‰ tallennettujen hahmojen yhteenlasketut tilastot halutuille paikoilleen
     * @return paketin jossa on kaikkien hahmojen yhteenlasketut tilastot. Palautetaan Hahmo-oliona sill‰ Hahmo-oliosta lˆytyy kaikki tarvittavat attribuutit, oikeata hahmoa ei kuitenkaan luoda.
     */
    public int[] getOverallTilastot() {
        if (hahmot.getLkm() < 1) return new int[4];
        return hahmot.getOverallTilastot();
    }
    
    /**
     * Tallentaa t‰m‰nhetkiset tiedot tiedostoon
     * @param tied profiilinimi jonne tallennetaan
     * @throws SailoException jos ei onnistu
     */
    public void tallenna(String tied) throws SailoException {
        setProfiili(tied);
        hahmot.tallenna();
        buildit.tallenna();
        hahmobuildit.tallenna();
    }
    
    /**
     * Lukee tiedot oletusprofiilista
     * @throws SailoException jos ei lˆydy
     */
    public void lueTiedostosta() throws SailoException{
            lueTiedostosta(getProfiiliNimi());
    }
    
    /**
     * Asettaa profiilin nimen
     * @param uusi profiilinimi
     */
    public void setProfiiliNimi(String uusi) {
        profiiliNimi = uusi;
    }
    
    
    /**
     * Palauttaa profiilinimen
     * @return profiilinimen
     */
    public String getProfiiliNimi() {
        return profiiliNimi;
    }

    /**
     * Lukee tiedot profiilista jonka nimi annetaan parametrina
     * @param nimi profiilin nimi
     * @throws SailoException jos ei lˆydy
     */
    public void lueTiedostosta(String nimi) throws SailoException{
        hahmot = new Hahmot();
        buildit = new Buildit();
        hahmobuildit = new Hahmobuildit();
        
        setProfiili(nimi);
        hahmot.lueTiedostosta();
        buildit.lueTiedostosta();
        hahmobuildit.lueTiedostosta();
    }
    
    /**
     * @param args ei k‰ytˆss‰
     * @throws SailoException jos ei lˆydy
     */
    public static void main(String[] args) throws SailoException {
        Profiili profiili = new Profiili();
        
        profiili.lueTiedostosta("Esimerkkiprofiili");
        profiili.tallenna("Profiili2");
        
    }



}

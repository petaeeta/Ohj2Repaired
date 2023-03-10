package StatTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Buildit {
   private Build[] buildit = new Build[10];
   private int maxlkm = 10;
   private int lkm;
   private String profiiliNimi = "profiili";
   private String tiedostoNimi = "buildit";
   private boolean muutettu = false;
   
   /**
    * Metodi buildien lisäämiselle listaan
    * @param build joka listaan lisätään
    * @example
    * <pre name="test">
    * #THROWS SailoException
    * Buildit buildit = new Buildit();
    * Build build1 = new Build();
    * Build build2 = new Build();
    * buildit.getLkm() === 0;
    * buildit.lisaaBuild(build1); buildit.getLkm() === 1;
    * buildit.lisaaBuild(build2); buildit.getLkm() === 2;
    * buildit.lisaaBuild(build1); buildit.getLkm() === 3;
    * buildit.anna(0) === build1;
    * buildit.anna(1) === build2;
    * buildit.anna(2) === build1;
    * buildit.anna(1) == build1 === false;
    * buildit.anna(0) == build1 === true;
    * buildit.anna(3) === build1; #THROWS IndexOutOfBoundsException
    * buildit.lisaaBuild(build1); buildit.getLkm() === 4;
    * buildit.lisaaBuild(build1); buildit.getLkm() === 5;
    * </pre>
    */
   public void lisaaBuild(Build build) {
       if (build.getBid() < 0) build.rekisteroi();
       if (lkm >= buildit.length) {
           maxlkm += 20;
           Build[] uusi = new Build[maxlkm];
           for(int i = 0; i < buildit.length; i++) {
               uusi[i] = buildit[i];
           }
           buildit = uusi;
           muutettu = true;
       }
       for (int i = 0; i < buildit.length; i++) {
           if (buildit[i] == null) {
               buildit[i] = build;
               lkm++;
               muutettu = true;
               return;
           }
       }
   }
   
   /**
    * Metodi joka antaa viitteen buildiin haluttua indeksiä vastaan taulukossa
    * @param i buildin indeksi taulukossa
    * @return viitteen buildiin
    * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
    */
   public Build anna(int i) throws IndexOutOfBoundsException{
       if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
       return buildit[i];
   }
   
   /**
    * palauttaa viiteen buildiin joka vastaa parametrina annettua id:tä
    * @param i buildin id jota vastaan etsitään build
    * @return viite buildiin
    * @throws SailoException jos ei löydy
    */
      public Build annaBuild(int i) throws SailoException{
          Build vastaus = null;
          for(Build b : buildit) {
              if (b == null) continue;
              if (b.getBid() == i) vastaus = b;
          }
          // Mikäli on oikein koodattu järjestelmä, näin ei pitäisi edes tapahtua. Printataan error jokatapauksessa.
          if (vastaus == null) throw new SailoException("Etsittävää buildia id:llä " + i + " ei löytynyt");
          return vastaus;
      }
      
     /**
     * palauttaa kaikki buildit
     * @return kaikki buildit
     */
      public List<Build> annaBuildit() {
          List <Build> vastaus = new ArrayList<Build>();
          for(Build build : buildit) {
              if (build != null) vastaus.add(build);
          }
          return vastaus;
      }
   
 /**
 * Etsii buildin id:n perusteella, ja palauttaa buildin nimen. Tehty pääasiassa vaiheen 5 testaamista varten
 * , mutta jätetään jos jostain syystä halutaankin vain nimi.
 * @param i buildin id jota vastaan etsitään build
 * @return buildin nimi
 */
   public String annaBuildNimi(int i){
       String vastaus = "";
       for(Build b : buildit) {
           if (b == null) continue;
           if (b.getBid() == i) vastaus = b.getNimi();
       }
       return vastaus;
   }
   
   /**
    * Palauttaa profiilin nimen
    * @return profiilin nimen
     */
   public String getProfiiliNimi() {
       return profiiliNimi;
   }
   
   /**
    * Asettaa profiilin nimen
    * @param uusi uusi nimi
     */
   public void setProfiiliNimi(String uusi) {
       profiiliNimi = uusi;
   }
   
   /**
    * Lukee tiedoston jo asetetulla profiilinimellä
    * @throws SailoException jos ei löydy
     */
   public void lueTiedostosta() throws SailoException {
       lueTiedostosta(getProfiiliNimi());
   }
   
    /**
     * Lukee tiedoston halutusta paikasta
     * @param hakemisto josta tiedosto luetaan
     * @throws SailoException jos luku ei onnistu
     */
   public void lueTiedostosta(String hakemisto) throws SailoException {
       String tiedostonNimi = hakemisto + "/" + tiedostoNimi +  ".dat";
       File ftied = new File(tiedostonNimi);
       try {
           try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
               while (fi.hasNext()) {
                   String s = "";
                   s = fi.nextLine();
                   Build build = new Build(false);
                   build.parse(s);
                   lisaaBuild(build);
               }
           }
       } catch (FileNotFoundException e) {
           throw new SailoException("Tällä profiilinimellä ei ole aikaisempia tiedostoja, tai ne ovat tuhoutuneet. Ne luodaan kun tallennat tässä sessiossa.");
           
       }
       /*
          * catch (IOException e) { throw new
          * SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); }
          */
   }
   
    /**
     * Etsii korvattavan buildin. Jos ei löydy, lisää buildin.
    * @param build uusi lisättävä tai korvattava build
    */
   public void korvaaTaiLisaa(Build build) {
       int id = build.getBid();
       for (int i = 0; i < buildit.length; i++) {
           if (buildit[i].getBid() == id) {
               buildit[i] = build;
               muutettu = true;
               return;
           }
       }
       lisaaBuild(build);
       muutettu = true;
   }
   
   /**
    * Poistaa halutun buildin id-numeroa vastaan
    * @param bid buildin id joka poistetaan
    * @return 1 jos onnistui, 0 jos ei
    */
   public int poista(int bid) {
       for (int i = 0; i < buildit.length; i++) {
           if (buildit[i] != null && buildit[i].getBid() == bid) {
               buildit[i] = null;
               lkm--;
               muutettu = true;
               return 1;
           }
       }
       return 0;
   }
   
   /**
    * Tallentaa tiedot jo asetettuun tiedostonimeen
    * @throws SailoException jos tallennus epäonnistuu
    */
   public void tallenna() throws SailoException {
       tallenna(getProfiiliNimi());
   }
   
   /**
    * Tallentaa tämänhetkiset tiedot tiedostoon
    * @param tiednimi tallennettavan tiedoston nimi
    * @throws SailoException jos talletus epäonnistuu
    */
   public void tallenna(String tiednimi) throws SailoException {
       File tied = new File(tiednimi + "/" + tiedostoNimi + ".dat");
       try {
           try (PrintStream fo = new PrintStream(new FileOutputStream(tied, false))) {
               for (int i = 0; i < buildit.length; i++) {
                   if (buildit[i] != null)fo.println(buildit[i]);
               }
           }
       } catch(Exception e) {
           throw new SailoException("Tiedosto " + tied.getAbsolutePath() + " ei aukea");
       }
       muutettu = false;
   }
   
   /**
    * get-metodi, jolla saadaan buildit-taulukon alkioiden lukumäärä 
    * @return tällähetkellä olevien alkioiden lukumäärän
    */
   public int getLkm() {
       return lkm;
   }
   
   /**
    * Palauttaa arvon perustuen siihen onko tallennuskriittisiä tiedostoja muutettu 
    * @return muutettu-arvon
   */
   public boolean getMuutettu() {
       return muutettu;
   }

   /**
    * tulostaa listan id:tä vastaavat buildit, tehty vaihetta 5 varten
    * @param halutut lista buildien id-numeroista, jotka tulostetaan
    * @param os tietovirta johon tulostetaan
    */
   public void tulostaBuildit(List<Integer> halutut, PrintStream os) {
       for (int i = 0; i < lkm; i++) {
           Build osoitin = buildit[i];
           for (Integer x : halutut) {
               if (x == osoitin.getBid()) osoitin.tulosta(os);
           }
       }
   }   
}

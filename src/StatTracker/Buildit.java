package StatTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Buildit {
   private Build[] taulukko = new Build[10];
   private int maxlkm = 10; //k‰ytet‰‰n vasta kun tietorakennetta kasvatetaan rajatta
   private int lkm;
   private String profiiliNimi = "profiili";
   private String tiedostoNimi = "buildit";
   
   /**
    * Konstruktori oletusbuilditaulukon luomiselle
    */
   public Buildit() {
       
   }
   
   /**
    * Metodi buildien lis‰‰miselle listaan
    * @param build joka listaan lis‰t‰‰n
    * @throws SailoException jos taulukossa on liikaa alkioita
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
   public void lisaaBuild(Build build) throws SailoException {
       if (lkm >= taulukko.length) {
           maxlkm += 20;
           Build[] uusi = new Build[maxlkm];
           for(int i = 0; i < taulukko.length; i++) {
               uusi[i] = taulukko[i];
           }
           taulukko = uusi;
       }
       taulukko[lkm] = build;
       lkm++;
   }
   
   /**
    * Metodi joka antaa viitteen buildiin haluttua indeksi‰ vastaan taulukossa
    * @param i buildin indeksi taulukossa
    * @return viitteen buildiin
    * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
    */
   public Build anna(int i) throws IndexOutOfBoundsException{
       if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
       return taulukko[i];
   }
   
   /**
    * palauttaa viiteen buildiin joka vastaa parametrina annettua id:t‰
    * @param i buildin id jota vastaan etsit‰‰n build
    * @return viite buildiin
    * @throws SailoException jos ei lˆydy
    */
      public Build annaBuild(int i) throws SailoException{
          Build vastaus = null;
          for(Build b : taulukko) {
              if (b == null) continue;
              if (b.getBid() == i) vastaus = b;
          }
          // Mik‰li on oikein koodattu j‰rjestelm‰, n‰in ei pit‰isi edes tapahtua. Printataan error jokatapauksessa.
          if (vastaus == null) throw new SailoException("Etsitt‰v‰‰ buildia id:ll‰ " + i + " ei lˆytynyt");
          return vastaus;
      }
   
 /**
 * Etsii buildin id:n perusteella, ja palauttaa buildin nimen. Tehty p‰‰asiassa vaiheen 5 testaamista varten
 * , mutta j‰tet‰‰n jos jostain syyst‰ halutaankin vain nimi.
 * @param i buildin id jota vastaan etsit‰‰n build
 * @return buildin nimi
 */
   public String annaBuildNimi(int i){
       String vastaus = "";
       for(Build b : taulukko) {
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
    * Lukee tiedoston jo asetetulla profiilinimell‰
    * @throws SailoException jos ei lˆydy
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
           throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
           
       }
       /*
          * catch (IOException e) { throw new
          * SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); }
          */
   }
   
   /**
    * Tallentaa tiedot jo asetettuun tiedostonimeen
    * @throws SailoException jos tallennus ep‰onnistuu
    */
   public void tallenna() throws SailoException {
       tallenna(getProfiiliNimi());
   }
   
   /**
    * Tallentaa t‰m‰nhetkiset tiedot tiedostoon
    * @param tiednimi tallennettavan tiedoston nimi
    * @throws SailoException jos talletus ep‰onnistuu
    */
   public void tallenna(String tiednimi) throws SailoException {
       File tied = new File(tiednimi + "/" + tiedostoNimi + ".dat");
       try {
           try (PrintStream fo = new PrintStream(new FileOutputStream(tied, false))) {
               for (int i = 0; i < getLkm(); i++) {
                   Build build = anna(i);
                   fo.println(build);
               }
           }
       } catch(Exception e) {
           throw new SailoException("Tiedosto " + tied.getAbsolutePath() + " ei aukea");
       }
   }
   
   /**
    * get-metodi, jolla saadaan buildit-taulukon alkioiden lukum‰‰r‰ 
    * @return t‰ll‰hetkell‰ olevien alkioiden lukum‰‰r‰n
    */
   public int getLkm() {
       return lkm;
   }

   /**
    * tulostaa listan id:t‰ vastaavat buildit, tehty vaihetta 5 varten
    * @param halutut lista buildien id-numeroista, jotka tulostetaan
    * @param os tietovirta johon tulostetaan
    */
   public void tulostaBuildit(List<Integer> halutut, PrintStream os) {
       for (int i = 0; i < lkm; i++) {
           Build osoitin = taulukko[i];
           for (Integer x : halutut) {
               if (x == osoitin.getBid()) osoitin.tulosta(os);
           }
       }
    
    
}
    
}

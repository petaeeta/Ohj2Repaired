package StatTracker;

import java.io.PrintStream;
import java.util.List;

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
       if (lkm >= taulukko.length) throw new SailoException("Liikaa alkioita");
           taulukko[lkm] = build;
           lkm++;
   }
   
   /**
    * Metodi joka antaa viitteen buildiin, jonka id-numero vastaa annettua build-id:t‰
    * @param i buildin indeksi taulukossa
    * @return viitteen buildiin
    * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
    */
   public Build anna(int i) throws IndexOutOfBoundsException{
       if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
       return taulukko[i];
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

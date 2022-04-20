package fxStatTracker;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import StatTracker.*;
import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * kontrolleri StatTrackerin p‰‰ikkunalle
 * @author petteri
 * @version 14.2.2019
 *
 */
public class StatTrackerGUIController implements Initializable {
    @FXML private ScrollPane panelHahmo;
    @FXML private ScrollPane panelBuild;
    @FXML private ListChooser<Hahmo> chooserHahmot;
    
    /**
     * Uuden hahmon k‰sittely
     */
    @FXML private void handleUusiHahmo() {
        uusiHahmo();
    }
   
    /**
     * Poistaa valitun hahmon
     */
    @FXML private void handlePoistaHahmo() {
        Dialogs.showMessageDialog("Ei osata poistaa");
       }
    
    /**
     * Hahmon muokkausikkuna
     */
    @FXML private void handleHahmonTilastoja() {
        ModalController.showModal(StatTrackerGUIController.class.getResource("HahmonMuokkausGUIView.fxml"), "Muokkaus", null, "");
       }
    
    /**
     * Buildien hallinta
     */
    @FXML private void handleBuild() {
        uusiBuild();
       }
    

    /**
     * Nollaa profiilin tiedot, mutta s‰‰st‰‰ hahmot sek‰ buildit, jotka pit‰‰ itse k‰yd‰ poistamassa.
     */
    @FXML private void handleTietojenNollaus() {
        Dialogs.showMessageDialog("Ei osata nollata");
       }
    
    /**
     * Avaa ikkunan, jossa on version nimi, sek‰ tekij‰. T‰m‰n muoto todenn‰kˆisesti viel‰ muuttuu, mutta teoriassa sen voi t‰ll‰ dialogillakin tehd‰
     */
    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Author: Petteri Taro, Version: 14.2.2019");
       }
    
    /**
     * Avaa ohjeet siit‰, miten hahmoja luodaan, ja muutenkin k‰sitell‰‰n
     */
    @FXML private void handleHahmojaOhjeet() {
        Dialogs.showMessageDialog("Ei osata kertoa");
    }
    
    /**
     * Avaa ohjeet siit‰, miten buildeja avataan, poistetaan, muokataan ja muutenkin k‰ytet‰‰n
     */
    @FXML private void handleBuildOhjeet() {
        Dialogs.showMessageDialog("Ei osata kertoa");
    }
    
    /**
     * Avaa lyhyen selityksen siit‰, mik‰ StatTracker on, sek‰ kertoo lyhyesti sen k‰ytˆst‰
     */
    @FXML private void handleStatTrackerOhjeet() {
        Dialogs.showMessageDialog("Ei osata kertoa");
    }
    
    /**
     * Etsii hahmon hakuehdon mukaan
     */
    @FXML private void handleEtsi() {
        Dialogs.showMessageDialog("Ei osata viel‰ etsi‰");
       }
    
    /**
     * Avaa uuden profiilin
     */
    @FXML private void handleAvaa() {
        Dialogs.showMessageDialog("Ei osata avata");
    }
    
    /**
     * Poistuu ohjelmasta
     */
    @FXML private void handlePoistu() {
        Platform.exit();
    }

    /**
     * Metodi jota kysyt‰‰n sulkemisen yhteydess‰ varmistaakseen,
     * ett‰ kaikki tallentamaton tieto k‰sitell‰‰n halutulla tavalla ennen sulkemista.
     * Toistaiseksi palauttaa aina true, muutetaan toimivaksi, kun toteutetaan tiedostojen kirjoitus. 
     * 
     * @return boolean arvon, johon perustuen ohjelma joko suljetaan tai ei suljeta.
     */
    public boolean voikoSulkea() {
        return true;
    }




    
//===========================================================================    
    private Profiili profiili;
    private TextArea areaHahmo = new TextArea();
    private TextArea areaBuild = new TextArea();
    private Hahmo hahmoKohdalla;
    private int hid;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
        
    }

    private void alusta() {
        panelHahmo.setContent(areaHahmo);
        panelBuild.setContent(areaBuild);
        areaHahmo.setFont(new Font("Courier New", 12));
        areaBuild.setFont(new Font("Courier New", 12));
        panelHahmo.setFitToHeight(true);
        panelBuild.setFitToHeight(true);
        
        chooserHahmot.clear();
        chooserHahmot.addSelectionListener(e -> naytaHahmo());
        
    }
    
    /**
     * Luo uuden hahmon
     */
    public void uusiHahmo() {
        Hahmo hahmo = new Hahmo();
        try {
            profiili.LisaaHahmo(hahmo);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Virhe uuden luomisessa: " + e.getMessage());
            return;
        }
        hae(hahmo.getTunnusNro());
    }
    
    /**
     * Luo uuden buildin
     */
    public void uusiBuild() {
        try {
        if (hahmoKohdalla == null) return;
        Build build = new Build();
        profiili.LisaaBuild(build);
        profiili.LisaaHahmolleBuild(hahmoKohdalla.getTunnusNro(), build.getBid());
        hae(hahmoKohdalla.getTunnusNro());
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    
    /**
     * 
     * @param profiili jota k‰sitell‰‰n
     */
    public void setProfiili(Profiili profiili) {
        this.profiili = profiili;
        naytaHahmo();
    }

    /**
     * 
     */
    protected void naytaHahmo() {
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        
        if (hahmoKohdalla == null) return;
        
        areaHahmo.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaHahmo)){
            hahmoKohdalla.tulosta(os);
        }
        areaBuild.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaBuild)){
            hid = hahmoKohdalla.getTunnusNro();
            List<Integer> buildit = profiili.annaHahmonBuildit(hid);
            if (buildit.isEmpty()) return;
            profiili.tulostaBuildit(buildit, os);
        }
        
    }
    
    /**
     * Hakee hahmojen tiedot listaan
     * @param tunnusNro hahmon numero, joka aktivoidaan haun j‰lkeen
     */
    protected void hae(int tunnusNro) {
        chooserHahmot.clear();
        
        int index = 0;
        for (int i = 0; i < profiili.getHahmoja(); i++) {
            Hahmo hahmo = profiili.annaHahmo(i);
            if (hahmo.getTunnusNro() == tunnusNro) index = i;
            chooserHahmot.add(hahmo.getNimi(), hahmo);
        }
        chooserHahmot.getSelectionModel().clearAndSelect(index);
        
    }
    
    
}
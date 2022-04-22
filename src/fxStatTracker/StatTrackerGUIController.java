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
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * kontrolleri StatTrackerin p‰‰ikkunalle
 * @author petteri
 * @version 14.2.2019
 * @version 22.4.2022
 *
 */
public class StatTrackerGUIController implements Initializable {
    @FXML private ScrollPane panelHahmo;
    @FXML private ScrollPane panelBuild;
    @FXML private ListChooser<Hahmo> chooserHahmot;
    @FXML private ListChooser<Build> chooserBuildit;
    @FXML private TextField nimiField;
    @FXML private TextField tapotField;
    @FXML private TextField kuolematField;
    @FXML private TextField voitotField;
    @FXML private TextField haviotField;
    @FXML private TextField kdField;
    @FXML private TextField wlField;
    @FXML private TextArea buildInfo;
    @FXML private TextArea builditField;
    @FXML private TextField overallVoitot;
    @FXML private TextField overallHaviot;
    @FXML private TextField overallTapot;
    @FXML private TextField overallKuolemat;
    @FXML private TextField overallWl;
    @FXML private TextField overallKd;
    
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
        ModalController.showModal(StatTrackerGUIController.class.getResource("BuildLuontiGUIView.fxml"), "", null, "");
        //uusiBuild();
        uusiTemplateBuild();
       }
    
    @FXML private void assignBuild() {
        annaHahmolleBuild();
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
        avaa();
    }
    
    /**
     * Poistuu ohjelmasta
     */
    @FXML private void handlePoistu() {
        Platform.exit();
    }
    
    /**
     * Poistaa buildin
     */
    @FXML private void handlePoistaBuild() {
        Dialogs.showMessageDialog("Ei osata poistaa");
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




    
//===============================================================================================================
    private Profiili profiili;
    //private TextArea areaHahmo = new TextArea();
    //private TextArea areaBuild = new TextArea();
    private Hahmo hahmoKohdalla;
    private Build buildKohdalla;
    private int hid;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    private void alusta() {
        //panelHahmo.setContent(areaHahmo);
        //panelBuild.setContent(areaBuild);
        //areaHahmo.setFont(new Font("Courier New", 12));
        //areaBuild.setFont(new Font("Courier New", 12));
        panelHahmo.setFitToHeight(true);
        panelBuild.setFitToHeight(true);
        
        chooserHahmot.clear();
        chooserHahmot.addSelectionListener(e -> naytaHahmo());
        
        chooserBuildit.clear();
        chooserBuildit.addSelectionListener(e -> naytaBuild());
    }
    
    /*
     * P‰ivitt‰‰ overall-tilastot
     */
    private void updateOverall() {
        int[] tilastot = profiili.getOverallTilastot();
        overallVoitot.setText(Integer.toString(tilastot[0]));
        overallHaviot.setText(Integer.toString(tilastot[1]));
        overallTapot.setText(Integer.toString(tilastot[2]));
        overallKuolemat.setText(Integer.toString(tilastot[3]));
        overallWl.setText(Double.toString(intSuhde(tilastot[0], tilastot[1])));
        overallKd.setText(Double.toString(intSuhde(tilastot[2], tilastot[3])));
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
        haeHahmot(hahmo.getHid());
        updateOverall();
    }
    
    /**
     * Luo uuden buildin, mutte
     */
    private void uusiTemplateBuild() {
        Build build = new Build();
        
        try {
            profiili.LisaaBuild(build);
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virhe uuden buildin luomisessa: " + e.getMessage());
            return;
        }
        haeBuildit(build.getBid());
    }
    
    /**
     * Luo uuden buildin
     */
    /*
     * public void uusiBuild() { try { if (hahmoKohdalla == null) return; Build
     * build = new Build(); profiili.LisaaBuild(build);
     * haeHahmot(hahmoKohdalla.getHid()); } catch (SailoException e) {
     * Dialogs.showMessageDialog(e.getMessage()); } }
     */
    
    
    /**
     * 
     * @param profiili jota k‰sitell‰‰n
     */
    public void setProfiili(Profiili profiili) {
        this.profiili = profiili;
        updateOverall();
        naytaHahmo();
    }
    
    /*
     * Palauttaa suhteen kahden int-luvun v‰lill‰. K‰ytet‰‰n kd:n ja wl:n laskemiseksi
     */
    private double intSuhde(int jaettava, int jakaja) {
        return Double.valueOf(jaettava)/Double.valueOf(jakaja);
    }
    
    /**
     * N‰ytt‰‰ buildit joissa hahmo on mukana
     */
    protected void naytaBuild() {
        buildKohdalla = chooserBuildit.getSelectedObject();
        
        if (buildKohdalla == null) return;
        buildInfo.setText(buildKohdalla.getKuvaus());
    }
    
    /**
     * Lis‰‰ valitun buildin valitulle hahmolle
     */
    protected void annaHahmolleBuild(){
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        buildKohdalla = chooserBuildit.getSelectedObject();
        
        if (buildKohdalla == null || hahmoKohdalla == null) {
            System.out.println("Buildia ei annettu sill‰ toinen valinnoista on tyhj‰ \nValittu hahmo: " + hahmoKohdalla + "\nValittu build" + buildKohdalla);
            Dialogs.showMessageDialog("Build voidaan antaa hahmolle vain jos build sek‰ hahmo ovat valittuina");
            return;
        }
        profiili.LisaaHahmolleBuild(hahmoKohdalla.getHid(), buildKohdalla.getBid());
        naytaHahmo();
    }

    /**
     * N‰ytt‰‰ hahmon tiedot joka on t‰ll‰ hetkell‰ valittuna
     */
    protected void naytaHahmo() {
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        builditField.clear();
        
        if (hahmoKohdalla == null) return;
        nimiField.setText(hahmoKohdalla.getNimi());
        int voitot = hahmoKohdalla.getVoitot();
        int haviot = hahmoKohdalla.getHaviot();
        int tapot = hahmoKohdalla.getTapot();
        int kuolemat = hahmoKohdalla.getKuolemat();
        double kd = intSuhde(tapot, kuolemat);
        double wl = intSuhde(voitot, haviot);
        
        tapotField.setText(Integer.toString(tapot));
        kuolematField.setText(Integer.toString(kuolemat));
        voitotField.setText(Integer.toString(voitot));
        haviotField.setText(Integer.toString(haviot));
        kdField.setText(Double.toString(kd));
        wlField.setText(Double.toString(wl));
        
        List<Integer> BuildIdt = profiili.annaHahmonBuildit(hahmoKohdalla.getHid());
        for (int id : BuildIdt) {
            Build vastaus = profiili.annaBuildViite(id);
            if (vastaus.getNimi() != null) builditField.appendText(vastaus.getNimi() + "\n");
        }
        
        /*
         * areaHahmo.setText(""); try (PrintStream os =
         * TextAreaOutputStream.getTextPrintStream(areaHahmo)){
         * hahmoKohdalla.tulosta(os); } areaBuild.setText(""); try (PrintStream
         * os = TextAreaOutputStream.getTextPrintStream(areaBuild)){ hid =
         * hahmoKohdalla.getHid(); List<Integer> buildit =
         * profiili.annaHahmonBuildit(hid); if (buildit.isEmpty()) return;
         * profiili.tulostaBuildit(buildit, os); }
         */
        
    }
    
    /**
     * Hakee hahmojen tiedot listaan
     * @param tunnusNro hahmon numero, joka aktivoidaan haun j‰lkeen
     */
    private void haeHahmot(int tunnusNro) {
        chooserHahmot.clear();
        
        int index = 0;
        for (int i = 0; i < profiili.getHahmoMaara(); i++) {
            Hahmo hahmo = profiili.annaHahmo(i);
            if (hahmo.getHid() == tunnusNro) index = i;
            chooserHahmot.add(hahmo.getNimi(), hahmo);
        }
        chooserHahmot.getSelectionModel().clearAndSelect(index);
        
    }
    
    /**
     * Hakee buildien tiedot listaan
     * @param tunnusNro buildin numero, joka aktivoidaan haun j‰lkeen
     */
    protected void haeBuildit(int tunnusNro) {
        chooserBuildit.clear();
        
        int index = 0;
        for (int i = 0; i < profiili.getBuildMaara(); i++) {
            Build build = profiili.annaBuild(i);
            if (build.getBid() == tunnusNro) index = i;
            chooserBuildit.add(build.getNimi(), build);
        }
        chooserBuildit.getSelectionModel().clearAndSelect(index);
    }

    /**
     * Metodi joka varmistaa ett‰ profiili avataan
     * @return False jos k‰ytt‰j‰ peruu profiilin avaamisen
     */
    public static boolean avaa() {
        String profiiliNimi = AvaaController.kysyProfiili(null, "exampleProfile");
        if (profiiliNimi == null) return false;
        // lueTiedosto(profiiliNimi);
        return true;
    }
    
    
}
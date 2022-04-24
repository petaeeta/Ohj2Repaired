package fxStatTracker;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import StatTracker.*;
import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * kontrolleri StatTrackerin p‰‰ikkunalle
 * @author petteri
 * @version 14.2.2019
 * @version 22.4.2022
 * @version 1.0 24.4.2022
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
    @FXML private GridPane gridHahmo;
    @FXML private ComboBoxChooser<Object> cbKentat;
    @FXML private TextField hakuEhto;
    
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
        poistaHahmo();
       }

    /**
     * Hahmon muokkausikkuna
     */
    @FXML private void handleHahmonTilastoja() {
        avaaHahmonMuokkaus();
       }
    
    /**
     * Buildien hallinta
     */
    @FXML private void handleBuild() {
        uusiTemplateBuild();
       }
    
    /*
     * Buildin lis‰‰minen hahmolle
     */
    @FXML private void assignBuild() {
        annaHahmolleBuild();
    }
    
    /**
     * Avaa ikkunan, jossa on version nimi, sek‰ tekij‰. T‰m‰n muoto todenn‰kˆisesti viel‰ muuttuu, mutta teoriassa sen voi t‰ll‰ dialogillakin tehd‰
     */
    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Author: Petteri Taro, Version: 14.2.2019");
       }
    
    /**
     * Avaa lyhyen selityksen siit‰, mik‰ StatTracker on, sek‰ kertoo lyhyesti sen k‰ytˆst‰
     */
    @FXML private void handleStatTrackerOhjeet() {
        avaaApu();
    }
    
    /*
     * Vaihtaa uuteen profiiliin
     */
    @FXML private void handleVaihda() {
        if (voikoSulkea()) {
            avaa();
        }
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
        if (voikoSulkea()) {
            Platform.exit();
        }
    }
    
    /**
     * Poistaa buildin
     */
    @FXML private void handlePoistaBuild() {
        poistaBuild();
    }

    @FXML void handleBuildMuokkaus() {
        avaaBuildinMuokkaus();
    }
    
    /**
     * Poistaa spesifin hahmobuildin
     */
    @FXML void handlePoistaHahmonBuild() {
        poistaHahmonBuild();
    }

    /*
     * Hakee halutun ehdon mukaan
     */
    @FXML void handleHakuEhto() {
        haeHahmot(0);
    }
    
    /*
     * Tiedon tallennus
     */
    @FXML void handleTallenna() {
        tallenna();
    }
    
    /**
     * Tulostaa hahmon tiedot
     */
    @FXML private void handleTulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null);
        tulostaValitutHahmot(tulostusCtrl.getTextArea());
    }

    /**
     * Metodi jota kysyt‰‰n sulkemisen yhteydess‰ varmistaakseen,
     * ett‰ kaikki tallentamaton tieto k‰sitell‰‰n halutulla tavalla ennen sulkemista.
     * Toistaiseksi palauttaa aina true, muutetaan toimivaksi, kun toteutetaan tiedostojen kirjoitus. 
     * 
     * @return boolean arvon, johon perustuen ohjelma joko suljetaan tai ei suljeta.
     */
    public boolean voikoSulkea() {
        if (profiili.askMuutettu()) {
            if (Dialogs.showQuestionDialog("Tallennetaanko?", "Sinulla on tallentamattomia muutoksia, tallennetaanko ennen sulkemista?", "Kyll‰", "Ei")); {
                tallenna();
            }
        }
        return true;
    }




    
//===============================================================================================================
    private Profiili profiili;
    private String profiiliNimi;
    private Hahmo hahmoKohdalla;
    private Build buildKohdalla;
    private Build hahmobuildKohdalla;
    private static Hahmo apuhahmo = new Hahmo(false);
    private TextField edits[];
    private ListChooser<Build> chooserHahmoBuildit;
    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    private void alusta() {
        panelHahmo.setFitToHeight(true);
        panelBuild.setFitToHeight(true);
        
        cbKentat.clear();
        for (int k = apuhahmo.ekaKentta(); k < apuhahmo.getKenttia(); k++) {
            cbKentat.add(apuhahmo.getKysymys(k));
        }
        cbKentat.getSelectionModel().select(0);
        cbKentat.addSelectionListener(e -> haeHahmot());
        
        edits = HahmonMuokkausController.luoKentat(gridHahmo, apuhahmo.getKenttia(), false);
        
        Label label = new Label("Hahmon Buildit:");
        chooserHahmoBuildit = new ListChooser<>();
        gridHahmo.add(label, 0, apuhahmo.getKenttia());
        gridHahmo.add(chooserHahmoBuildit, 1, apuhahmo.getKenttia());
        
        gridHahmo.setGridLinesVisible(true);
        
        chooserHahmot.clear();
        chooserHahmot.addSelectionListener(e -> laitaHahmo());
        
        chooserBuildit.clear();
        chooserBuildit.addSelectionListener(e -> naytaBuild());
        
        chooserHahmoBuildit.clear();
        chooserHahmoBuildit.setPrefHeight(160);
    }
    
    /**
     * Avataan harkkatyˆn suunnitelma
     */
    private void avaaApu() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/ht/petaeeta#mtypuo4cyMgg");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    /*
     * Tallentaa tiedot levylle
     */
    private void tallenna() {
        try {
            profiili.tallenna();
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
     
    /*
     * Laittaa hahmon tiedot kenttiin
     */
    private void laitaHahmo() {
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        chooserHahmoBuildit.clear();
        if (hahmoKohdalla == null) return;
        HahmonMuokkausController.laitaHahmo(edits, hahmoKohdalla, apuhahmo.getKenttia());
        List<Integer> BuildIdt = profiili.annaHahmonBuildit(hahmoKohdalla.getHid());
        for (int id : BuildIdt) {
            Build vastaus;
            try {
                vastaus = profiili.annaBuildViite(id);
                if (vastaus.getNimi() != null) chooserHahmoBuildit.add(vastaus.getNimi(), vastaus);
            } catch (SailoException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
    }
    
    /*
     * Poistaa hahmolta h‰nelle assignatun buildin
     */
    private void poistaHahmonBuild() {
        hahmobuildKohdalla = chooserHahmoBuildit.getSelectedObject();
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        if (hahmobuildKohdalla == null) {
            Dialogs.showMessageDialog("Ei hahmon buildia valittu");
            return;
        }
        else if (hahmoKohdalla == null) {
            Dialogs.showMessageDialog("Ei hahmoa valittu");
            return;
        }
        profiili.poistaHahmonBuild(hahmoKohdalla, hahmobuildKohdalla);
        laitaHahmo();
    }
    
    /*
     * Poistaa hahmon kokonaan
     */
    private void poistaHahmo() {
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        if (hahmoKohdalla == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko hahmo: " + hahmoKohdalla.getNimi() + "?", "Kyll‰", "Ei")) return;
        int vastaus = profiili.poistaHahmo(hahmoKohdalla);
        if (vastaus == 0) Dialogs.showMessageDialog("Poisto ep‰onnistui.");
        chooserHahmot.getSelectionModel().clearAndSelect(0);
        clearHahmoFields();
        haeHahmot(0);
        laitaHahmo();
        updateOverall();
    }
    
    /*
     * Poistaa buildin kokonaan
     */
    private void poistaBuild() {
        buildKohdalla = chooserBuildit.getSelectedObject();
        if(buildKohdalla == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko build: " + buildKohdalla.getNimi() + "?", "Kyll‰", "Ei")) return;
        int vastaus = profiili.poistaBuild(buildKohdalla);
        if (vastaus == 0) Dialogs.showMessageDialog("Poisto ep‰onnistui.");
        haeBuildit(0);
        laitaHahmo();
    }
    
    /*
     * Tyhjent‰‰ hahmon tekstikent‰t
     */
    private void clearHahmoFields() {
        for (TextField edit : edits) {
            if (edit != null) edit.clear();
        }
    }
    
    /*
     * Metodi jolla muokataan olemassaolevaa hahmoa
     */
    private void avaaHahmonMuokkaus() {
        try {
        hahmoKohdalla = chooserHahmot.getSelectedObject();
        if (hahmoKohdalla == null) return;
        Hahmo hahmo = new Hahmo(false);
        hahmo = HahmonMuokkausController.kysyHahmo(null, hahmoKohdalla.kloonaa());
        if (hahmo == null) return;
        profiili.korvaaTaiLisaa(hahmo);
        haeHahmot(hahmo.getHid());
        updateOverall();}
        catch(SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    /*
     * Metodi jolla muokataan olemassaolevaa buildia
     */
    private void avaaBuildinMuokkaus() {
        try {
        buildKohdalla = chooserBuildit.getSelectedObject();
        if (buildKohdalla == null) return;
        Build build = new Build(false);
        build = BuildLuontiController.kysyBuild(null, buildKohdalla.kloonaa());
        if (build == null) return;
        profiili.korvaaTaiLisaa(build);
        haeBuildit(build.getBid());
        laitaHahmo();
        updateOverall();}
        catch(SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
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
        try {
            Hahmo hahmo = new Hahmo(false);
            hahmo = HahmonMuokkausController.kysyHahmo(null, hahmo);
            if ( hahmo == null) return;
            hahmo.rekisteroi();
            profiili.LisaaHahmo(hahmo);
            haeHahmot(hahmo.getHid());
            updateOverall();
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Virhe uuden luomisessa: " + e.getMessage());
            return;
        }
    }
    
    /**
     * Luo uuden buildin rekisterˆim‰tt‰ sit‰
     */
    private void uusiTemplateBuild() {
        try {
            Build build = new Build(false);
            build = BuildLuontiController.kysyBuild(null, build);
            if (build == null) return;
            build.rekisteroi();
            profiili.LisaaBuild(build);
            haeBuildit(build.getBid());
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virhe uuden buildin luomisessa: " + e.getMessage());
            return;
        }
    }
    
    /**
     * Asettaa profiilin
     * @param profiili joka valittiin
     */
    public void setProfiili(Profiili profiili) {
        this.profiili = profiili;
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
            Dialogs.showMessageDialog("Build voidaan antaa hahmolle vain jos build sek‰ hahmo ovat valittuina");
            return;
        }
        profiili.LisaaHahmolleBuild(hahmoKohdalla.getHid(), buildKohdalla.getBid());
        laitaHahmo();
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
            Build vastaus;
            try {
                vastaus = profiili.annaBuildViite(id);
                if (vastaus.getNimi() != null) builditField.appendText(vastaus.getNimi() + "\n");
            } catch (SailoException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
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
    
    private void haeHahmot() {
        if (chooserHahmot.getSelectedObject() != null)haeHahmot(chooserHahmot.getSelectedObject().getHid());
    }
    
    /**
     * Hakee hahmojen tiedot listaan
     * @param tunnusNro hahmon numero, joka aktivoidaan haun j‰lkeen
     */
    private void haeHahmot(int tunnusNro) {
        String hakuehto = "";
        if (hakuEhto.getText().trim() != "") hakuehto = hakuEhto.getText().trim();
        chooserHahmot.clear();
        
        int sortEhto = cbKentat.getSelectionModel().getSelectedIndex() + apuhahmo.ekaKentta();
        
        int index = 0;
        List<Hahmo> vastaus = profiili.etsi(hakuehto);
        
        /*
         * Sorttaa hakujen perusteella listan. Ilman rajapintoja pit‰‰ tehd‰ n‰in,
         * sill‰ haluan sortata hahmoa kolmen eri muuttujatyypin perusteella, String jos nimi, int jos perus-statistiikka.
         * K/D sek‰ W/L myˆs ilmoitetaan kaikkialla double-muodossa, joten ne j‰rjestet‰‰n ihan tavallisella v‰hennyslaskulla
         */
        Collections.sort(vastaus, new Comparator<Hahmo>() {
            @Override
            public int compare(Hahmo x, Hahmo y) {
                switch(sortEhto) {
                    case 1:
                        return x.anna(1).compareTo(y.anna(1));
                    case 6:
                        if (intSuhde(x.getTapot(), x.getKuolemat()) < intSuhde(y.getTapot(), y.getKuolemat())) return 1;
                        return -1;
                    case 7:
                        if (intSuhde(x.getVoitot(), x.getHaviot()) < intSuhde(y.getVoitot(), y.getHaviot())) return 1;
                        return -1;
                    default:
                        return y.annaNumeraaliset(sortEhto) - x.annaNumeraaliset(sortEhto);
                }
            }
        });
        
        int i = 0;
        for (Hahmo hahmo : vastaus) {
            if (hahmo.getHid() == tunnusNro) index = i;
            chooserHahmot.add(hahmo.getNimi(), hahmo);
            i++;
        }
        chooserHahmot.getSelectionModel().clearAndSelect(index);
        laitaHahmo();
    }
    
    /**
     * Hakee buildien tiedot listaan
     * @param tunnusNro buildin numero, joka aktivoidaan haun j‰lkeen
     */
    protected void haeBuildit(int tunnusNro) {
        chooserBuildit.clear();
        List<Build> vastaus = profiili.annaBuildit();
        int index = 0;
        int i = 0;
        for (Build build : vastaus) {
            if(build.getBid() == tunnusNro) index = i;
            chooserBuildit.add(build.getNimi(), build);
            i++;
        }
        chooserBuildit.getSelectionModel().clearAndSelect(index);
    }
    
    /**
     * Alustaa kerhon lukemalla tiedostonimen joka on jo asetettu
     */
    protected void lueTiedosto() {
        try {
            profiili.lueTiedostosta(profiiliNimi);
        } catch(SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        haeHahmot(0);
        haeBuildit(0);
        updateOverall();
        
    }
    
    
    /**
     * Tulostaa hahmon tiedot
     * @param os Tietovirta johon tulostetaan
     * @param hahmo joka tulostetaan
     */
    public void tulosta(PrintStream os, final Hahmo hahmo) {
        os.println("----------------------------------------------");
        hahmo.tulosta(os);
        os.println("----------------------------------------------");
    }
    
    
    /**
     * Tulostaa hahmon tiedot annettuun striimiin
     * @param text joka tulostetaan
     */
    public void tulostaValitutHahmot(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)){
            os.println("Tulostetaan hahmot");
            for (Hahmo hahmo : chooserHahmot.getObjects()) {
                tulosta(os, hahmo);
                os.println("\n\n");
            }
        }
    }
    
    
    /**
     * Metodi joka varmistaa ett‰ profiili avataan
     * @return False jos k‰ytt‰j‰ peruu profiilin avaamisen
     */
    public boolean avaa() {
        profiiliNimi = AvaaController.kysyProfiili(null, "EsimerkkiProfiili");
        if (profiiliNimi == null) return false;
        lueTiedosto();
        return true;
    }
}
package fxStatTracker;

import java.net.URL;
import java.util.ResourceBundle;

import StatTracker.Hahmo;
import fi.jyu.mit.fxgui.*;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Kontrolleri hahmon tilastojen muokkaamiselle
 * @author petteri
 * @version 15.2.2019
 * @version 21.4.2022
 *
 */
public class HahmonMuokkausController implements ModalControllerInterface<Hahmo> {

    @FXML private Button muokkausvalmis;
    @FXML private Button perumuokkaus;
    @FXML private GridPane gridMuokkaus;

    @FXML void handleHahmonTilastoTallennus() {
        
        if ( hahmoKohdalla != null && hahmoKohdalla.getNimi().trim().equals("")) {
            Dialogs.showMessageDialog("Nimi ei saa olla tyhj‰");
            return;
        }
        ModalController.closeStage(muokkausvalmis);
        
    }

    @FXML void handlePeru() {
        hahmoKohdalla = null;
        ModalController.closeStage(muokkausvalmis);
    }
    
    @Override
    public Hahmo getResult() {
        return hahmoKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Hahmo oletus) {
        alusta();
        hahmoKohdalla = oletus;
        laitaHahmo(edits, hahmoKohdalla, apuhahmo.getEditKenttia());
        
    }
    
    //========================================================
    private TextField edits[];
    private static Hahmo apuhahmo = new Hahmo(false);
    private Hahmo hahmoKohdalla;
    
    /**
     * Tekee tarvittavat alustukset
     */
    protected void alusta() {
        edits = luoKentat(gridMuokkaus, apuhahmo.getEditKenttia(), true);
        for (TextField edit : edits) {
            if (edit != null)
                edit.setOnKeyReleased( e -> kasitteleMuutosHahmoon((TextField)(e.getSource())));
        }
    }
    
    /**
     * K‰sittelee tehdyt muutokset.
     * @param edit tekstikentt‰ jota editoidaan
     */
    protected void kasitteleMuutosHahmoon(TextField edit) {
    if (hahmoKohdalla == null) return;
    int k = getFieldId(edit, apuhahmo.ekaKentta());
    String s = edit.getText();
    String virhe = null; 
    virhe = hahmoKohdalla.aseta(k, s);
    if (virhe == null) {
        Dialogs.setToolTipText(edit, "");
        edit.getStyleClass().removeAll("virhe");
        
        }
    else { 
        Dialogs.setToolTipText(edit, virhe);
        edit.getStyleClass().add("Virhe");
        }
    }
    
    /**
     * Palautetaan komponentin id
     * @param obj komponentti jota tutkitaan
     * @param oletus mik‰ arvo palautetaan jos arvo ei ole oikeellinen
     * @return komponentin id
     */
    public static int getFieldId(Object obj, int oletus) {
        if (!( obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        return Mjonot.erotaInt(node.getId().substring(1), oletus);
    }
    
    /**
     * Luo hahmoeditointikent‰t halutulle gridille, sek‰ kentt‰m‰‰r‰lle
     * @param gridHahmo Grid jolle kent‰t tehd‰‰n
     * @param kenttia m‰‰r‰ kentist‰ jotka halutaan
     * @param editable Voiko Kentt‰‰ editoida
     * @return TextField[] jossa kenttien viitteet
     */
    public static TextField[] luoKentat(GridPane gridHahmo, int kenttia, boolean editable) {
        gridHahmo.getChildren().clear();
        TextField[] edits = new TextField[kenttia];
        for(int i = 0, k = apuhahmo.ekaKentta(); k < kenttia; k++, i++) {
            Label label = new Label(apuhahmo.getKysymys(k));
            gridHahmo.add(label, 0, i);
            TextField edit = new TextField();
            edit.setEditable(editable);
            edits[k] = edit;
            edit.setId("e"+k);
            gridHahmo.add(edit, 1, i);
        }
        return edits;   
    }

    /**
     * Asettaa halutun hahmon tiedot haluttuihin kenttiin
     * @param edits Taulukollinen TextFieldej‰ joihin tiedot asetetaan 
     * @param hahmo jonka tiedot asetetaan
     * @param kenttia kenttien m‰‰r‰ jotka asetetaan, esim editoidessa ei n‰ytet‰ K/D tai W/L
     */
    public static void laitaHahmo(TextField[] edits, Hahmo hahmo, int kenttia) {
        if (hahmo == null) return;
        for (int k = apuhahmo.ekaKentta(); k < kenttia; k++) {
            edits[k].setText(hahmo.anna(k));
        }
        
    }

    /**
     * @param modalityStage mille ollaan modaalisia
     * @param oletus mit‰ dataa k‰ytet‰‰n oletuksena
     * @return null jos painetaan cancel, muuten uusi hahmo
     */
    public static Hahmo kysyHahmo(Stage modalityStage, Hahmo oletus) {
        return ModalController.showModal(StatTrackerGUIController.class.getResource("HahmonMuokkausGUIView.fxml"), "Hahmon Tiedot", modalityStage, oletus);
        
        /*
         * return ModalController.<Hahmo, HahmonMuokkausController>showModal(
         * HahmonMuokkausController.class.getResource(
         * "HahmonMuokkausGUIView,fxml"), "Muokkaus", modalityStage, oletus,
         * ctrl -> ctrl.setKentta(kentta));
         */

    }
    
}

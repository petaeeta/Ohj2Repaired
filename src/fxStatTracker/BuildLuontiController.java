package fxStatTracker;

import StatTracker.Build;
import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kontrolleri buildien luomiseen
 * @author petteri
 * @version 15.2.2019
 * @version 21.4.2022
 *
 */
public class BuildLuontiController implements ModalControllerInterface<Build> {

    @FXML private Button tallenna;
    @FXML private TextField buildNimiField;
    @FXML private TextArea buildKuvausArea;
    
    
    @FXML void handleBuildTallenna() {
        ModalController.closeStage(tallenna);
    }

    @FXML void handleBuildperuuta() {
        buildKohdalla = null;
        ModalController.closeStage(tallenna);
    }

    @Override
    public Build getResult() {
        return buildKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Build oletus) {
        buildKohdalla = oletus;
        alusta();
    }
    
    //==========================================================================
    private static Build apubuild = new Build(false);
    private Build buildKohdalla;
    
    /**
     * Tekee tarvittavat alustukset
     */
    protected void alusta() {
        buildNimiField.setOnKeyReleased(e -> kasitteleMuutosBuildiin((TextField)(e.getSource()), 0));
        buildKuvausArea.setOnKeyReleased(e -> kasitteleMuutosBuildiin((TextArea)(e.getSource()), 1));
        buildNimiField.setText(buildKohdalla.getNimi());
        buildKuvausArea.setText(buildKohdalla.getKuvaus());
    }
    
    /**
     * K‰sittelee tehdyt muutokset.
     * @param edit tekstikentt‰ jota editoidaan
     * @param k arvo joka kent‰lle halutaan
     */
    protected void kasitteleMuutosBuildiin(TextField edit, int k) {
        if (buildKohdalla == null) return;
            String s = edit.getText();
            String virhe = null; 
            virhe = buildKohdalla.aseta(k, s);
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
     * K‰sittelee tehdyt muutokset.
     * @param edit tekstikentt‰ jota editoidaan
     * @param k arvo joka kent‰lle halutaan
     */
    protected void kasitteleMuutosBuildiin(TextArea edit, int k) {
        if (buildKohdalla == null) return;
            String s = edit.getText();
            String virhe = null; 
            virhe = buildKohdalla.aseta(k, s);
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
     * @param modalityStage mille ollaan modaalisia
     * @param oletus mit‰ dataa k‰ytet‰‰n oletuksena
     * @return null jos painetaan cancel, muuten uusi build
     */
    public static Build kysyBuild(Stage modalityStage, Build oletus) {
        return ModalController.showModal(StatTrackerGUIController.class.getResource("BuildLuontiGUIView.fxml"), "Buildin Tiedot", modalityStage, oletus);
    }
    
    
}

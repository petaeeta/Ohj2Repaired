package fxStatTracker;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Kysyt‰‰n k‰ytt‰j‰lt‰ profiilin nimi, ja etsit‰‰n kyseisen profiilin tiedot.
 * toteutetaan kun tietoa oikeasti kirjoitetaan ylˆs, mik‰li p‰‰tet‰‰n tehd‰ lukuisia profiileja yhden sijasta.
 * @author Petteri
 * @version 14.2.2019
 * @version 21.4.2022
 */
public class AvaaController implements ModalControllerInterface<String> {

    @FXML private TextField textUusiProfiili;
    @FXML private ComboBox<String> comboboxProfiili;
    
    @FXML private void handleUusiProfiili() {
        vastaus = textUusiProfiili.getText();
        ModalController.closeStage(textUusiProfiili);
    }
    
    @FXML private void handleValitseProfiili() {
        ModalController.closeStage(textUusiProfiili);
    }
    
    @FXML private void handlePeruuta() {
        ModalController.closeStage(textUusiProfiili);
    }
    
    //================================================================================================================
    private String vastaus = null;
    
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        textUusiProfiili.requestFocus();
    }

    @Override
    public void setDefault(String oletus) {
        textUusiProfiili.setText(oletus);
        
    }
    
    /**
     * K‰ytt‰j‰lt‰ kysyt‰‰n profiili
     * @param modalityStage jonka suhteen ollaan modaalisia. Jos null, ollaan modaalisia koko sovellukselle.
     * @param oletus oletusnimi profiilille
     * @return null jos k‰ytt‰j‰ painaa cancel, profiilin nimi jos k‰ytt‰j‰ sen syˆtt‰‰
     */
    public static String kysyProfiili(Stage modalityStage, String oletus) {
        return ModalController.showModal(AvaaController.class.getResource("AvaaGUIView.fxml"), "Profile 1", modalityStage, oletus);
    }
   

}
package fxStatTracker;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Kysyt‰‰n k‰ytt‰j‰lt‰ profiilin nimi, ja etsit‰‰n kyseisen profiilin tiedot.
 * toteutetaan kun tietoa oikeasti kirjoitetaan ylˆs, mik‰li p‰‰tet‰‰n tehd‰ lukuisia profiileja yhden sijasta.
 * @author Petteri
 * @version 14.2.2019
 * @version 21.4.2022
 * @Version 24.4.2022
 */
public class AvaaController implements ModalControllerInterface<String> {

    @FXML private TextField textProfiili;
    
    @FXML private void handleProfiili() {
        vastaus = textProfiili.getText();
        ModalController.closeStage(textProfiili);
    }
    
    @FXML private void handlePeruuta() {
        ModalController.closeStage(textProfiili);
    }
    
    //================================================================================================================
    private String vastaus = null;
    
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        textProfiili.requestFocus();
    }

    @Override
    public void setDefault(String oletus) {
        textProfiili.setText(oletus);
        
    }
    
    /**
     * K‰ytt‰j‰lt‰ kysyt‰‰n profiili
     * @param modalityStage jonka suhteen ollaan modaalisia. Jos null, ollaan modaalisia koko sovellukselle.
     * @param oletus oletusnimi profiilille
     * @return null jos k‰ytt‰j‰ painaa cancel, profiilin nimi jos k‰ytt‰j‰ sen syˆtt‰‰
     */
    public static String kysyProfiili(Stage modalityStage, String oletus) {
        return ModalController.showModal(AvaaController.class.getResource("AvaaGUIView.fxml"), "Profiilin valinta", modalityStage, oletus);
    }
   

}
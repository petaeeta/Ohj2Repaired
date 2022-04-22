package fxStatTracker;

import fi.jyu.mit.fxgui.*;
import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Kontrolleri buildien luomiseen
 * @author petteri
 * @version 15.2.2019
 * @version 21.4.2022
 *
 */
public class BuildLuontiController implements ModalControllerInterface<String> {

    @FXML private Button tallenna;
    
    
    @FXML void handleBuildTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
        ModalController.closeStage(tallenna);
    }

    @FXML void handleBuildperuuta() {
        Dialogs.showMessageDialog("Perutaanko muutokset?");
        ModalController.closeStage(tallenna);
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
    
}

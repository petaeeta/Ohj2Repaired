package fxStatTracker;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Kontrolleri hahmon tilastojen muokkaamiselle
 * @author petteri
 * @version 15.2.2019
 * @version 21.4.2022
 *
 */
public class HahmonMuokkausController implements ModalControllerInterface<String> {

    @FXML private Button muokkausvalmis;
    @FXML private Button perumuokkaus;

    @FXML void handleHahmonTilastoTallennus() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
        ModalController.closeStage(muokkausvalmis);
    }

    @FXML void handlePeru() {
        ModalController.closeStage(muokkausvalmis);
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

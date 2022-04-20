package fxStatTracker;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import StatTracker.*;


/**
 * @author petteri
 * @version 14.2.2019
 *
 */
public class StatTrackerMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("StatTrackerGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final StatTrackerGUIController stattrackerCtrl = (StatTrackerGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("stattracker.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("StatTracker");
            
            Profiili profiili = new Profiili();
            stattrackerCtrl.setProfiili(profiili);
            
            primaryStage.setOnCloseRequest((event ->{
                if (!stattrackerCtrl.voikoSulkea() ) event.consume();
            }));
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
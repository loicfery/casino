package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.IIOException;

public class LoadingInterface {

    public  void loading(String nameSample, String nameCSS, int sizeX, int sizeY, Object interfaceController, MouseEvent mouseEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(nameSample));
            loader.setControllerFactory(c -> interfaceController);
            Parent root = loader.load();
            Scene scene = new Scene(root, sizeX, sizeY);
            //scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GalleryPicAnnonceController implements Initializable {
    @FXML
    private ScrollPane Pane;
    @FXML
    private TilePane tile;

    
    public ImageView createImageView(final File imageFile) {
     ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked((MouseEvent mouseEvent) -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            BorderPane borderPane = new BorderPane();
                            ImageView imageView1 = new ImageView();
                            Image image1 = new Image(new FileInputStream(imageFile));
                            imageView1.setImage(image1);
                            imageView1.setPreserveRatio(true);
                            imageView1.setSmooth(true);
                            imageView1.setCache(true);
                            imageView1.setFitHeight(250);
                            imageView1.setFitWidth(250);
                            borderPane.setCenter(imageView1);
                            borderPane.setStyle("-fx-background-color: BLACK");
                            Stage newStage = new Stage();
                            newStage.setTitle(imageFile.getName());
                            Scene scene = new Scene(borderPane);
                            newStage.setScene(scene);
                            newStage.show();
                        }catch (FileNotFoundException e) {
                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
        }
        return imageView;  
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path = "L:\\studir$\\3eme\\3 eme\\3A\\PIDEV\\PI_Java_3A\\src\\Image\\";
        File folder = new File(path);
        File[] listOfFiles;
        listOfFiles = folder.listFiles();
        for (final File file : listOfFiles) {
                ImageView imageView;
                imageView = createImageView(file);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                tile.getChildren().addAll(imageView);
        }
    }
    
}

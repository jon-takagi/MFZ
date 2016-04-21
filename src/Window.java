/**
 * Created by 40095 on 4/20/16.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Window extends Application {
    private BorderPane root;
    private Stage stage;
    private Scene scene;
    VBox frameBox;
    Company localPlayer;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        Scene buildCompany = new Scene(root, 600, 500);
        stage.setScene(buildCompany);
        buildCompany.getStylesheets().add("stylesheet.css");
        stage.setTitle("MFZ Online");  //text for the title bar of the window

        localPlayer = new Company();
        generateCompanyBuilderUI();
        stage.show();
    }

    void generateCompanyBuilderUI() {
        HBox top = new HBox();
        Label frameNumLabel = new Label("How many frames? (Click to edit)");
        Button frameNumButton = new Button("0");
        frameNumButton.setOnAction((event) -> {
            TextInputDialog d = new TextInputDialog("5");
            d.setTitle("Input");
            d.setHeaderText("Recommend between 4-5");
            Optional<String> result = d.showAndWait();
            if (result.isPresent()) {
                localPlayer.numOfFrames = Integer.parseInt(result.get());
                buildFrames();
            }
        });
        top.getChildren().add(frameNumLabel);
        top.getChildren().add(frameNumButton);
        root.setTop(top);
    }

    void buildFrames() {
        VBox left = new VBox();
        localPlayer.buildFrames();
        for (int i = 0; i < localPlayer.numOfFrames; i++) {
            Button editFrameButton = new Button("Edit frame #" + (i + 1));
            editFrameButton.setId(i + "");
            left.getChildren().add(editFrameButton);
            editFrameButton.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            int idNumber = Integer.parseInt(((Button) event.getSource()).getId());
                            localPlayer.frames[idNumber] = new Frame(idNumber);
                            updateFrames();
                        }
                    }
            );
        }
        root.setLeft(left);
        frameBox = new VBox();
        for (int i = 0; i < localPlayer.numOfFrames; i++) {
            if (localPlayer.frames[i] != null) {
                FrameIcon f = new FrameIcon(localPlayer.frames[i]);
                f.setId((1 + i) + "");
                frameBox.getChildren().add(f);
            }
        }
        root.setCenter(frameBox);
    }


    void updateFrames() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < localPlayer.numOfFrames; i++) {
            if (localPlayer.frames[i] != null)
                System.out.println(localPlayer.frames[i]);
            else
                System.out.println("no frame yet");
        }
        frameBox.getChildren().clear();
        for (int i = 0; i < localPlayer.numOfFrames; i++) {
            if (localPlayer.frames[i] != null) {
                FrameIcon f = new FrameIcon(localPlayer.frames[i]);
                f.setId((1 + i) + "");
                frameBox.getChildren().add(f);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


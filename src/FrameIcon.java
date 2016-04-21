import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by 40095 on 4/20/16.
 */
public class FrameIcon extends BorderPane {
    Frame frame;
    Label DEF, MVM, MLE, DF, ART, COM;
    String url = "placehold.png";
    
    FrameIcon(Frame frame) {
        this.frame = frame;
        DEF = new Label();
        DEF.getStyleClass().add("DEF");
        MVM = new Label();
        MVM.getStyleClass().add("MVM");
        MLE = new Label();
        MLE.getStyleClass().add("weapon");
        DF = new Label();
        DF.getStyleClass().add("weapon");
        ART = new Label();
        ART.getStyleClass().add("weapon");
        COM = new Label();
        COM.getStyleClass().add("COM");
        VBox left = new VBox();
        left.getChildren().addAll(DEF, MVM, COM);
        VBox right = new VBox();
        right.getChildren().addAll(MLE, DF, ART);
        ImageView token = new ImageView(new Image(url, 100.0, 120.0, true, true, true));
        token.getStyleClass().add("token-image");
        token.setFitHeight(120);
        token.setFitWidth(100);
        this.setLeft(left);
        this.setCenter(token);
        this.setRight(right);
        render();
        this.setMaxHeight(120);
        this.setMaxWidth(100);
    }
    
    public void render() {
        DEF.setText(frame.systems.getNumOfSystemByType("DEF") + "");
        MVM.setText(frame.systems.getNumOfSystemByType("MVM") + "");
        MLE.setText(frame.systems.getNumOfSystemByType("MLE") + "");
        DF.setText (frame.systems.getNumOfSystemByType("DF") + "");
        ART.setText(frame.systems.getNumOfSystemByType("ART") + "");
        COM.setText(frame.systems.getNumOfSystemByType("COM") + "");
    }
}

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Optional;

/**
 * Created by 40095 on 4/20/16.
 */
public class Frame {
    Systems systems;    //# of each type of system: DEF, MVM, MLE, DF, ART, COM
    int whiteDice;      //# of white dice (decreased after all systems destroyed)
    boolean active;     //has the entire frame been destroyed
    int frameSystems;

    public Frame() {
        active = true;
        systems = new Systems(new int[]{1, 0, 1, 1, 0, 1});
        whiteDice = 2;
        frameSystems = 4;

    }

    public Frame(int idNumber) {
        active = true;
        whiteDice = 2;
        Dialog<Systems> dialog = new Dialog();

        dialog.setTitle("Editing frame " + idNumber);
        dialog.setHeaderText("Cannot have more than 2 of any system or more than 4 total");

        TextField DEFtext = new TextField("");
        DEFtext.setPrefColumnCount(1);
        HBox DEFbox = new HBox();
        DEFbox.getChildren().addAll(new Label("Defensive"), DEFtext);

        TextField MVMtext = new TextField("");
        MVMtext.setPrefColumnCount(1);
        HBox MVMbox = new HBox();
        MVMbox.getChildren().addAll(new Label("Movement"), MVMtext);

        TextField MLEtext = new TextField("");
        MLEtext.setPrefColumnCount(1);
        HBox MLEbox = new HBox();
        MLEbox.getChildren().addAll(new Label("Melee"), MLEtext);

        TextField DF_text = new TextField("");
        DF_text.setPrefColumnCount(1);
        HBox DF_box = new HBox();
        DF_box.getChildren().addAll(new Label("Direct Fire"), DF_text);

        TextField ARTtext = new TextField("");
        ARTtext.setPrefColumnCount(1);
        HBox ARTbox = new HBox();
        ARTbox.getChildren().addAll(new Label("Artillery"), ARTtext);

        TextField COMtext = new TextField("");
        COMtext.setPrefColumnCount(1);
        HBox COMbox = new HBox();
        COMbox.getChildren().addAll(new Label("Communications"), COMtext);

        VBox pane = new VBox();
        pane.getChildren().addAll(DEFbox, MVMbox, MLEbox, DF_box, ARTbox, COMbox);

        dialog.getDialogPane().setContent(pane);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, Systems>() {
            @Override
            public Systems call(ButtonType param) {
                if (param == buttonTypeOk) {
                    int DEF_Systems = Integer.parseInt(DEFtext.getText());
                    int MVM_Systems = Integer.parseInt(MVMtext.getText());
                    int MLE_Systems = Integer.parseInt(MLEtext.getText());
                    int DF_Systems = Integer.parseInt(DF_text.getText());
                    int ART_Systems = Integer.parseInt(ARTtext.getText());
                    int COM_Systems = Integer.parseInt(COMtext.getText());
                    int[] args = {DEF_Systems, MVM_Systems, MLE_Systems, DF_Systems, ART_Systems, COM_Systems};
                    frameSystems = 0;
                    for (int i = 0; i < args.length; i++) {
                        if (args[i] > 2) {
                            flipShit(SystemType.types[i]);
                            active = false;
                            return null;
                        }
                        frameSystems += args[i];
                    }
                    if (frameSystems > 4) {
                        flipShit("total");
                        active = false;
                        return null;
                    }
                    return new Systems(args);
                }
                return null;
            }

        });

        Optional<Systems> result = dialog.showAndWait();
        if (result.isPresent()) {
            systems = result.get();
        }
    }

    public void flipShit(String error) {
        Alert fuckYou = new Alert(Alert.AlertType.CONFIRMATION);
        fuckYou.setTitle("Illegal selection");
        fuckYou.setHeaderText("Selected configuration is illegal");
        fuckYou.setContentText("Too many " + error + " systems");
        fuckYou.showAndWait();
    }

    public void takeDamage(int damage) {
    }

    public void removeSystems(int damage) {
        //TODO: prompt the user to select which systems to remove.
        //list current systems
        //deincrement systems[i] for each selected system.
    }

    boolean isActive() {
        return active;
    }

    public String toString() {
        String desc = "";
        if (isActive())
            desc += "active| ";
        else
            desc += "destroyed";
        if(this == null) {
            return "No frame yet";
        }
        for (int i = 0; i < SystemType.NUMBER_OF_SYSTEM_TYPES; i++) {
            if (systems.systemsByType.get(SystemType.types[i]) != null)
                desc += SystemType.types[i] + "x" + systems.systemsByType.get(SystemType.types[i]).intValue() + " ";
            else
                desc += SystemType.types[i] + "x0 ";
        }
        return desc;
    }
}

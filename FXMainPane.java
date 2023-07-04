import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * 
 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
 * wait to add a component to its containing component until the container has
 * been created.  This is the only constraint on the order in which the following 
 * statements appear.
 * 
 * The DataManager class should never depend on the GUI, but rather the reverse. 
 * So the DataManager methods should not use the GUI directly.  If you want data
 * to get from the user to the manager, have the GUI get the data, and call the manager 
 * with the data that the GUI got from a text field or other data structure.
 * 
 * The FXMainPane class extends VBox to serve as the main panel for the GUI.
 * It contains buttons, a label, and a text field, organized in HBoxes and VBox.
 * The buttons have event handlers to respond to mouse clicks and interact with
 * the DataManager to retrieve data and update the text field.
 * 
 * The layout and positioning of components are handled using HBox, VBox, and
 * various alignment and margin settings.
 * 
 * This class is part of the JavaFX GUI example program.
 * 
 * @author ralexander
 */
public class FXMainPane extends VBox {

    //student Task #2:
    //  declare five buttons, a label, and a textfield
    //  declare two HBoxes
    private Button helloButton;
    private Button howdyButton;
    private Button chineseButton;
    private Button clearButton;
    private Button exitButton;
    private Label feedbackLabel;
    private TextField textField;
    private HBox buttonBox;
    private HBox feedbackBox;

    //student Task #4:
    //  declare an instance of DataManager
    private DataManager dataManager;

    /**
     * The MainPanel constructor sets up the entire GUI.
     */
    FXMainPane() {
        // Constructor implementation

        //student Task #2:
        //  instantiate the buttons, label, and textfield
        //  instantiate the HBoxes
        helloButton = new Button("Hello");
        howdyButton = new Button("Howdy");
        chineseButton = new Button("Chinese");
        clearButton = new Button("Clear");
        exitButton = new Button("Exit");

        buttonBox = new HBox();
        feedbackBox = new HBox();

        feedbackLabel = new Label("Feedback");
        textField = new TextField();

        //student Task #4:
        //  instantiate the DataManager instance
        dataManager = new DataManager();

        //  set margins and set alignment of the components
        buttonBox.getChildren().addAll(helloButton, howdyButton, chineseButton, clearButton, exitButton);

        // Set spacing between components in the HBoxes
        buttonBox.setSpacing(10);
        feedbackBox.setSpacing(10);

        // Add the HBoxes to the VBox
        getChildren().addAll(buttonBox, feedbackBox);
        feedbackBox.getChildren().addAll(feedbackLabel, textField);
        HBox.setMargin(helloButton, new Insets(10));
        HBox.setMargin(howdyButton, new Insets(10));
        HBox.setMargin(chineseButton, new Insets(10));
        HBox.setMargin(clearButton, new Insets(10));
        HBox.setMargin(exitButton, new Insets(10));

        // Set alignment for buttonBox
        buttonBox.setAlignment(Pos.CENTER);

        // Set margins for label and textField in feedbackBox
        HBox.setMargin(feedbackLabel, new Insets(10));
        HBox.setMargin(textField, new Insets(10));

        // Set alignment for feedbackBox
        feedbackBox.setAlignment(Pos.CENTER);

        // Set spacing between components in the VBox
        setSpacing(10);

        // Set alignment for the VBox
        setAlignment(Pos.CENTER);

        //student Task #3:
        //  add the event handlers to the buttons
        helloButton.setOnAction(new ButtonHandler());
        howdyButton.setOnAction(new ButtonHandler());
        chineseButton.setOnAction(new ButtonHandler());
        clearButton.setOnAction(new ButtonHandler());
        exitButton.setOnAction(new ButtonHandler());
    }

    //student Task #4:
    //  create a private inner class to handle the button clicks
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();
            if (source == helloButton || source == howdyButton || source == chineseButton) {
                String response;
                if (source == helloButton) {
                    response = dataManager.getHello();
                } else if (source == howdyButton) {
                    response = dataManager.getHowdy();
                } else {
                    response = dataManager.getChinese();
                }
                textField.setText(response);
            } else if (source == clearButton) {
                textField.setText("");
            } else if (source == exitButton) {
                Platform.exit();
                System.exit(0);
            }
        }
    }
}
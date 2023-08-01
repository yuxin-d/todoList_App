package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
import ui.AddTask;
import ui.EditTask;
import ui.ListView;
import ui.PomoTodoApp;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
import sun.rmi.runtime.Log;
import ui.EditTask;
import ui.ListView;
import ui.PomoTodoApp;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Controller class for Todobar UI
public class TodobarController implements Initializable {
    private static final String todoOptionsPopUpFXML = "resources/fxml/TodoOptionsPopUp.fxml";
    private static final String todoActionsPopUpFXML = "resources/fxml/TodoActionsPopUp.fxml";
    private File todoActionsPopUpFxmlFile = new File(todoActionsPopUpFXML);
    private File todoOptionsPopUpFxmlFile = new File(todoOptionsPopUpFXML);


    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXHamburger todoActionsPopUpBurger;
    @FXML
    private StackPane todoActionsPopUpContainer;
    @FXML
    private JFXRippler todoOptionsPopUpRippler;
    @FXML
    private StackPane todoOptionsPopUpBurger;

    private Task task;
    private JFXPopup todobarPopUp;
    private JFXPopup optionPopUp;

    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: sets the task in this Todobar
    //          updates the Todobar UI label to task's description
    public void setTask(Task task) {
        this.task = task;
        descriptionLabel.setText(task.getDescription());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTodobarPopUp();
        loadTodobarPopUpActionListener();
        loadTodoOptionsPopUp();
        loadTodoOptionsPopUpActionListener();
    }

    private void loadTodobarPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoActionsPopUpFxmlFile.toURI().toURL());
            fxmlLoader.setController(new TodobarPopUpController());
            todobarPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadTodoOptionsPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoOptionsPopUpFxmlFile.toURI().toURL());
            fxmlLoader.setController(new ViewOptionsPopUpController());
            optionPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    // EFFECTS: show view selector pop up when its icon is clicked
    private void loadTodoOptionsPopUpActionListener() {
        todoOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                optionPopUp.show(todoOptionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.LEFT,
                        12,
                        15);
            }
        });
    }

    // EFFECTS: show options pop up when its icon is clicked
    private void  loadTodobarPopUpActionListener() {
        todoActionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                todobarPopUp.show(todoActionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.RIGHT,
                        -12,
                        15);
            }
        });
    }



    // Inner class: view selector pop up controller
    class ViewOptionsPopUpController {
        @FXML
        private JFXListView<?> optionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = optionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarOptionsPopUpController", "Edit");
                    PomoTodoApp.setScene(new EditTask(task));



                    break;
                case 1:
                    Logger.log("TodobarOptionsPopUpController", "Delete");
                    PomoTodoApp.getTasks().remove(task);
                    PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));
//                    Platform.exit();
                    break;
                default:
                    Logger.log("TodobarOptionsPopUpController", "No action is implemented for the selected option");
            }
            optionPopUp.hide();

            //                case 0:
//                    Logger.log("TodobarActionsPopUpController", "List View Selected");
//                    break;
//                case 1:
//                    Logger.log("TodobarActionsPopUpController", "Priority View is not supported in this version!");
//                    break;
//                case 2:
//                    Logger.log("TodobarActionsPopUpController", "Status View is not supported in this version!");
//                    break;
//                default:
//                    Logger.log("TodobarActionsPopUpController", "No action is implemented for the selected option");
//            }
//            optionPopUp.hide();
        }
    }

    // Inner class: option pop up controller
    class TodobarPopUpController {
        @FXML
        private JFXListView<?> actionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = actionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarActionsPopUpController", "List View Selected");
                    break;
                case 1:
                    Logger.log("TodobarActionsPopUpController", "Priority View is not supported in this version!");
                    break;
                case 2:
                    Logger.log("TodobarActionsPopUpController", "Status View is not supported in this version!");
                    break;
                default:
                    Logger.log("TodobarActionsPopUpController", "No action is implemented for the selected option");
            }
            todobarPopUp.hide();

            // Logger.log("TodobarOptionsPopUpController", "Setting is not supported in this version");
//                    break;
//                case 1:
//                    Logger.log("TodobarOptionsPopUpController", "Close application");
//                    Platform.exit();
//                    break;
//                default:
//                    Logger.log("TodobarOptionsPopUpController", "No action is implemented for the selected option");
//            }
//            todobarPopUp.hide();
        }
    }
}

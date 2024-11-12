import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private CriminalDatabase criminalDatabase;
    private VBox mainLayout; // Main layout container

    public MainApp() {
        this.criminalDatabase = new CriminalDatabase();
    }

    @Override
    public void start(Stage primaryStage) {
        mainLayout = new VBox();
        mainLayout.setSpacing(10);
        
        // Initial main menu UI
        showMainMenu();

        Scene scene = new Scene(mainLayout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    
        primaryStage.setTitle("Criminal Database System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainMenu() {
        mainLayout.getChildren().clear();

        Label heading = new Label("Criminal Database System");
        heading.getStyleClass().add("heading");

        Button addCriminalButton = new Button("Add New Criminal");
        addCriminalButton.setOnAction(e -> showAddCriminalForm());

        Button searchCriminalButton = new Button("Search Criminal by ID");
        searchCriminalButton.setOnAction(e -> showSearchCriminalForm());

        mainLayout.getChildren().addAll(heading, addCriminalButton, searchCriminalButton);
    }

    private void showAddCriminalForm() {
        mainLayout.getChildren().clear();

        Label idLabel = new Label("Criminal ID:");
        TextField idField = new TextField();

        Label nameLabel = new Label("Criminal Name:");
        TextField nameField = new TextField();

        Label crimeLabel = new Label("Crime:");
        TextField crimeField = new TextField();

        Button saveButton = new Button("Save Criminal");
        saveButton.setOnAction(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String crime = crimeField.getText();

            if (id.isEmpty() || name.isEmpty() || crime.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "All fields are required!");
                alert.showAndWait();
                return;
            }

            Criminal newCriminal = new Criminal(id, name, crime);
            criminalDatabase.addCriminal(newCriminal);

            Alert alert = new Alert(AlertType.INFORMATION, "Criminal added successfully!");
            alert.showAndWait();
            showMainMenu(); // Return to main menu after saving
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> showMainMenu());

        mainLayout.getChildren().addAll(idLabel, idField, nameLabel, nameField, crimeLabel, crimeField, saveButton, backButton);
    }

    private void showSearchCriminalForm() {
        mainLayout.getChildren().clear();

        Label searchLabel = new Label("Enter Criminal ID to Search:");
        TextField searchField = new TextField();

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String id = searchField.getText();

            if (id.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Criminal ID is required!");
                alert.showAndWait();
                return;
            }

            Criminal criminal = criminalDatabase.getCriminal(id);
            if (criminal != null) {
                Alert alert = new Alert(AlertType.INFORMATION, 
                    "Criminal Found:\nID: " + criminal.getId() +
                    "\nName: " + criminal.getName() + 
                    "\nCrime: " + criminal.getCrime());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Criminal not found!");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> showMainMenu());

        mainLayout.getChildren().addAll(searchLabel, searchField, searchButton, backButton);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

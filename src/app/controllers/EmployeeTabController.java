package app.controllers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.OracleConnectionDistributer;
import entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.bson.Document;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeTabController implements Initializable, OracleConnectionDistributer {

    // Add Employee Attributes
    public TextField cnicFieldAdd;
    public TextField emailFieldAdd;
    public TextField passwordFieldAdd;
    public TextField salaryFieldAdd;
    public DatePicker joiningDateAdd;
    public Button resetButtonAdd;
    public Button saveButtonAdd;
    // REMOVE Employee Attributes
    public TextField searchFieldRemove;
    public Button searchButtonRemove;
    public TextArea queriedTextAreaRemove;
    public DatePicker removeLeaveDate;
    public Button resetButtonRemove;
    public Button removeButtonRemove;
    // Update Employee Attributes
    public TextField searchFieldUpdate;
    public Button searchButtonUpdate;
    public TextArea queriedTextAreaUpdate;
    public ComboBox<String> attributeUpdate;
    public TextField newValueFieldUpdate;
    public DatePicker newDateUpdate;
    public Button resetButtonUpdate;
    public Button updateButtonUpdate;
    // SEARCH employee
    public TextField searchFieldSearch;
    public Button searchButtonSearch;
    public TextArea queriedTextAreaSearch;
    // Saving the Employee Instance
    private Employee queriedEmployee;

    MongoCollection<Document> collection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initializing List of value for Update Employee ComboBox
        initializeUpdateCombox();

        // Mongo Initialization
        String uri = "mongodb+srv://saadi:roy@cluster0.25mazny.mongodb.net";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("ClothingStore");
            collection = database.getCollection("User");

        }
    }
    // Search
    @FXML
    private void searchEmployee(ActionEvent e) throws SQLException {
        String displayText;

        // Validating non Blank input
        if (e.getSource().equals(searchButtonSearch)) {
            if (searchFieldSearch.getText().isBlank()) emptyFieldAlert("Search");
            else {
                displayText = getEmployeeDetails(searchFieldSearch.getText());
                queriedTextAreaSearch.setText(displayText);
            }
        } else if (e.getSource().equals(searchButtonRemove)) {
            if (searchFieldRemove.getText().isBlank()) emptyFieldAlert("Remove");
            else {
                displayText = getEmployeeDetails(searchFieldRemove.getText());
                queriedTextAreaRemove.setText(displayText);
            }
        } else if (e.getSource().equals(searchButtonUpdate)) {
            if (searchFieldUpdate.getText().isBlank()) emptyFieldAlert("Update");
            else {
                displayText = getEmployeeDetails(searchFieldUpdate.getText());
                queriedTextAreaUpdate.setText(displayText);
            }
        }
    }

    private String getEmployeeDetails(String email) throws SQLException {
        Employee e = OracleConnectionDistributer.getAndParseEmployee(email);
        if (e != null) {
            queriedEmployee = e;
            return e.toString();
        }

        return "No Such Employee Found!";
    }

    private void initializeUpdateCombox() {
        // Initializing List of value for Update Employee ComboBox
        String[] employee_attribute_list = new String[]{
                "JOINING_DATE",
                "LEAVE_DATE",
                "SALARY",
                "CNIC",
                "PASSWORD",
                "EMAIL"
        };

        attributeUpdate.getItems().addAll(employee_attribute_list);

        attributeUpdate.setOnAction(e -> {
            if (attributeUpdate.getValue().equals("JOINING_DATE") || attributeUpdate.getValue().equals("LEAVE_DATE")) {
                newDateUpdate.setVisible(true);
                newValueFieldUpdate.setVisible(false);
            } else {
                newDateUpdate.setVisible(false);
                newValueFieldUpdate.setVisible(true);
            }
        });
    }

    // Empty field alert
    private void emptyFieldAlert(String fieldName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Empty Required Field");
        alert.setTitle("");
        alert.setContentText(fieldName + " field is required and cannot be EMPTY!");
        alert.show();
    }

    @FXML
    private void updateEmployee(ActionEvent e) throws SQLException {
        if (!attributeUpdate.getValue().isEmpty()) {
            if (newValueFieldUpdate.isVisible() && newValueFieldUpdate.getText().isBlank())
                emptyFieldAlert("New Value");
                // todo - add check for empty date Picker value
            else {
                if (newDateUpdate.isVisible())
                    OracleConnectionDistributer.updateEmployee(queriedEmployee.getU_id(), attributeUpdate.getValue(), newDateUpdate.getValue());
                int result = OracleConnectionDistributer.updateEmployee(queriedEmployee.getU_id(), attributeUpdate.getValue(), newValueFieldUpdate.getText());

                if (result == 1) clearUpdateForm();
            }
        }
    }

    @FXML
    private void clearUpdateForm() {
        newDateUpdate.setValue(null);
        newValueFieldUpdate.clear();
        searchFieldUpdate.clear();
        queriedTextAreaUpdate.clear();
    }


    /*REMOVE EMPLOYEE*/
    @FXML
    private void removeEmployee() throws SQLException {

        int result = OracleConnectionDistributer.removeEmployee(queriedEmployee.getE_ID());

        if (result == 1) resetRemoveForm();

        /*-------------------- Mongo ----------------------*/
//        collection.deleteOne({"CNIC":queriedEmployee.getCnic()});
    }

    @FXML
    private void resetRemoveForm() {
        queriedTextAreaRemove.clear();
        searchFieldRemove.clear();
    }

    @FXML
    private void addEmployee() throws SQLException {

        final int uid = OracleConnectionDistributer.getUsersCount();
        final int eid = OracleConnectionDistributer.getEmployeesCount();

        String cnic = cnicFieldAdd.getText();
        String email = emailFieldAdd.getText();
        String password = passwordFieldAdd.getText();
        double salary = Double.parseDouble(salaryFieldAdd.getText());
        Date joiningDate = Date.valueOf(joiningDateAdd.getValue());

        Employee newEmployee = new Employee(uid, eid, joiningDate, null, "Employee", cnic, password, email, salary);

        int result = OracleConnectionDistributer.insertEmployee(newEmployee);

        if (result == 1) resetAddForm();


        /*----------------- MONGO ---------------------*/
        Document employee = new Document("CNIC", cnic).append("Email", email).append("Password", password).append("Type", "Employee").append("Joining_Date", joiningDate.toString()).append("leave_date", "").append("Salary", salary);
        collection.insertOne(employee);

    }

    @FXML
    private void resetAddForm() {
        joiningDateAdd.setValue(null);
        cnicFieldAdd.clear();
        emailFieldAdd.clear();
        passwordFieldAdd.clear();
        salaryFieldAdd.clear();
    }
}

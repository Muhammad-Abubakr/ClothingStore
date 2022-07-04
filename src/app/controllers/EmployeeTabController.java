package app.controllers;

import database.ConnectionDistributor;
import entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeTabController implements Initializable, ConnectionDistributor {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initializing List of value for Update Employee ComboBox
        initializeUpdateCombox();
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
        Employee e = ConnectionDistributor.getAndParseEmployee(email);
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
                    ConnectionDistributor.updateEmployee(queriedEmployee.getU_id(), attributeUpdate.getValue(), newDateUpdate.getValue());
                int result = ConnectionDistributor.updateEmployee(queriedEmployee.getU_id(), attributeUpdate.getValue(), newValueFieldUpdate.getText());

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
        Date leaveDate = Date.valueOf(removeLeaveDate.getValue());

        int result = ConnectionDistributor.removeEmployee(queriedEmployee.getE_ID(), leaveDate);

        if (result == 1) resetRemoveForm();
    }

    @FXML
    private void resetRemoveForm() {
        removeLeaveDate.setValue(null);
        queriedTextAreaRemove.clear();
        searchFieldRemove.clear();
    }

    @FXML
    private void addEmployee() throws SQLException {

        final int uid = ConnectionDistributor.getUsersCount();
        final int eid = ConnectionDistributor.getEmployeesCount();

        String cnic = cnicFieldAdd.getText();
        String email = emailFieldAdd.getText();
        String password = passwordFieldAdd.getText();
        double salary = Double.parseDouble(salaryFieldAdd.getText());
        Date joiningDate = Date.valueOf(joiningDateAdd.getValue());

        Employee newEmployee = new Employee(uid, eid, joiningDate, null, "Employee", cnic, password, email, salary);

        int result = ConnectionDistributor.insertEmployee(newEmployee);

        if (result == 1) resetAddForm();
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

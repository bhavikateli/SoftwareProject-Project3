package payroll;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class Controller {

    private Company company = new Company();


    @FXML
    private TextArea outputArea;

    @FXML
    private TextField nameTextField;

    @FXML
    private RadioButton csButton;

    @FXML
    private ToggleGroup departmentType;

    @FXML
    private RadioButton itButton;

    @FXML
    private RadioButton eceButton;

    @FXML
    private DatePicker dateHiredTextField;

    @FXML
    private RadioButton fullTimeButton;

    @FXML
    private ToggleGroup employeeType;

    @FXML
    private RadioButton partTimeButton;

    @FXML
    private RadioButton managementButton;

    @FXML
    private TextField annualSalaryTextField;

    @FXML
    private TextField hoursWorkedTextField;

    @FXML
    private TextField rateTextField;

    @FXML
    private RadioButton managerButton;

    @FXML
    private ToggleGroup managementType;

    @FXML
    private RadioButton departmentHeadButton;

    @FXML
    private RadioButton directionButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button removeEmployeeButton;

    @FXML
    private Button setHoursButton;

    @FXML
    private MenuButton fileMenuButton;

    /**
     * Method when clear button is selected to clear all fields
     */
    @FXML
    void clearScreen() {
        nameTextField.clear();

        annualSalaryTextField.clear();

        hoursWorkedTextField.clear();

        rateTextField.clear();

        dateHiredTextField.getEditor().clear();

        if(departmentType.getSelectedToggle() != null){
            departmentType.getSelectedToggle().setSelected(false);
        }

        if(employeeType.getSelectedToggle() != null){
            employeeType.getSelectedToggle().setSelected(false);
        }

        if(managementType.getSelectedToggle() != null){
            managementType.getSelectedToggle().setSelected(false);
        }

        managerButton.setDisable(false);
        departmentHeadButton.setDisable(false);
        directionButton.setDisable(false);
        annualSalaryTextField.setDisable(false);
        hoursWorkedTextField.setDisable(false);
        rateTextField.setDisable(false);

        outputArea.clear();
    }

    /**
     * Method to disable options according to selected employee type
     */
    @FXML
    public void disableOptions() {
        RadioButton status = (RadioButton) employeeType.getSelectedToggle();
        String employeeType = status.getText();

        if(employeeType.equals("Management")) {
            managerButton.setDisable(false);
            departmentHeadButton.setDisable(false);
            directionButton.setDisable(false);
            annualSalaryTextField.setDisable(false);
            hoursWorkedTextField.setDisable(true);
            rateTextField.setDisable(true);
        } else if(employeeType.equals("Part Time")){
            managerButton.setDisable(true);
            departmentHeadButton.setDisable(true);
            directionButton.setDisable(true);
            annualSalaryTextField.setDisable(true);
            hoursWorkedTextField.setDisable(false);
            rateTextField.setDisable(false);
        } else if(employeeType.equals("Full Time")){
            managerButton.setDisable(true);
            departmentHeadButton.setDisable(true);
            directionButton.setDisable(true);
            annualSalaryTextField.setDisable(false);
            hoursWorkedTextField.setDisable(true);
            rateTextField.setDisable(true);
        }

    }

    /**
     * Helper method to create profile when adding employee
     * @return Profile of current employee
     */
    @FXML
    Profile getProfile() {
        try {
            RadioButton departmentTypeSelectedToggle = (RadioButton) departmentType.getSelectedToggle();
            String tempDepartment = departmentTypeSelectedToggle.getText();
            String tempName = nameTextField.getText();

            LocalDate tempLocalDate = dateHiredTextField.getValue();
            Date tempDate = new Date("" + tempLocalDate.getMonthValue() + "/" + tempLocalDate.getDayOfMonth() + "/" + tempLocalDate.getYear());

            if (tempDate.isValid() == false) {
                outputArea.appendText("Please input a valid date");
                return null;
            }

            Profile tempProfile = new Profile(tempName,tempDepartment, tempDate);
            return  tempProfile;

        } catch(NullPointerException e ){
            outputArea.appendText("Please fill out all the required fields");
            return null;
        }

    }

    /**
     * Helper method to create part-time employee when adding employee
     * @return Profile of current employee
     */
    @FXML
    void partTimeEmployee(Profile profile) {
        try{
            String salaryString = rateTextField.getText();
            double tempSalary = Double.parseDouble(salaryString);
            if (tempSalary >= 0) {
                Employee employee = new Parttime(profile, tempSalary);
                if (!company.add(employee))
                    outputArea.appendText("Employee is already in the list.");
                else
                    outputArea.appendText("Employee added.");
            } else {
                System.out.println("Pay rate cannot be negative");
            }
        }catch(NullPointerException e){
            outputArea.appendText("Please fill out all requirements");
        }
    }

    /**
     * Helper method to create full-time employee when adding employee
     * @return Profile of current employee
     */
    @FXML
    void fullTimeEmployee() {}

    /**
     * Helper method to create management employee when adding employee
     * @return Profile of current employee
     */
    @FXML
    void managementEmployee() {}

    /**
     * Method to give functionality to the add employee button
     * @param event
     */
    @FXML
    void addEmployee(ActionEvent event) {
        try {
            RadioButton employeeTypeSelectedToggle = (RadioButton) employeeType.getSelectedToggle();
            String tempEmployeeType = employeeTypeSelectedToggle.getText();
            Profile tempProfile = getProfile();

            if(tempEmployeeType.equals("Management")){
                partTimeEmployee(tempProfile);
            }else if(tempEmployeeType.equals("Part Time")){

            }else if(tempEmployeeType.equals("Full Time")){

            }


        } catch(NullPointerException e ){
            outputArea.appendText("Please fill out all the required fields");
        }

    }

    @FXML
    void removeEmployee(ActionEvent event) {
        //outputArea.appendText("yooooo");

    }

    @FXML
    void setHours(ActionEvent event) {

    }

}


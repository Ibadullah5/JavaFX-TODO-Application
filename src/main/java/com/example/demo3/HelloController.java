package com.example.demo3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class HelloController implements Initializable{
    @FXML private Label mainLabel;

    @FXML protected TextField subjectTextField;

    @FXML private DatePicker myDatePicker;

    @FXML private TextField searchTF;

    @FXML private TextField timeTextField;

    @FXML private TextField descriptionTextField;

    @FXML private TextField locationTextField;

    @FXML private TableView<Task> table;

    @FXML private TableColumn<Task, String> subjectCol;

    @FXML private TableColumn<Task, String> dateCol;

    @FXML private TableColumn<Task, String> timeCol;

    @FXML private TableColumn<Task, String> descriptionCol;

    @FXML private TableColumn<Task, String> locationCol;

    @FXML private Button addButton;

    @FXML private Button removeButton;

    @FXML private Button searchButton;

    @FXML private Button showAllButton;

    ObservableList<Task> list = FXCollections.observableArrayList();

    String datePickerDate;

    File File = new File("File.txt");


    //Gives a String Representation of the date from the DatePicker
    @FXML
    public void getDate(ActionEvent event){
        LocalDate myDate = myDatePicker.getValue();
        datePickerDate = myDate.format(DateTimeFormatter.ofPattern("MMM,dd,yyyy"));
    }

    @FXML
    public void addButtonPress(ActionEvent event) throws IOException {
        if(subjectTextField.getText().equals("")||timeTextField.getText().equals("")
        ||descriptionTextField.getText().equals("")||locationTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter All Field Before Adding a Task");
        }
        else {
            String a = subjectTextField.getText();
            String b = datePickerDate;
            String c = timeTextField.getText();
            String d = descriptionTextField.getText();
            String e = locationTextField.getText();

            FileWriter fw = new FileWriter(File, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(a + "//" + b + "//" + c + "//" + d + "//" + e);
            pw.close();

            Task task = new Task(a, b, c, d, e);
            list.add(task);
        }
        subjectCol.setCellValueFactory(new PropertyValueFactory<Task, String>("subject"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Task, String>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Task, String>("time"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<Task, String>("location"));

        table.setItems(list);

        subjectTextField.setText("");timeTextField.setText("");
        descriptionTextField.setText("");locationTextField.setText("");
    }

    public void onSearchButtonPress(ActionEvent event){
            showAllButton.setOpacity(1);
            String a = searchTF.getText();
            ObservableList<Task> allRows = table.getItems();
            allRows.removeIf(task -> !a.equalsIgnoreCase(task.getSubject()));
        }

    public void onRemoveButtonPressed(ActionEvent event) {
        ObservableList<Task> selectedRow, allRows;

        allRows = table.getItems();
        selectedRow = table.getSelectionModel().getSelectedItems();

        FileWriter fw = null;
        try {
            fw = new FileWriter(File, false);
        } catch (Exception exception) {
            System.out.println("File not found");
        }
        PrintWriter pw = new PrintWriter(fw);
        for (Task task : selectedRow) {
            allRows.remove(task);
        }
        for(Task task : allRows) {
            pw.println(task.getSubject()+"//"+task.getDate()+"//"+task.getTime()
            +"//"+task.getDescription()+"//"+task.getLocation());
        }pw.close();
    }

    //Initialize Method reads the file and populates the TableView
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setEditable(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Scanner input = null;
        try {
            input = new Scanner(File);
        } catch (FileNotFoundException e) {
            System.out.println("Not Found");
        }
        String a="";String b="";String c="";String d="";String e="";
        while (true) {
            assert input != null;
            if (!input.hasNextLine()) break;
            String q = input.nextLine();
            Scanner s1 = new Scanner(q);
            s1.useDelimiter("//");
            a = s1.next();
            b = s1.next();
            c = s1.next();
            d = s1.next();
            e = s1.next();

            Task task = new Task(a, b, c, d, e);
            list.add(task);
        }

            subjectCol.setCellValueFactory(new PropertyValueFactory<Task, String>("subject"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Task, String>("date"));
            timeCol.setCellValueFactory(new PropertyValueFactory<Task, String>("time"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<Task, String>("location"));

            table.setItems(list);

            subjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
            dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            timeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
            locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void showAllButtonPressed(ActionEvent event) {
        for(Task task: list){
            System.out.println(task.getSubject());
        }
        list.clear();

        Scanner input = null;
        try {
            input = new Scanner(File);
        } catch (FileNotFoundException e) {
            System.out.println("Not Found");
        }

        String a="";String b="";String c="";String d="";String e="";

        while (true) {
            assert input != null;
            if (!input.hasNextLine()) break;
            String q = input.nextLine();
            Scanner s1 = new Scanner(q);
            s1.useDelimiter("//");
            a = s1.next();
            b = s1.next();
            c = s1.next();
            d = s1.next();
            e = s1.next();

            Task task = new Task(a, b, c, d, e);
            list.add(task);
        }

        subjectCol.setCellValueFactory(new PropertyValueFactory<Task, String>("subject"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Task, String>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Task, String>("time"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<Task, String>("location"));
    }

    //When a required cell in subject is double-clicked for editing
    public void onSubjectChange(TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) throws IOException {
    Task task = table.getSelectionModel().getSelectedItem();
    task.setSubject(taskStringCellEditEvent.getNewValue());
        FileWriter fw = new FileWriter(File, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Task task1: list){
            pw.println(task1.getSubject()+"//"+task1.getDate()+"//"+task1.getTime()
                    +"//"+task1.getDescription()+"//"+task1.getLocation());
        }pw.close();
         }

    //When a required cell in date is double-clicked for editing
    public void onDateChanged(TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) throws IOException {
        Task task = table.getSelectionModel().getSelectedItem();
        task.setDate(taskStringCellEditEvent.getNewValue());
        FileWriter fw = new FileWriter(File, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Task task1: list){
            pw.println(task1.getSubject()+"//"+task1.getDate()+"//"+task1.getTime()
                    +"//"+task1.getDescription()+"//"+task1.getLocation());
        }pw.close();
    }

    //When a required cell in time is double-clicked for editing
    public void onTimeChanged(TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) throws IOException {
        Task task = table.getSelectionModel().getSelectedItem();
        task.setTime(taskStringCellEditEvent.getNewValue());
        FileWriter fw = new FileWriter(File, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Task task1: list){
            pw.println(task1.getSubject()+"//"+task1.getDate()+"//"+task1.getTime()
                    +"//"+task1.getDescription()+"//"+task1.getLocation());
        }pw.close();
    }

    //When a required cell in description is double-clicked for editing
    public void onDescriptionChanged(TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) throws IOException {
        Task task = table.getSelectionModel().getSelectedItem();
        task.setDescription(taskStringCellEditEvent.getNewValue());
        FileWriter fw = new FileWriter(File, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Task task1: list){
            pw.println(task1.getSubject()+"//"+task1.getDate()+"//"+task1.getTime()
                    +"//"+task1.getDescription()+"//"+task1.getLocation());
        }pw.close();
    }

    //When a required cell in location is double-clicked for editing
    public void onLocationChanged(TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) throws IOException {
        Task task = table.getSelectionModel().getSelectedItem();
        task.setLocation(taskStringCellEditEvent.getNewValue());
        FileWriter fw = new FileWriter(File, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Task task1: list){
            pw.println(task1.getSubject()+"//"+task1.getDate()+"//"+task1.getTime()
                    +"//"+task1.getDescription()+"//"+task1.getLocation());
        }pw.close();
    }
}
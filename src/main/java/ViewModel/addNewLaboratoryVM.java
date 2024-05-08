package ViewModel;

import org.example.learn.JDBConnection;

import javax.swing.*;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addNewLaboratoryVM {
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    public void saveLaboratory (TextField textField, TextField textField1, TextField textField2){
        String sql = "Insert into Laboratory (laboName, contactNumber, addressLaboratory) Values (?,?,?)";
        String name = textField.getText().trim().toLowerCase();
        String contactNumber = textField1.getText().trim().toLowerCase();
        String address= textField2.getText();
        if (name.isEmpty()||contactNumber.isEmpty()||address.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all data");
        }
        else {
            try{
                connection = JDBConnection.NhaKhoa100eConnect();
                pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, contactNumber);
                pst.setString(3, address);
                int i =pst.executeUpdate();
                if (i==1){
                    JOptionPane.showMessageDialog(null, "Save data successfully");
                    resetText(textField, textField1, textField2);
                }
                pst.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void resetText(TextField textField, TextField textField1, TextField textField2) {
        textField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
    }

}

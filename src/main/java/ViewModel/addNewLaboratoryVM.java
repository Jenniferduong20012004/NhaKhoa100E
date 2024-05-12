package ViewModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addNewLaboratoryVM {
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    public void saveLaboratory (String name, String contactNumber, String address){
        String sql = "Insert into Laboratory (laboName, contactNumber, addressLaboratory) Values (?,?,?)";
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
                }
                pst.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

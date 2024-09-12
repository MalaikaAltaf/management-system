import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarDetailsWindow extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField colorField;
    private JTextField fuelTypeField;
    private JTextField priceField;
    private JTextField typeField;
    private JTextField transmissionField;
    private List<Cars> carList;
    public CarDetailsWindow(List<Cars> carList) {
        this.carList=carList;
        setTitle("Car Details");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        nameField = addTextField("Car Name:");
        colorField = addTextField("Car Color:");
        fuelTypeField = addTextField("Car Fuel Type:");
        priceField = addTextField("Car Price:");
        typeField = addTextField("Car Type:");
        transmissionField = addTextField("Car Number:");

        JButton addButton = new JButton("Add Car");
        addButton.addActionListener(this);
        add(addButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTextField addTextField(String labelText) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField();
        add(label);
        add(textField);
        return textField;
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Add Car")) {
        
        String carName = nameField.getText();
        String carColor = colorField.getText();
        String carFuelType = fuelTypeField.getText();
        int carPrice = Integer.parseInt(priceField.getText());
        String carType = typeField.getText();
        String carNumber = transmissionField.getText();
        
        Cars car=new Cars();
        car.car_name=carName;
        car.car_color=carColor;
        car.car_fuel_type=carFuelType;
        car.car_price =carPrice;
        car.car_type=carType;
        car.car_number=carNumber;
        carList.add(car);

        try {
            Connection con = DBconnector.getCon();
            if (con != null) {
                String query = "INSERT INTO Cars (car_name, car_color, car_fuel, car_price, car_type, car_no) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, carName);
                pst.setString(2, carColor);
                pst.setString(3, carFuelType);
                pst.setInt(4, carPrice);
                pst.setString(5, carType);
                pst.setString(6, carNumber);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Car added successfully!");
                // Clear fields after successful insertion
                nameField.setText("");
                colorField.setText("");
                fuelTypeField.setText("");
                priceField.setText("");
                typeField.setText("");
                transmissionField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error connecting to the database.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error adding car: " + ex.getMessage());
        }
    }
}
}

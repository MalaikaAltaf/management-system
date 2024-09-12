import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowroomDetailsWindow extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField managerField;
    private JTextField employeesField;
    private JTextField carsField;
    private List<Showroom> showrooms; // Declaration of showrooms list

    public ShowroomDetailsWindow(List<Showroom> showrooms) {
        this.showrooms = showrooms; // Initialize showrooms list

        setTitle("Showroom Details");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        nameField = addTextField("Showroom Name:");
        addressField = addTextField("Showroom Address:");
        managerField = addTextField("Manager Name:");
        employeesField = addTextField("Total Employees:");
        carsField = addTextField("Total Cars in Stock:");

        JButton addButton = new JButton("Add Showroom");
        addButton.addActionListener(this); // Adding the ActionListener to the button
        add(addButton);

        setVisible(true);
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
        // Handling the button click event
        if (e.getActionCommand().equals("Add Showroom")) {
            Showroom showroom = new Showroom();
            showroom.showroom_name = nameField.getText();
            showroom.showroom_address = addressField.getText();
            showroom.manager_name = managerField.getText();
            showroom.total_employees = Integer.parseInt(employeesField.getText());
            showroom.total_cars_in_stock = Integer.parseInt(carsField.getText());
            showrooms.add(showroom);
            JOptionPane.showMessageDialog(this, "Showroom added successfully!");
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDetailsWindow extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField departmentField;
    private JTextField showroomNameField;
    private List<Employees> employees;

    public EmployeeDetailsWindow(List<Employees> employees) {
        this.employees = employees;

        setTitle("Employee Details");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        nameField = addTextField("Employee Name:");
        ageField = addTextField("Employee Age:");
        departmentField = addTextField("Employee Department:");
        showroomNameField = addTextField("Showroom Name:");

        JButton addButton = new JButton("Add Employee");
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
        if (e.getActionCommand().equals("Add Employee")) {
            
            String empName = nameField.getText();
            int empAge = Integer.parseInt(ageField.getText());
            String empDepartment = departmentField.getText();
            String showroomName = showroomNameField.getText();

            Employees employee = new Employees();
            employee.emp_name = empName;
            employee.emp_age = empAge;
            employee.emp_department = empDepartment;
            employee.showroom_name = showroomName;
            employees.add(employee);

            try {
            Connection con = DBconnector.getCon();
            if (con != null) {

            String query = "INSERT INTO Employees (emp_name, emp_age, emp_department, showroom_name) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            

                preparedStatement.setString(1, empName);
                preparedStatement.setInt(2, empAge);
                preparedStatement.setString(3, empDepartment);
                preparedStatement.setString(4, showroomName);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding employee to database: " + sqlException.getMessage());
            }
        }
    }
    
}

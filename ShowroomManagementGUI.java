import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowroomManagementGUI {
    private JFrame frame;
    private List<Showroom> showrooms = new ArrayList<>();
    private List<Employees> employees = new ArrayList<>();
    private List<Cars> carList = new ArrayList<>();

    public ShowroomManagementGUI() {
        frame = new JFrame("Showroom Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(5, 2));

        addButton("Add Showroom Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShowroomDetailsWindow();
            }
        });

        addButton("Add Employees", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEmployeeDetailsWindow();
            }
        });

        addButton("Add Cars", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCarDetailsWindow();
            }
        });

        addButton("Get Showroom Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayShowroomDetails();
            }
        });

        addButton("Get Employees", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployeeDetails();
            }
        });

        addButton("Get Cars", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCarDetails();
            }
        });

        addButton("Sell Car", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSellCarWindow();
            }
        });

        addButton("Rent Car", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRentCarWindow();
            }
        });

        addButton("Get Sold Cars Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySoldCarDetails();
            }
        });

        addButton("Get Rented Cars Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRentedCarDetails();
            }
        });

        frame.setVisible(true);
    }

    private void addButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        frame.add(button);
    }

    private void openShowroomDetailsWindow() {
        new ShowroomDetailsWindow(showrooms);
    }

    private void openEmployeeDetailsWindow() {
        new EmployeeDetailsWindow(employees);
    }

    private void openCarDetailsWindow() {
        new CarDetailsWindow(carList);
    }

    private void openSellCarWindow() {
        new SellCarWindow(carList);
    }

    private void openRentCarWindow() {
        new RentCarWindow(carList);
    }

    private void displayShowroomDetails() {
        JTextArea textArea = new JTextArea();
        for (Showroom showroom : showrooms) {
            textArea.append(showroom.toString() + "\n\n");
        }
        showDetailsWindow("Showroom Details", textArea);
    }

    private void displayEmployeeDetails() {
        JTextArea textArea = new JTextArea();
        for (Employees employee : employees) {
            textArea.append(employee.toString() + "\n\n");
        }
        showDetailsWindow("Employee Details", textArea);
    }

    private void displayCarDetails() {
        JTextArea textArea = new JTextArea();
        for (Cars car : carList) {
            textArea.append(car.toString() + "\n\n");
        }
        showDetailsWindow("Car Details", textArea);
    }

    private void displaySoldCarDetails() {
        JTextArea textArea = new JTextArea();
        for (Cars car : Cars.soldCars) {
            textArea.append(car.toString() + "\n\n");
        }
        showDetailsWindow("Sold Car Details", textArea);
    }

    private void displayRentedCarDetails() {
        JTextArea textArea = new JTextArea();
        for (Cars car : Cars.rentedCars) {
            textArea.append(car.toString() + "\n\n");
        }
        showDetailsWindow("Rented Car Details", textArea);
    }

    private void showDetailsWindow(String title, JTextArea textArea) {
        JFrame detailsFrame = new JFrame(title);
        detailsFrame.setSize(400, 300);
        detailsFrame.add(new JScrollPane(textArea));
        detailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ShowroomManagementGUI();
    }
}

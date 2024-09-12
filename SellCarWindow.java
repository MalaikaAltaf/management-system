import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SellCarWindow extends JFrame implements ActionListener {

    private List<Cars> carList;

    public SellCarWindow(List<Cars> carList) {
        this.carList = carList;

        setTitle("Sell Car");
        setSize(400, 300);
        setLayout(new GridLayout(carList.size() + 1, 1));

        if (carList.isEmpty()) {
            JLabel noCarsLabel = new JLabel("No cars available in stock to sell.");
            add(noCarsLabel);
        } else {
            for (int i = 0; i < carList.size(); i++) {
                Cars car = carList.get(i);
                JButton carButton = new JButton(car.car_name + " - " + car.car_type + " - $" + car.car_price);
                carButton.addActionListener(this); // Adding the ActionListener to the button
                add(carButton);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling the button click event
        for (Cars car : carList) {
            if (e.getActionCommand().equals(car.car_name + " - " + car.car_type + " - $" + car.car_price)) {
                carList.remove(car);
                Cars.soldCars.add(car);
                JOptionPane.showMessageDialog(this, "Car sold successfully!");
                dispose();
                break;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cars extends Showroom implements utility {
    String car_name;
    String car_color;
    String car_fuel_type;
    int car_price;
    String car_type;
    String car_number;

    static List<Cars> soldCars = new ArrayList<>();
    static List<Cars> rentedCars = new ArrayList<>();

    @Override
    public void get_details() {
        System.out.println("NAME: " + car_name);
        System.out.println("COLOR: " + car_color);
        System.out.println("FUEL TYPE: " + car_fuel_type);
        System.out.println("PRICE: " + car_price);
        System.out.println("CAR TYPE: " + car_type);
        System.out.println("CAR NUMBER: " + car_number);
    }

    @Override
    public void set_details() {
        Scanner sc = new Scanner(System.in);
        System.out.println("======================= *** ENTER CAR DETAILS *** =======================");
        System.out.println();
        System.out.print("CAR NAME: ");
        car_name = sc.nextLine();
        System.out.print("CAR COLOR: ");
        car_color = sc.nextLine();
        System.out.print("CAR FUEL TYPE(PETROL/DIESEL): ");
        car_fuel_type = sc.nextLine();
        System.out.print("CAR PRICE: ");
        car_price = sc.nextInt();
        sc.nextLine();
        System.out.print("CAR TYPE(AUTOMATIC/MANUAL): ");
        car_type = sc.nextLine();
        System.out.print("CAR NUMBER: ");
        car_number = sc.nextLine();
        total_cars_in_stock++;
    }

    @Override
    public String toString() {
        return "Car Details: " +
                "\nName: " + car_name +
                "\nColor: " + car_color +
                "\nFuel Type: " + car_fuel_type +
                "\nPrice: " + car_price +
                "\nType: " + car_type +
                "\nNumber: " + car_number;
    }

    public static void sell_car(List<Cars> carList) {
        if (carList.isEmpty()) {
            System.out.println("No cars available in stock to sell.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Select the car to sell:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.print((i + 1) + "]. ");
            carList.get(i).get_details();
            System.out.println();
        }

        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < carList.size()) {
            Cars carToSell = carList.get(choice);
            carList.remove(choice);
            soldCars.add(carToSell);
            carToSell.total_cars_in_stock--;
            System.out.println("Car sold successfully!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void rent_car(List<Cars> carList) {
        List<Cars> availableCars = new ArrayList<>(carList);
        availableCars.removeAll(rentedCars);

        if (availableCars.isEmpty()) {
            System.out.println("No cars available in stock to rent.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Select the car to rent:");
        for (int i = 0; i < availableCars.size(); i++) {
            System.out.print((i + 1) + "]. ");
            availableCars.get(i).get_details();
            System.out.println();
        }

        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < availableCars.size()) {
            Cars carToRent = availableCars.get(choice);
            rentedCars.add(carToRent);
            System.out.println("Car rented successfully!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void get_sold_cars() {
        if (soldCars.isEmpty()) {
            System.out.println("No cars sold yet.");
        } else {
            System.out.println("======================= *** SOLD CARS DETAILS *** =======================");
            for (Cars car : soldCars) {
                car.get_details();
                System.out.println();
            }
        }
    }

    public static void get_rented_cars() {
        if (rentedCars.isEmpty()) {
            System.out.println("No cars rented yet.");
        } else {
            System.out.println("======================= *** RENTED CARS DETAILS *** =======================");
            for (Cars car : rentedCars) {
                car.get_details();
                System.out.println();
            }
        }
    }
}

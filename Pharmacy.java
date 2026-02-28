import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacy {

    // I used 3 lists to store medicine data
    static ArrayList<String> medicineNames = new ArrayList<String>();
    static ArrayList<Double> medicinePrices = new ArrayList<Double>();
    static ArrayList<Integer> medicineQuantities = new ArrayList<Integer>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // adding medicines
        medicineNames.add("Paracetamol");
        medicinePrices.add(2.5);1
        medicineQuantities.add(100);

        medicineNames.add("Amoxicillin");
        medicinePrices.add(5.0);
        medicineQuantities.add(50);

        medicineNames.add("Ibuprofen");
        medicinePrices.add(3.0);
        medicineQuantities.add(15);

        medicineNames.add("Aspirin");
        medicinePrices.add(1.5);
        medicineQuantities.add(0);

        medicineNames.add("Vitamin C");
        medicinePrices.add(4.0);
        medicineQuantities.add(200);

        System.out.println("Welcome to Pharmacy System");

        int choice = 0;

        while (choice != 3) {

            System.out.println("");
            System.out.println("1 - Search Medicine");
            System.out.println("2 - Show All Medicines");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine(); // clear the Enter after nextInt

            if (choice == 1) {
                searchAndBuy();
            } else if (choice == 2) {
                showAll();
            } else if (choice == 3) {
                System.out.println("Bye!");
            } else {
                System.out.println("Wrong choice, try again");
            }
        }
    }

    static void searchAndBuy() {
        System.out.print("Enter medicine name: ");
        String name = input.nextLine();

        // search for medicine
        int index = -1;
        for (int i = 0; i < medicineNames.size(); i++) {
            if (medicineNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Medicine not found");
            return;
        }

        if (medicineQuantities.get(index) == 0) {
            System.out.println("Medicine : " + medicineNames.get(index));
            System.out.println("Price    : $" + medicinePrices.get(index));
            System.out.println("Out of Stock!");
            return;
        }

        System.out.println("Medicine : " + medicineNames.get(index));
        System.out.println("Price    : $" + medicinePrices.get(index));
        System.out.println("In Stock : " + medicineQuantities.get(index));

        System.out.print("How many do you want? ");
        int qty = input.nextInt();
        input.nextLine(); // clear Enter again

        if (qty > medicineQuantities.get(index)) {
            System.out.println("Not enough stock!");
            System.out.println("We only have " + medicineQuantities.get(index) + " left.");
            return;
        }

        // sell
        double total = qty * medicinePrices.get(index);
        int newQty = medicineQuantities.get(index) - qty;
        medicineQuantities.set(index, newQty);

        System.out.println("Done!");
        System.out.println("Total    : $" + total);
        System.out.println("Remaining: " + newQty + " units");
    }

    static void showAll() {
        System.out.println("--- All Medicines ---");
        for (int i = 0; i < medicineNames.size(); i++) {
            String status = "";
            if (medicineQuantities.get(i) == 0) {
                status = " [OUT OF STOCK]";
            }
            System.out.println((i + 1) + ". " + medicineNames.get(i)
                + "  Price: $" + medicinePrices.get(i)
                + "  Stock: " + medicineQuantities.get(i)
                + status);
        }
    }
}


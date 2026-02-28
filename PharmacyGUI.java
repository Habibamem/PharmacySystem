import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class PharmacyGUI extends JFrame {

    // I used 3 lists to store medicine data
    static ArrayList<String> medicineNames = new ArrayList<String>();
    static ArrayList<Double> medicinePrices = new ArrayList<Double>();
    static ArrayList<Integer> medicineQuantities = new ArrayList<Integer>();

    JTextField nameField;
    JTextField qtyField;
    JTextArea resultArea;

    public PharmacyGUI() {

        // adding medicines
        medicineNames.add("Paracetamol");
        medicinePrices.add(2.5);
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

        // window setup
        setTitle("Pharmacy System");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        getContentPane().setBackground(new Color(30, 30, 30));

        // title
        JLabel title = new JLabel("Pharmacy System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 200, 150));
        add(title);

        // name input
        JLabel nameLabel = new JLabel("Medicine Name:");
        nameLabel.setForeground(Color.LIGHT_GRAY);
        add(nameLabel);

        nameField = new JTextField(28);
        nameField.setBackground(new Color(50, 50, 50));
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        add(nameField);

        // quantity input
        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setForeground(Color.LIGHT_GRAY);
        add(qtyLabel);

        qtyField = new JTextField(28);
        qtyField.setBackground(new Color(50, 50, 50));
        qtyField.setForeground(Color.WHITE);
        qtyField.setCaretColor(Color.WHITE);
        add(qtyField);

        // buttons
        JButton searchBtn = new JButton("Search & Buy");
        searchBtn.setBackground(new Color(0, 200, 150));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.setFocusPainted(false);
        add(searchBtn);

        JButton showAllBtn = new JButton("Show All");
        showAllBtn.setBackground(new Color(70, 70, 70));
        showAllBtn.setForeground(Color.WHITE);
        showAllBtn.setFocusPainted(false);
        add(showAllBtn);

        // result area
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.LIGHT_GRAY);
        add(resultLabel);

        resultArea = new JTextArea(10, 32);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(20, 20, 20));
        resultArea.setForeground(new Color(0, 200, 150));
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setMargin(new Insets(8, 10, 8, 10));
        resultArea.setText("Welcome! Search for a medicine.");
        add(new JScrollPane(resultArea));

        // buttons actions
        searchBtn.addActionListener(e -> searchAndBuy());
        showAllBtn.addActionListener(e -> showAll());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void searchAndBuy() {
        String name = nameField.getText().trim();
        String qtyText = qtyField.getText().trim();

        if (name.isEmpty()) {
            resultArea.setText("Please enter a medicine name!");
            return;
        }

        // search for medicine
        int index = -1;
        for (int i = 0; i < medicineNames.size(); i++) {
            if (medicineNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            resultArea.setText("Medicine not found!\nTry Show All to see available medicines.");
            return;
        }

        if (medicineQuantities.get(index) == 0) {
            resultArea.setText("Medicine : " + medicineNames.get(index)
                + "\nPrice    : $" + medicinePrices.get(index)
                + "\n\nSorry! Out of Stock!");
            return;
        }

        if (qtyText.isEmpty()) {
            resultArea.setText("Medicine : " + medicineNames.get(index)
                + "\nPrice    : $" + medicinePrices.get(index)
                + "\nIn Stock : " + medicineQuantities.get(index)
                + "\n\nEnter quantity to buy.");
            return;
        }

        int qty = Integer.parseInt(qtyText);

        if (qty > medicineQuantities.get(index)) {
            resultArea.setText("Not enough stock!\nWe only have "
                + medicineQuantities.get(index) + " left.");
            return;
        }

        // sell
        double total = qty * medicinePrices.get(index);
        int newQty = medicineQuantities.get(index) - qty;
        medicineQuantities.set(index, newQty);

        resultArea.setText("Done!\n\n"
            + "Medicine : " + medicineNames.get(index) + "\n"
            + "Quantity : " + qty + "\n"
            + "Total    : $" + total + "\n"
            + "Remaining: " + newQty + " units");

        nameField.setText("");
        qtyField.setText("");
    }

    void showAll() {
        String result = "--- All Medicines ---\n\n";
        for (int i = 0; i < medicineNames.size(); i++) {
            String status = "";
            if (medicineQuantities.get(i) == 0) {
                status = " [OUT OF STOCK]";
            }
            result += (i + 1) + ". " + medicineNames.get(i)
                + "\n   Price: $" + medicinePrices.get(i)
                + "  Stock: " + medicineQuantities.get(i)
                + status + "\n\n";
        }
        resultArea.setText(result);
    }

    public static void main(String[] args) {
        new PharmacyGUI();
    }
}
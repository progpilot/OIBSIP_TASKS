import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ReservationSystem extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel, titleLabel, nameLabel, emailLabel, phoneLabel, dateLabel, sourceLabel, destinationLabel, trainNumberLabel;
    private JTextField usernameField, nameField, emailField, phoneField, sourceField, destinationField, trainNumberField;
    private JPasswordField passwordField;
    private JButton loginButton, reserveButton, cancelButton;
    private JTextArea reservationArea;
    private JComboBox<String> dateBox;
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private User currentUser;

    public ReservationSystem() {
        setTitle("Online Reservation System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Online Reservation System");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 80, 80, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 80, 150, 20);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 120, 80, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 120, 150, 20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 80, 80, 60);
        loginButton.addActionListener(this);
        add(loginButton);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(80, 170, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 170, 150, 20);
        add(nameField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(80, 210, 80, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 210, 150, 20);
        add(emailField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(80, 250, 80, 20);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(170, 250, 150, 20);
        add(phoneField);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(80, 290, 80, 20);
        add(dateLabel);

        String[] dates = {"May 15, 2023", "May 16, 2023", "May 17, 2023", "May 18, 2023", "May 19, 2023"};
        dateBox = new JComboBox<>(dates);
        dateBox.setBounds(170, 290, 150, 20);
        add(dateBox);

        sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(80, 330, 80, 20);
        add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(170, 330, 150, 20);
        add(sourceField);

        destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(80, 370, 80, 20);
        add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(170, 370, 150, 20);
        add(destinationField);

        trainNumberLabel = new JLabel("Train Number:");
        trainNumberLabel.setBounds(80, 410, 100, 20);
        add(trainNumberLabel);

        trainNumberField = new JTextField();
        trainNumberField.setBounds(180, 410, 100, 20);
        add(trainNumberField);

        reserveButton = new JButton("Reserve");
        reserveButton.setBounds(80, 450, 100, 30);
        reserveButton.addActionListener(this);
        add(reserveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 450, 100, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        reservationArea = new JTextArea();
        reservationArea.setEditable(false);
        reservationArea.setBounds(80, 500, 300, 80);
        JScrollPane scrollPane = new JScrollPane(reservationArea);
        scrollPane.setBounds(80, 500, 300, 80);
        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReservationSystem();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // check if username and password match
            // for simplicity, we will use a hardcoded username and password
            if (username.equals("user") && password.equals("pass")) {
                currentUser = new User(username, nameField.getText(), emailField.getText(), phoneField.getText());
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } else if (e.getSource() == reserveButton) {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first.");
            } else {
                String name = currentUser.getName();
                String date = (String) dateBox.getSelectedItem();
                String source = sourceField.getText();
                String destination = destinationField.getText();
                String trainNumber = trainNumberField.getText();
                PNRNumber pnrNumber = new PNRNumber();
                Reservation reservation = new Reservation(name, date, source, destination, trainNumber, pnrNumber.generatePNR());
                reservations.add(reservation);
                reservationArea.setText("Reservations:\n");
                for (Reservation r : reservations) {
                    reservationArea.append(r + "\n");
                }
            }
        } else if (e.getSource() == cancelButton) {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first.");
            } else {
                String name = currentUser.getName();
                String date = (String) dateBox.getSelectedItem();
                String source = sourceField.getText();
                String destination = destinationField.getText();
                Reservation reservationToRemove = null;
                for (Reservation r : reservations) {
                    if (r.getName().equals(name) && r.getDate().equals(date) && r.getSource().equals(source) && r.getDestination().equals(destination)) {
                        reservationToRemove = r;
                        break;
                    }
                }
                if (reservationToRemove != null) {
                    reservations.remove(reservationToRemove);
                    reservationArea.setText("Reservations:\n");
                    for (Reservation r : reservations) {
                        reservationArea.append(r + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Reservation not found.");
                }
            }
        }
    }

    class User {
        private String username;
        private String name;
        private String email;
        private String phone;

        public User(String username, String name, String email, String phone) {
            this.username = username;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }

    class Reservation {
        private String name;
        private String date;
        private String source;
        private String destination;
        private String trainNumber;
        private String pnrNumber;

        public Reservation(String name, String date, String source, String destination, String trainNumber, String pnrNumber) {
            this.name = name;
            this.date = date;
            this.source = source;
            this.destination = destination;
            this.trainNumber = trainNumber;
            this.pnrNumber = pnrNumber;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public String getTrainNumber() {
            return trainNumber;
        }

        public String getPNRNumber() {
            return pnrNumber;
        }

        @Override
        public String toString() {
            return "Name: " + name +
                    ", Date: " + date +
                    ", Source: " + source +
                    ", Destination: " + destination +
                    ", Train Number: " + trainNumber +
                    ", PNR Number: " + pnrNumber;
        }
    }

    class PNRNumber {
        private int counter = 0;

        public String generatePNR() {
            counter++;
            return "PNR" + counter;
        }
    }
}


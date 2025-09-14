import java.util.*;

class ReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer, String> reservations = new HashMap<>(); // store PNR -> reservation details
    static int pnrCounter = 1000;

    // Login credentials (hardcoded for demo)
    static String validUser = "user";
    static String validPass = "pass";

    public static void main(String[] args) {
        if (login()) {
            while (true) {
                System.out.println("\n--- Online Reservation System ---");
                System.out.println("1. Book Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. View Reservations");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: bookTicket(); break;
                    case 2: cancelTicket(); break;
                    case 3: viewReservations(); break;
                    case 4:
                        System.out.println("Thank you for using the system!");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } else {
            System.out.println("Login Failed. Exiting...");
        }
    }

    // Login Form
    static boolean login() {
        System.out.print("Enter Login ID: ");
        String id = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        return id.equals(validUser) && pass.equals(validPass);
    }

    // Reservation System
    static void bookTicket() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = sc.nextLine();
        System.out.print("Enter Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String date = sc.nextLine();
        System.out.print("Enter From (Source): ");
        String from = sc.nextLine();
        System.out.print("Enter To (Destination): ");
        String to = sc.nextLine();

        int pnr = ++pnrCounter;
        String details = "Name: " + name + ", Train: " + trainNo + " - " + trainName +
                         ", Class: " + classType + ", Date: " + date +
                         ", From: " + from + ", To: " + to;

        reservations.put(pnr, details);
        System.out.println("Ticket booked successfully! PNR Number: " + pnr);
    }

    // Cancellation Form
    static void cancelTicket() {
        System.out.print("Enter PNR Number to Cancel: ");
        int pnr = sc.nextInt();
        if (reservations.containsKey(pnr)) {
            System.out.println("Booking Details: " + reservations.get(pnr));
            System.out.print("Confirm cancellation? (yes/no): ");
            String confirm = sc.next();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Ticket Cancelled Successfully!");
            } else {
                System.out.println("Cancellation Aborted.");
            }
        } else {
            System.out.println("Invalid PNR Number!");
        }
    }

    // View all reservations
    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Current Reservations:");
            for (Map.Entry<Integer, String> entry : reservations.entrySet()) {
                System.out.println("PNR: " + entry.getKey() + " | " + entry.getValue());
            }
        }
    }
}
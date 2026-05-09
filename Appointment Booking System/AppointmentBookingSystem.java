import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Patient {
    private final String name;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Doctor {
    private final String name;
    private final String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Dr. " + name + " (" + specialization + ")";
    }
}

class Appointment {
    private final Patient patient;
    private final Doctor doctor;
    private final String date;
    private final String time;

    public Appointment(Patient patient, Doctor doctor, String date, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void displayAppointment() {
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor : " + doctor);
        System.out.println("Date   : " + date);
        System.out.println("Time   : " + time);
        System.out.println("--------------------------------");
    }
}

class BookingSystem {
    private final List<Doctor> doctors;
    private final List<Appointment> appointments;

    public BookingSystem() {
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        addDefaultDoctors();
    }

    private void addDefaultDoctors() {
        doctors.add(new Doctor("Dr. kumar", "Cardiology"));
        doctors.add(new Doctor("Dr. sreenivas", "Dermatology"));
        doctors.add(new Doctor("Dr. rahul ", "Orthopedics"));
    }

    public void displayDoctors() {
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
    }

    public boolean bookAppointment(String patientName, int doctorChoice, String date, String time) {
        if (doctorChoice < 1 || doctorChoice > doctors.size()) {
            System.out.println("Invalid doctor selection. Appointment was not booked.");
            return false;
        }

        Patient patient = new Patient(patientName);
        Doctor doctor = doctors.get(doctorChoice - 1);
        Appointment appointment = new Appointment(patient, doctor, date, time);
        appointments.add(appointment);

        System.out.println("Appointment booked successfully.");
        return true;
    }

    public void displayAppointments() {
        System.out.println("\nScheduled Appointments:");

        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled yet.");
            return;
        }

        for (Appointment appointment : appointments) {
            appointment.displayAppointment();
        }
    }
}

public class AppointmentBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();
        int choice;

        do {
            System.out.println("\n===== Appointment Booking System =====");
            System.out.println("1. View Doctors");
            System.out.println("2. Book Appointment");
            System.out.println("3. View Scheduled Appointments");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookingSystem.displayDoctors();
                    break;
                case 2:
                    bookingSystem.displayDoctors();

                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();

                    System.out.print("Select doctor number: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid doctor number: ");
                        scanner.next();
                    }
                    int doctorChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter appointment date (DD-MM-YYYY): ");
                    String date = scanner.nextLine();

                    System.out.print("Enter appointment time (HH:MM AM/PM): ");
                    String time = scanner.nextLine();

                    bookingSystem.bookAppointment(patientName, doctorChoice, date, time);
                    break;
                case 3:
                    bookingSystem.displayAppointments();
                    break;
                case 4:
                    System.out.println("Thank you for using the Appointment Booking System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

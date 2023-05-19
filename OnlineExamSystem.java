import java.util.Scanner;

public class OnlineExamSystem {
    private static final int NUM_QUESTIONS = 10; // number of multiple choice questions
    private static final int TIME_LIMIT = 60; // time limit for the exam in minutes

    private static Scanner scanner = new Scanner(System.in);

    private static String[] questions = {
            "Who invented Java Programming?",
            "Which statement is true about Java??",
            "Which component is used to compile, debug and execute the java programs?",
            "Which one of the following is not a Java feature?",
            "Which of these cannot be used for a variable name in Java?",
            "What is the extension of java code files?",
            "Which environment variable is used to set the java path?",
            "Which of the following is not an OOPS concept in Java?",
            "Which of the following is a type of polymorphism in Java Programming?",
            " What is the extension of compiled java classes?"
    };
    private static String[][] options = {
            {"Guido van Rossum", " James Gosling", "Dennis Ritchie", "none"},
            {" Java is a sequence-dependent programming language", "Java is a code dependent programming language", "Java is a platform-dependent programming language", "Java is a platform-independent programming language"},
            {"JRE", "JIT", "JDK", "JVM"},
            {"Object-oriented", "CO2Use of pointers", "portable", "Dynamic and Extensible"},
            {"identifier & keyword", "identifier", "keyword", "none"},
            {" .js", ".txt", ".jvm", ".java"},
            {"MAVEN_Path", "JAVA_PATH", "JAVA", "JAVA_HOME"},
            {"Polymorphism", " Inheritance", "Compilation", "Encapsulation"},
            {"Multiple polymorphism", "Compile time polymorphism", "a,b", "none"},
            {".js", ".txt", ".class", ".java"}
    };
    private static int[] answers = {2, 4, 3, 3, 3, 4, 4, 3, 2, 3};

    public static void main(String[] args) {
        System.out.println("Welcome to the online examination system!");
        System.out.println("Please login to continue.");
        String username = login();
        if (username == null) {
            System.out.println("Login failed. Exiting...");
            return;
        }
        System.out.println("Login successful.");
        boolean continueExam = true;
        while (continueExam) {
            System.out.println("Please select an option:");
            System.out.println("1. Update profile and password");
            System.out.println("2. Start exam");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    startExam(username);
                    break;
                case 3:
                    continueExam = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        System.out.println("Thank you for using the online examination system. Goodbye!");
    }

    private static String login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        // TODO: Check if username and password are correct
        return username;
    }

    private static void updateProfile() {
        // TODO: Implement profile update functionality
        System.out.println("Profile updated successfully.");
    }

    private static void startExam(String username) {
        System.out.println("Starting exam...");
        int[] userAnswers = new int[NUM_QUESTIONS];
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TIME_LIMIT * 60 *1000; // convert minutes to milliseconds
        boolean examEnded = false;
        while (!examEnded) {
            for (int i = 0; i < NUM_QUESTIONS; i++) {
                System.out.println("Question " + (i+1) + ": " + questions[i]);
                for (int j = 0; j < options[i].length; j++) {
                    System.out.println((j+1) + ". " + options[i][j]);
                }
                System.out.print("Enter your answer (1-" + options[i].length + "): ");
                int answer = scanner.nextInt();
                userAnswers[i] = answer - 1; // subtract 1 to convert to zero-based index
            }
            long remainingTime = endTime - System.currentTimeMillis();
            if (remainingTime <= 0) {
                examEnded = true;
                System.out.println("Time's up! Submitting answers...");
            } else {
                System.out.println("You have " + (remainingTime/1000/60) + " minutes left. Press 1 to submit or 2 to continue.");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    examEnded = true;
                }
            }
        }
        int numCorrectAnswers = 0;
        for (int i = 0; i < NUM_QUESTIONS; i++) {
            if (userAnswers[i] == answers[i]) {
                numCorrectAnswers++;
            }
        }
        double score = (double) numCorrectAnswers / NUM_QUESTIONS * 100;
        System.out.println("Exam ended. You answered " + numCorrectAnswers + " out of " + NUM_QUESTIONS + " questions correctly.");
        System.out.println("Your score is " + score + "%.");
// TODO: Save exam results to database
        System.out.println("Logging out user " + username + "...");
    }
}

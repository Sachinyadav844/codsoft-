import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        double[] scores = new double[numSubjects];
        double total = 0;

        // Input scores for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the score for subject " + (i + 1) + ": ");
            scores[i] = scanner.nextDouble();
            total += scores[i];
        }

        // Calculate average
        double average = total / numSubjects;

        // Determine grade
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n--- Grade Report ---");
        System.out.println("Total Score: " + total);
        System.out.println("Average Score: " + average);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}

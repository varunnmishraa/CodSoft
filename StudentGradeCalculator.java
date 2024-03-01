import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of subjects - ");
        int numOfSubjects = scanner.nextInt();
        double[] marks = new double[numOfSubjects];
        System.out.println("Enter the marks obtained in each subject (out of 100) -5 ");
        for (int i = 0; i < numOfSubjects; i++) {
            marks[i] = scanner.nextDouble();
        }
        double totalMarks = calculateTotalMarks(marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numOfSubjects * 100);
        String grade = calculateGrade(averagePercentage);
        displayResults(totalMarks, averagePercentage, grade);
    }

    private static double calculateTotalMarks(double[] marks) {
        double totalMarks = 0;
        for (double mark : marks) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    private static double calculateAveragePercentage(double totalMarks, int maxPossibleMarks) {
        return (totalMarks / maxPossibleMarks) * 100;
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private static void displayResults(double totalMarks, double averagePercentage, String grade) {
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }
}
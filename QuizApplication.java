import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApplication {
    private static List<QuizQuestion> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();

        Scanner scanner = new Scanner(System.in);
        startQuiz(scanner);
        scanner.close();
    }

    private static void initializeQuestions() {
        // Add your questions here
        List<String> options1 = new ArrayList<>();
        options1.add("A. 3");
        options1.add("B. 8");
        options1.add("C. 2");
        options1.add("D. 4");
        QuizQuestion question1 = new QuizQuestion("Question 1: What is 1 + 1?", options1, 2);

        List<String> options2 = new ArrayList<>();
        options2.add("A. 2");
        options2.add("B. 7");
        options2.add("C. 9");
        options2.add("D. 4");
        QuizQuestion question2 = new QuizQuestion("Question 2: What is 2 * 2?", options2, 3);

        // Add more questions as needed
        questions.add(question1);
        questions.add(question2);
    }

    private static void startQuiz(Scanner scanner) {
        System.out.println("Welcome to the Quiz Application!");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                showNextQuestion(scanner);
            }
        }, 20000); // 20 seconds per question

        showNextQuestion(scanner);
    }

    private static void showNextQuestion(Scanner scanner) {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\n" + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            System.out.print("Enter your choice (A/B/C/D): ");
            String userChoice = scanner.nextLine().toUpperCase();

            if (validateAnswer(userChoice, currentQuestion)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }

            currentQuestionIndex++;
            timer.cancel();
            startQuiz(scanner);
        } else {
            endQuiz();
        }
    }

    private static boolean validateAnswer(String userChoice, QuizQuestion question) {
        int correctIndex = question.getCorrectAnswerIndex();
        String correctChoice = String.valueOf((char) ('A' + correctIndex));
        return userChoice.equals(correctChoice);
    }

    private static void endQuiz() {
        System.out.println("\nQuiz ended!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.size() - score));
    }
}

package Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

class QuizApplication {
    private static final int TIME_LIMIT = 10; // seconds
    private static int score = 0;

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 2));
        questions.add(new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 1));
        questions.add(new Question("What is the color of the sky?", new String[]{"1. Blue", "2. Green", "3. Red", "4. Yellow"}, 0));

        Scanner scanner = new Scanner(System.in);

        for (Question q : questions) {
            System.out.println(q.question);
            for (String option : q.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    scanner.close();
                    System.exit(0);
                }
            };
            timer.schedule(task, TIME_LIMIT * 1000);

            System.out.print("Your answer (1-4): ");
            int answer = scanner.nextInt() - 1; // Convert to zero-based index
            timer.cancel(); // Cancel the timer if the answer is submitted

            if (answer == q.correctAnswerIndex) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was: " + q.options[q.correctAnswerIndex] + "\n");
            }
        }

        System.out.println("Quiz finished! Your final score: " + score + "/" + questions.size());
        scanner.close();
    }
}

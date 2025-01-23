import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class QuizApplication {
    private JFrame frame;
    private JLabel questionLabel, timerLabel, scoreLabel;
    private JButton optionA, optionB, optionC, optionD;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int timeLeft = 10; // Time per question in seconds
    private Timer timer;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Rome", "Berlin", "A"},
            {"Who wrote 'Hamlet'?", "Charles Dickens", "William Shakespeare", "Mark Twain", "J.K. Rowling", "B"},
            {"What is 5 + 3?", "5", "8", "10", "15", "B"}
    };

    public QuizApplication() {
        frame = new JFrame("Quiz Application");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("Question will appear here.", SwingConstants.CENTER);
        frame.add(questionLabel);

        optionA = new JButton("Option A");
        optionB = new JButton("Option B");
        optionC = new JButton("Option C");
        optionD = new JButton("Option D");

        frame.add(optionA);
        frame.add(optionB);
        frame.add(optionC);
        frame.add(optionD);

        timerLabel = new JLabel("Time left: " + timeLeft + "s", SwingConstants.CENTER);
        frame.add(timerLabel);

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        frame.add(scoreLabel);

        // Add action listeners
        optionA.addActionListener(new OptionButtonListener("A"));
        optionB.addActionListener(new OptionButtonListener("B"));
        optionC.addActionListener(new OptionButtonListener("C"));
        optionD.addActionListener(new OptionButtonListener("D"));

        loadNextQuestion();

        frame.setVisible(true);
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            String[] currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion[0]);
            optionA.setText("A. " + currentQuestion[1]);
            optionB.setText("B. " + currentQuestion[2]);
            optionC.setText("C. " + currentQuestion[3]);
            optionD.setText("D. " + currentQuestion[4]);
            timeLeft = 10;
            updateTimerLabel();
            startTimer();
        } else {
            endQuiz();
        }
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                updateTimerLabel();
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up!");
                    nextQuestion();
                }
            }
        });
        timer.start();
    }

    private void updateTimerLabel() {
        timerLabel.setText("Time left: " + timeLeft + "s");
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        loadNextQuestion();
    }

    private void endQuiz() {
        if (timer != null) {
            timer.stop();
        }
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your final score is: " + score);
        System.exit(0);
    }

    private class OptionButtonListener implements ActionListener {
        private String selectedOption;

        public OptionButtonListener(String option) {
            this.selectedOption = option;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer != null) {
                timer.stop();
            }
            String correctAnswer = questions[currentQuestionIndex][5];
            if (selectedOption.equals(correctAnswer)) {
                score++;
                scoreLabel.setText("Score: " + score);
                JOptionPane.showMessageDialog(frame, "Correct!");
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong! Correct answer was: " + correctAnswer);
            }
            nextQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApplication::new);
    }
}

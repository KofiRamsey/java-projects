import java.util.*;

class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            String userAnswer = scanner.nextLine();
            if (question.isCorrect(userAnswer)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            System.out.println();
        }

        System.out.println("Quiz completed!");
        int totalQuestions = questions.size();
        double percentageScore = (score * 100.0) / totalQuestions;
        System.out.println("Your score: " + score + " out of " + totalQuestions);
        System.out.println("Percentage score: " + percentageScore + "%");
    }
}

class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding questions to the quiz
        Question question1 = new Question("What is the capital of France?", "Paris");
        Question question2 = new Question("What is the largest planet in our solar system?", "Jupiter");
        Question question3 = new Question("What year was Java programming language released?", "1995");
        Question question4 = new Question("Which country is known as the 'Land of the Rising Sun'?", "Japan");
        Question question5 = new Question("What is the currency of Brazil?", "Real");
        Question question6 = new Question("Who painted the Mona Lisa?", "Leonardo da Vinci");
        Question question7 = new Question("Which planet is known as the 'Red Planet'?", "Mars");
        Question question8 = new Question("Who wrote the play 'Romeo and Juliet'?", "William Shakespeare");
        Question question9 = new Question("What is the largest ocean on Earth?", "Pacific Ocean");
        Question question10 = new Question("What is the chemical symbol for gold?", "Au");
        Question question11 = new Question("Who is the author of the Harry Potter book series?", "J.K. Rowling");
        Question question12 = new Question("Which city is the capital of Australia?", "Canberra");
        Question question13 = new Question("Who is the current President of the United States?", "Joe Biden");
        Question question14 = new Question("What is the tallest mountain in the world?", "Mount Everest");
        Question question15 = new Question("Which famous scientist developed the theory of relativity?", "Albert Einstein");
        Question question16 = new Question("In which country would you find the Great Wall?", "China");
        Question question17 = new Question("What is the largest animal in the world?", "Blue whale");
        Question question18 = new Question("Who painted the Sistine Chapel ceiling?", "Michelangelo");
        Question question19 = new Question("Which planet is known as the 'Giant of our Solar System'?", "Jupiter");
        Question question20 = new Question("What is the official language of Japan?", "Japanese");


        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);
        quiz.addQuestion(question4);
        quiz.addQuestion(question5);
        quiz.addQuestion(question6);
        quiz.addQuestion(question7);
        quiz.addQuestion(question8);
        quiz.addQuestion(question9);
        quiz.addQuestion(question10);
        quiz.addQuestion(question11);
        quiz.addQuestion(question12);
        quiz.addQuestion(question13);
        quiz.addQuestion(question14);
        quiz.addQuestion(question15);
        quiz.addQuestion(question16);
        quiz.addQuestion(question17);
        quiz.addQuestion(question18);
        quiz.addQuestion(question19);
        quiz.addQuestion(question20);
        // Starting the quiz
        quiz.startQuiz();
    }
}

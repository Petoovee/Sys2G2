package sharedEntities;

import java.io.Serializable;

/**
 * The Questions class will store all the questions
 *
 * @author abdulsamisahil, Motaz Kasem
 * @version 2.0
 * @since 2020-04-29
 */
public class Questions implements Serializable {
    private boolean correctAnswer;
    private String question;
    private String answer;

    //Creating an array with 3 positions to save the wrong answers in it
    private String[] wrongAnswers = new String[3];
    private String userAnswer;

    //private Course course;
    //Constructor
    //addQuestions method
    //removeQuestions method


    //Creating Questions, Answers and filling the wrong answers into the array wrongAnswer
    public Questions(String question, String answer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        this.question = question;
        this.answer = answer;
        wrongAnswers[0] = wrongAnswer1; // Adding wrongAnswer1 to an array called wrongAnswer at the pos 0
        wrongAnswers[1] = wrongAnswer2; // Adding wrongAnswer2 to an array called wrongAnswer at the pos 1
        wrongAnswers[2] = wrongAnswer3; // Adding wrongAnswer3 to an array called wrongAnswer at the pos 2
    }

    public Questions(String question, String answer, String wrongAnswer1)
    {
        this.question = question;
        this.answer = answer;
        this.userAnswer = wrongAnswer1;
    }
    public void correctAnswer(boolean correctAnswer, String userAnswer){
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    //getQuestions method
    //setQuestions method

    public Questions() {
    }

    //Setter & Getter methods for Question,WrongAnswer and Answer
    public String getQuestion() {
        return question;
    }

    public String[] getWrongAnswers() {
        return wrongAnswers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //Fixing the String format for Questions
    public String toString() {
        return "Questions: " +
                question;
    }

}


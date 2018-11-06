package org.example.fyp;

/**
 * Created by nimesh on 30/03/2018.
 */

public class QuizItem {

   private String question, answer;


    public QuizItem(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }
}

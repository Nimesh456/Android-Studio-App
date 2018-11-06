package org.example.fyp;

/**
 * Created by nimesh on 11/03/2018.
 */

public class QuestionBank {

   public String mQuestions[] = {
            "What percentage of the human body is water?",
            "Many children with asthma experience more severe reactions when they breathe ________.",
            "What chemical does your immune system produce to fight a particular invading substance?",
            "What causes the skin disease called shingles?",
            "Which type of fat is present in healthy diets?",
            "Nearly _ out of 10 lung cancers are caused by smoking",
            "What illness has no commonly used vaccine?",
            "Which part of the body is affected by conjunctivitis?",
            "What is the most prevalent noncontagious disease in the world?",
            "What causes the skin malady known as acne?",
            "How many grams of salt is the recommended daily allowance for adults in the UK?",
            "Where does most of your vitamin D come from?",
            "What is the white blood cells that attack pathogens?",
            "How many chambers are in the heart?",
            "Which nutrients are especially important for promoting good bone strength?",
            "How much water do experts reckon people should drink every day?",
            "What percentage of our daily calorie intake (energy) should come from carbohydrates?"
    };

    // array of multiple choices for each question
    private String mAnswers [][] = {
            {"50%", "45%", "66%", "70%"},
            {"Oxygen", "Secondhand Smoke", "Carbon dioxide", "Nitrogen"},
            {"Anti-bodies", "Blood cells", "Chlorine", "Calcium"},
            {"scratching", "bacteria", "viruses", "dryness"},
            {"Saturated fat", "Mono-unsaturated fat", "Disaturated fat", "Poly-unsaturated fat"},
            {"7", "5", "6", "9"},
            {"cold", "smallpox", "mumps", "tetanus"},
            {"Ear", "Eyes", "Nose", "Mouth"},
            {"heart disease", "obesity", "tooth decay", "cold"},
            {"chocolate", "extra oil production", "sweat", "bad manners"},
            {"5g", "6g", "7g", "8g"},
            {"Eggs", "Cereal", "Sunlight", "Oily fish"},
            {"Neurocytes", "Lymphocytes", "Carcinogens", "Antitoxins"},
            {"1", "3", "5", "4"},
            {"Carbohydrates, Calcium and vitamin D, Omega 3, Iron and Sodium" },
            {"1 litre", "2 litres", "3 litres", "4 litres"},
            {"40%", "60%", "50%", "80%"}
    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"66%", "Secondhand Smoke", "Anti-bodies", "viruses", "Poly-unsaturated fat", "9", "cold", "Eyes", "tooth decay", "extra oil production", "6g", "Sunlight", "Lymphocytes", "4", "Calcium and vitamin D", "2 litres", "50%"};

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice = mAnswers[a][0];
        return choice;
    }

    public String getChoice2(int a){
        String choice = mAnswers[a][1];
        return choice;
    }

    public String getChoice3(int a){
        String choice = mAnswers[a][2];
        return choice;
    }

    public String getChoice4(int a){
        String choice = mAnswers[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }

}


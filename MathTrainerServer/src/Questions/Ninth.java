package Questions;

import sharedEntities.Questions;

/**
 * This class is a subclass to the parent Course, this class makes an
 * array of the Question class and returns it back whenever the user
 * selects to be apart of the class.
 * @author Johan Lövberg, abdulsamisahil
 * @version 1.5
 * @since 2021-01-06
 *
 *
 * Obs: Class is usable right now, keeping it for future implementation same as class Sixth
 */
public class Ninth extends Course {
	private Questions[] fourCountQuestionForNinth;
	
	public Ninth() {
		 initFourCountQuestions();
	}
    public Questions[] shuffleArrayRandomly(Questions [] array) {
    int nbrOfQuestions = array.length;
        for (int i = 0; i < nbrOfQuestions; i++)
        {
            int rand = (int) (Math.random() * (nbrOfQuestions));
            Questions question = array[i];
            array[i] = array[rand];
            array[rand] = question;
        }
        return array;
    }
    private void initFourCountQuestions() {
        String q1 = "term + term = ";

        
        fourCountQuestionForNinth = new Questions[]{
        		new Questions(q1, "summa", "produkt", "differens", "kvot"),

        };
    }
    @Override
    public Questions[] getStatisticQuestions() {
        return new Questions[0];
    }

    @Override
    public Questions[] getGeometryQuestions() {
        return new Questions[0];
    }

    @Override
    public Questions[] getFourCountQuestions() {
        Questions [] array = shuffleArrayRandomly(fourCountQuestionForNinth);
        return array;
    }

    @Override
    public Questions[] getRandomiseQuestions() {
        return new Questions[0];
    }
}
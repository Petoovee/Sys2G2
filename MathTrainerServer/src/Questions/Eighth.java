package Questions;

import sharedEntities.Questions;

/**
 * This class is a subclass to the parent Course, this class makes an
 * array of the Question class and returns it back whenever the user
 * selects to be apart of the class.
 * @author abdulsamisahil
 * @version 1.5
 * @since 2020-03-31
 *
 *
 * Obs: Class is usable right now, keeping it for future implementation, same as class Sixth
 */
public class Eighth extends Course {

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
        return new Questions[0];
    }

    @Override
    public Questions[] getRandomiseQuestions() {
        return new Questions[0];
    }
}

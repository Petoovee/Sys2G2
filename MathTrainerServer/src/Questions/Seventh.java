package Questions;

import sharedEntities.Questions;
import java.util.List;
/**
 * This class is a subclass to the parent Course, this class makes an
 * array of the Question class and returns it back whenever the user
 * selects to be apart of the class.
 * @author Johan L�vberg, abdulsamisahil
 * @version 1.5
 * @since 2021-01-06
 *
 *
 *
 *
 * Obs: Class is usable right now, keeping it for future implementation same as class Sixth
 */
public class Seventh extends Course {
	private Questions[] fourCountQuestionForSeventh;
    private Questions[] statisticQuestionsForSeventh;
    private Questions[] geometryQuestionsForSeventh;
    private Questions[] randomlyQuestionsForSeventh;
    
    private List<String> statisticList;
    private String question, correct, wrong1, wrong2, wrong3;
    private String n = "\n"; 

	public Seventh() {
        initFourCountQuestions();
        initGeometryQuestions();
        initStatisticQuestions();
        initRandomlyQuestion();
	}
    public Questions[] shuffleArrayRandomly(Questions [] array) {
    	
    int nbrOfQuestions = array.length;
    for (int i = 0; i < nbrOfQuestions; i++) {
            int rand = (int) (Math.random() * (nbrOfQuestions));
            Questions question = array[i];
            array[i] = array[rand];
            array[rand] = question;
        }
        return array;
    }
    
    private void initStatisticQuestions(){
    	String q1 = "I en familj finns det fyra syskon som �r 1 �r, 3 �r, 5 �r och 13 �r."+"\n  vad �r medianen av barnens �lder";
    	String q2 = "Mona k�per en ny sp�nnande bok som har 150 sidor, som hon l�ser under 5 dagar. Dag ett l�ser hon 34 sidor, dag tv� l�ser hon 40 sidor, dag tre 22 sidor, dag fyra 31 sidor och dag fem 23 sidor" + "\n Ungef�r hur m�nga sidor l�ser Mona per dag ?";
    	String q3 = "Vad �r medelv�rdet av f�ljande tal" + "\n 4+30+22+43+51?";
    	String q4 = "I en familj finns det tre syskon som �r 1 �r, 3 �r och 5 �r."+"\n vad �r medianen av barnens �lder";
    	String q5 = "Under en vardag i en biograf kom det 20 personer. 5st s�g Lassemajas detektivbyr�, 10st s�g Fram�t och 5st s�g Sune i fj�llen. " + "\n Hur stor andel av bes�karna s�g Lassemajas detektivbyr�?";
    	String q6 = "Under en vardag i en biograf kom det 20 personer. 5st s�g Lassemajas detektivbyr�, 10st s�g Fram�t och 5st s�g Sune i fj�llen. " + "\n Hur stor andel av bes�karna s�g Fram�t?";
    	String q7 = "Under en vardag i en biograf kom det 20 personer. 5st s�g Lassemajas detektivbyr�, 10st s�g Fram�t och 5st s�g Sune i fj�llen. " + "\n Hur stor andel av bes�karna s�g Sune i fj�llen?";
    	String q8 = "I en unders�kning f�r 1000 personer, fr�gades hur m�nga som t�nkte g� p� bio i �r. 700 svarade Ja, 200 svarade Nej och 100 svarde Vet ej " + "\n Hur stor andel svarade Ja?";
    	String q9 = "I en unders�kning f�r 1000 personer, fr�gades hur m�nga som t�nkte g� p� bio i �r. 700 svarade Ja, 200 svarade Nej och 100 svarde Vet ej " + "\n Hur stor andel svarade Nej?";
    	String q10 = "I en unders�kning f�r 1000 personer, fr�gades hur m�nga som t�nkte g� p� bio i �r. 700 svarade Ja, 200 svarade Nej och 100 svarde Vet ej " + "\n Hur stor andel svarade Vet ej?";
    	String q11 = "Under en vecka har Agnest unders�kt den sjunkande tempraturen utanf�r hennes hem. Tempraturen f�r m�ndag var 9 grader, 8 p� tisdagen, 6 p� onsdag, 5 p� tosdagen, 5 p� fredagen, 1 p� l�rdagen och 1 grad p� s�ndagen" + "\n Var har medeltempraturen varit f�r veckan?";
    	
    	statisticQuestionsForSeventh = new Questions[]{
                new Questions(q1, "4", "1", "15", "30"),
                new Questions(q2, "30", "50", "15", "40"),
                new Questions(q3, "30", "10", "3", "32"),               
                new Questions(q4, "3", "2", "1", "5"),
                new Questions(q5, "25%", "20%", "5%", "10%"),
                new Questions(q6, "50%", "25%", "10%", "25%"),
                new Questions(q7, "25%", "5%", "50%", "1%"),
                new Questions(q8, "70%", "700%", "25%", "100%"),
                new Questions(q9, "20%", "15", "10%", "50%"),
                new Questions(q10, "10%", "20%", "1%", "50%"),
                new Questions(q11, "5", "6", "9", "7")
                };
    }
      
    private void initGeometryQuestions(){
    	String q1 = "En triangel har en vinkel med 70 grader och en annan �r 60 grader" + "\n Hur m�nga grader �r den tredje vinkeln?";
    	String q2 = "En triangel har l�ngden 5, 4 samt 3 cm p� sin sidor" + "\n Vad �r omkretsen?";
    	String q3 = "En r�tvinklig triangel har basen 4 och h�jden 3" + "\n Vad �r trianglens area?";
    	String q4 = "Hur m�nga grader har varje h�rn i en liksidig triangel?";
    	String q5 = "I en r�tvinklig triangel vet vi att en av vinklarna �r 50 grader" + "\n Hur m�nga grader har de andra vinklarna?";
    	String q6 = "Tv� vinklar i en triangel har summan 130 grader" + "\n Hur m�nga grader �r den tredje vinkeln?";
    	String q7 = "En triangel har en vinkel med 10 grader och en annan �r 10 grader" + "\n Hur m�nga grader �r den tredje vinkeln?";;
    	String q8 = "Vilken av f�ljande omvandlingar �r korrekt, om vi vill r�kna fram 100m.";
    	String q9 = "Vi vill skala om till 1:10 vilket av alternativen �r korrekt?";
    	String q10 = "En avbildning av en myra har skalan 5:1, vilket alternativ �r korrekt?";
    	String q11 = "Vilken skala �r naturlig storlek?";
    	
    	geometryQuestionsForSeventh = new Questions[]{
    			new Questions(q1,"50","60","70","40"),
    			new Questions(q2,"12","6","8","60"),
    			new Questions(q3,"6","4","8","12"),
    			new Questions(q4,"60","90","70","50"),
    			new Questions(q5,"90 och 40","80 och 40","80 och 60","65 och 65"),
    			new Questions(q6,"50","90","70","60"),
    			new Questions(q7,"160","190","170","150"),
    			new Questions(q8,"100*100 cm","10*100 cm","100*10 cm","1000*100 cm"),
    			new Questions(q9,"100cm blir 10cm","1cm blir 100cm","10cm blir 1000cm","10 cm blir 100"),
    			new Questions(q10,"1cm �r 0,2cm","1cm �r 5cm","5cm �r 2cm","10cm �r 1cm"),
    			new Questions(q11,"1:1","1:2","2:1","1:10"),
    			};    			   	
    	}
    
    private void initFourCountQuestions() {
        String q1 = "Term + term = ";
        String q2 = "Term - term = ";
        String q3 = "Faktor * faktor = ";
        String q4 = "T�ljar/n�mnare = ";
        String q5 = "Vilket av alternativen kan dela upp heltalet 3783?";
        String q6 = "Hur mycket �r siffran 6 v�rd i f�ljande tal"+"\n 7689";
        String q7 = "Hur mycket �r siffran 2 v�rd i f�ljande tal"+"\n 1237";
        String q8 = "Hur mycket �r siffran 8 v�rd i f�ljande tal"+"\n 678";
        String q9 = "Vad �r f�ljande tal i utvecklad form?"+"\n 678";
        String q10 = "Vad �r f�ljande tal i utvecklad form?"+"\n 2000";
        String q11 = "Vilket av alternativen kan dela upp decimaltalet"+"\n 37,99";
               
        fourCountQuestionForSeventh = new Questions[]{
        		new Questions(q1, "summa", "produkt", "differens", "kvot"),
        		new Questions(q2, "differens", "produkt", "summa", "kvot"),
        		new Questions(q3, "produkt", "summan", "differens", "kvot"),
        		new Questions(q4, "kvot", "produkt", "differens", "summan"),
        		new Questions(q5, "3000+700+80+3", "3000+7000+80+3", "3000+70+80+3", "3000+800+70+3"),
        		new Questions(q6, "600", "60", "60000", "6"),
        		new Questions(q7, "200", "20", "22", "222"),
        		new Questions(q8, "8", "800", "80", "888"),
        		new Questions(q9, "600+70+8", "6000+700+80+0", "80+700+600", "800+70+6"),
        		new Questions(q10, "2000", "2000+200+20+2", "1000+200+1000+20", "2000+800+70+3"),
        		new Questions(q11, "30+7+0,99", "300+70+9+9", "30+70+9+9", "30+7+99"),        		
        		};
    }
    
    private void initRandomlyQuestion(){
        String q1 = "Term + term = ";
    	String q2 = "En triangel har l�ngden 5, 4 samt 3 cm p� sin sidor" + "\n Vad �r omkretsen?";
    	String q3 = "En r�tvinklig triangel har basen 4 och h�jden 3" + "\n Vad �r trianglens area?";
        String q4 = "T�ljar/n�mnare = ";
    	String q5 = "I en familj finns det fyra syskon som �r 2 �r, 3 �r, 5 �r och 13 �r."+"\n  vad �r medianen av barnens �lder";
    	String q6 = "Vad �r medelv�rdet av f�ljande tal" + "\n 7+32+15+45+51?";   	
        String q7 = "Hur mycket �r siffran 2 v�rd i f�ljande tal"+"\n 267";
        String q8 = "Hur mycket �r siffran 8 v�rd i f�ljande tal"+"\n 8670";        
        String q9 = "Vad �r f�ljande tal i utvecklad form?"+"\n 123";
        String q10 = "Vad �r f�ljande tal i utvecklad form?"+"\n 7894";
        String q11 = "Vilket av alternativen kan dela upp decimaltalet"+"\n 37,99";      
        
    	randomlyQuestionsForSeventh = new Questions[]{
        	new Questions(q1, "summa", "produkt", "differens", "kvot"),
			new Questions(q2,"12","6","8","60"),
			new Questions(q3,"6","4","8","12"),
        	new Questions(q4, "kvot", "produkt", "differens", "summan"),
        	new Questions(q5, "4", "1", "15", "30"),
        	new Questions(q6, "30", "10", "3", "32"),        	
    		new Questions(q7, "200", "20", "22", "222"),
    		new Questions(q8, "8000", "800", "80", "888"),    		
    		new Questions(q9, "100+20+3", "3000+300+10+6", "8+70+600", "500+80+6"),
    		new Questions(q10, "7000+800+90+4", "2000+100+40+9", "7000+900+10+2", "1000+300+70+3"),
			new Questions(q11, "30+7+0,99", "300+70+9+9", "30+70+9+9", "30+7+99")
    		};
    }
    
    @Override
    public Questions[] getRandomiseQuestions() {
        Questions [] array = shuffleArrayRandomly(randomlyQuestionsForSeventh);
        return array;
    }
    @Override
    public Questions[] getStatisticQuestions() {
        Questions [] array = shuffleArrayRandomly(statisticQuestionsForSeventh);
        return array;
    }
    @Override
    public Questions[] getGeometryQuestions() {
        Questions [] array = shuffleArrayRandomly(geometryQuestionsForSeventh);
        return array;
    }
    @Override
    public Questions[] getFourCountQuestions() {
        Questions [] array = shuffleArrayRandomly(fourCountQuestionForSeventh);
        return array;
    }

}

package Server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sharedEntities.Questions;
import sharedEntities.User;


class SharedEntitiesTest {
	User user;
	Questions q;
	@BeforeEach
	void setUp() throws Exception {
		//new MServer(7890);
		user = new User("UserName",9,"email","password", "school", "town","id");
		 q = new Questions("Question", "correctAnswer", "wrongAnswer1","wrongAnswer2","wrongAnswer3"); // making a question where the first string is the question, second is the correct answer. 
		 q.correctAnswer(true, "correctAnswer");
		
		

	}
	@AfterEach
	void tearDown() throws Exception { 

	}

	//------------------------------------------------------------------------------------------
		// Tests for class User	
	@Test
	void testUserGetUserName() {
		assertEquals("UserName", user.getUserName());
	}
	@Test
	void testUserGetPassword() {
		assertEquals("password", user.getPassword());
	}
	@Test
	void testUserGetYear() {
		assertEquals(9, user.getYear());
	}
	@Test
	void testUserGetSchool() {
		assertEquals("school", user.getSchool());
	}
	@Test
	void testUserGetTown() {
		System.out.println(user.toString());
		assertEquals("town", user.getTown());
	}
	@Test
	void testUserToString() { 
		assertEquals("Accessing User {name = 'UserName', password = password}", user.toString());
	}
//------------------------------------------------------------------------------------------
	// Tests for class Questions
	@Test
	void testQuestionsGetQuestion() { 
		assertEquals("Question", q.getQuestion());
	}

	@Test
	void testQuestionsGetWrongAnswers() { 
		assertEquals(3, q.getWrongAnswers().length);
		assertEquals("wrongAnswer1",q.getWrongAnswers()[0]);
		assertEquals("wrongAnswer2",q.getWrongAnswers()[1]);
		assertEquals("wrongAnswer3",q.getWrongAnswers()[2]);
	}
	@Test
	void testQuestionsAnswer() { 
		assertEquals("correctAnswer", q.getAnswer());
	}
	@Test
	void testQuestionsToString() { 
		assertEquals("Questions: Question", q.toString());
	}

	@Test
	void testQuestionsGetUserAnswer() { 
		assertEquals("correctAnswer", q.getUserAnswer());
	}
	@Test
	void testQuestionsGetCorrectAnswer() { 
		assertEquals(true, q.getCorrectAnswer());
	}
	
}

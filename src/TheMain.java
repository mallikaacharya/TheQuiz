/**
 * This is the main class/method.
 * It passes all the methods from the Questions class
 * This way, the program can run. 
 * @author mallikaacharya
 *
 */
public class TheMain {

	public static void main(String[] args) throws Exception {
	      
	Questions questions = new Questions();
	questions.getClass();
	questions.loadQuestions();
	questions.askQuestions();
	questions.scoring();
	questions.generateResponse();
	}
}       
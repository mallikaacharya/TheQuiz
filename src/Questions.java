//Import for console
import java.util.Scanner;

//Import for file operations
import java.io.*;
//Import for scanner         
import java.util.*;

	/**      
	 * @author Mallika Acharya
	 *Questions class:
	 *This loads and asks questions
	 *Calculates a score based on the user's answers
	 *Then it displays different text based on score
	 */

	public class Questions {
	  
	/**
	* Defining tree maps, etc... here, so other classes can use them. 
	*/
	        String text;
	        String chosenAnswer;
	        
	     // A class to structure the answer row
	     // Answer row is the way Questions.txt is formatted
	     // After the question there are lines:
	     // Each one has: 
	     //ansLetter (letter choice of answer) tab ansValue (points per that choice) ansText (text; the answer choice)
	        
	        public class AnswerRow {
	            String ansText;
	            Integer ansValue;
	            public  AnswerRow(String t, Integer p) {
	            this.ansText = t;
	            this.ansValue = p;
	            }
	        }  
	        
	        //TreeMap: key = Letter of the answer; value = Answer that corresponds to letter
	        TreeMap <String, AnswerRow> answerChoices;
	        
	        static int numQuestions;
	        static Questions [] myQuestionList = new Questions[100];
	                
	        /**
	* This method reads the input file, creates tree map, and loops through the questions & answer choices.
	         * @param ansRow 
	* @param console
	*/
	public void loadQuestions () throws FileNotFoundException {
	//Open Questions.txt file
	Scanner fileReader = new Scanner(new File("src/Questions.txt"));
			//Create the variables that are in the file
	        String questionText, ansLetter, ansText;
	        AnswerRow ansRow;
	        Integer ansValue;
	int i = 0;
	//Loop, go through the Questions.txt file
	while (fileReader.hasNext() && i < 100) {
	/// Maximum 100 questions
	//qestionText = the question written out
	questionText = fileReader.nextLine();
	            myQuestionList[i] = new Questions();
	            myQuestionList[i].text = questionText;
	            myQuestionList[i].answerChoices = new TreeMap <String, AnswerRow>();
	            
	            //ansLetter = e.g. A,B,C,D
	            //ansValue = e.g. 0, 5, 10, 20; points per ansLetter
	            //ansText = the written answer choice
	            while(fileReader.hasNext() && !fileReader.hasNext("END")) {
	            ansLetter = fileReader.next("[A-Z]");
	            ansValue = fileReader.nextInt();
	            ansText = fileReader.nextLine();
	            ansRow = myQuestionList[i].new AnswerRow(ansText, ansValue);
	            myQuestionList[i].answerChoices.put(ansLetter, ansRow);  
	            }
	questionText = fileReader.nextLine(); // Eats the END
	i++;
	}
	fileReader.close();
	numQuestions = i;
	}
	
	/**
	* This method prompts questions & respective answer choices for the user and gets responses for each question
	*/
	public void askQuestions () {
		Scanner input = new Scanner(System.in);
		String readAnswer;
		
		// For Each Question
		for (int i=0;i <numQuestions ; i++) {
			// Print the question
			System.out.println(i+1 + " " + myQuestionList[i].text);
		
		// For each item in the TreeMap print the answerChoice (ansLetter and ansText)
		for (String answerLetter:  myQuestionList[i].answerChoices.keySet()) {
		String s = myQuestionList[i].answerChoices.get(answerLetter).ansText;
		System.out.println(answerLetter + ": " + s);
		}
		
		// Reset the answer for this question
		myQuestionList[i].chosenAnswer = ""; 
		
		// Validates answer
		while (! myQuestionList[i].answerChoices.containsKey(myQuestionList[i].chosenAnswer)) {
		System.out.println("Please enter a valid response.");

		//Store answer
		readAnswer = input.next();
		myQuestionList[i].chosenAnswer = readAnswer;
		}
		 
		} 
		//Close input
		 input.close();
		}
		
		
	/**
	* This method is in charge of the scores
	*/
	public int scoring () {
		
		//int that keeps score of answers to questions
		int score = 0;

		//For each question...
		for(int i=0;i<numQuestions;i++) {
		    String ansLetter = myQuestionList[i].chosenAnswer;
		    score += myQuestionList[i].answerChoices.get(ansLetter).ansValue;
		}
		//Return final score
		return score;
	}
	
	public void generateResponse () throws Exception {
		//Get the score from scoring method
		int score = this.scoring();
	
		//If the score is less than or equal to 25...
		if (score <= 24) {	
		//Then display answer:You seem to be someone interested in something light and playful with some pastel shades, that overall has a clean cut design
			System.out.println("You seem to be someone interested in something light and playful with some pastel shades, that overall has a clean cut design");
		//If the score is greater than 25 and less than ...	
		} else if (score >= 25 && score <= 74) {
			//Then display answer: You seem to be someone interested in something trendy, with styles such as text on top of blurred images
			System.out.println("You seem to be someone interested in something trendy, with styles such as text on top of blurred images");
		    	
		//If the score is greater than 25 and less than ...	
		} else if (score >= 75 && score <= 99) {
			//Then display answer: You seem to be someone interested in something polished, with bold colors and fonts
			System.out.println("You seem to be someone interested in something polished, with bold colors and fonts");
			
		//If score the score is greater or equal to 100...
		} else if (score >= 100) {
			//Then display answer: "You seem to be someone interested in something utilitarian; it is functional and has several features, while maintaining a natural look"
			System.out.println("You seem to be someone interested in something utilitarian; it is functional and has several features, while maintaining a natural look");		
}
}
}
	
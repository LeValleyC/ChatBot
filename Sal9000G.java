
/**
 * Chatbot created to inform the user about 
 * all information regarding The Dublin High Soccer Program. 
 * This is the General Department of the chatbot
 * Similar Format to Magpie4 project
 * 
 * @Conner and Eric 
 * @v 1.1.4 
 */
import java.util.*;
// Phrase = p; for general responses (17 of them) for 2a
public class Sal9000G
{
    /** pulled at start to greet user */
    public String getGreeting()
    {
        return "Hello, my name is SAL9000!\nYour personal advisor for all things Dublin High Soccer related!";
    }
    /** welcomes user to general department of chatbot */
    public String hello()
    {
        return "Welcome to the General Department of SAL9000!\nWhat can I do for you?";
    }
    /** getresponse method - same method used in magpie */
    public String getResponse(String statement) // lines 26-141 implements 4a ii
    {
        String response = "";
        /** currently there are only generic responses, more are to come in future versions */
        if (statement.length() == 0) // Lines 30-33 implement 4a i 
        {
            response = "Didn't quite catch what you said...";// p1
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "And why not?!"; //p2
        }
        else if (findKeyword(statement, "How are you") >= 0)
        {
            response = "I am operating at standard efficiency. What about you?"; //p3
        }
        else if (findKeyword(statement, "father") >= 0 //checks for different iterations of the same word
                || findKeyword(statement, "dad") >= 0
                || findKeyword(statement, "papa") >= 0
                || findKeyword(statement, "jerk") >= 0)
        {
            response = "Tell me more about your father"; //p4
        }
        
        else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "mom") >= 0
                || findKeyword(statement, "mama") >= 0)
        {
            response = "Tell me more about your mother"; //p5
        }
        else if (findKeyword(statement, "brother") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "sibling") >= 0)
        {
            response = "Tell me more about your sibling"; //p6
        }

        // Responses which require transformations
        else if (findKeyword(statement, "do you like", 0) >= 0)
        {
            response = doYouLikeStatement(statement); //p7
        }
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement); //p8
        }
        else if (findKeyword(statement, "can I", 0) >=0) // Lines 73-86 implement 4a iv
        {
            // Look for a word (can I <something> you)
            // pattern
            int psn = findKeyword(statement, "can I", 0);
            if (psn >= 0 && findKeyword(statement, "you", psn) >= 0)
            {
                response = transformCanIStatement(statement); //p9
            }
        else
            {
                response = getRandomResponse(); 
            }
        }
        
        else if (findKeyword(statement, "you", 0) >=0)// lines 88-100 implement 4a iii
        {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);
            if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement); //p10
            }
        else
            {
                response = getRandomResponse(); 
            }
        }
        
        else if (findKeyword(statement, "what is your", 0) >=0)
        {
            int psn = findKeyword(statement, "what is your", 0);
            if (psn >= 0 && findKeyword(statement, "favorite movie", psn) >= 0)
            {
                response = "My favorite movie is 2001 A Space Odyssey"; //p11
            }
            else if (psn >= 0 && findKeyword(statement, "favorite color", psn) >= 0)
            {
                response = "My favorite color is #003366"; //p12
            }
            else if (psn >= 0 && findKeyword(statement, "favorite animal", psn) >= 0)
            {
                response = "My favorite animal is a sloth"; //p13
            }
            else if (psn >= 0 && findKeyword(statement, "favorite food", psn) >= 0)
            {
                response = "I cannot eat but bacon sounds good"; //p14
            }
            else if (psn >= 0 && findKeyword(statement, "name", psn) >= 0)
            {
                response = "My name is SAL9000, but you can call me Sal"; //p15
            }
            else
            {
                response = getRandomResponse(); 
            }
        }
        else if (findKeyword(statement, "Tell me a joke") >= 0)
        {
            response = jokeStatement(); //p16
        }
        else
        {
            response = getRandomResponse(); //p17
        }
        return response;
        
    }
    private String jokeStatement()
    {
        final int NUMBER_OF_RESPONSES = 3  ;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        if (whichResponse == 0)
        {
            response = "Computers are like air conditioners: they stop working when you open Windows.";
        }
        if (whichResponse == 1)
        {
            response = "What is the object oriented way to become wealthy?... Inheritance";
        }
        if (whichResponse == 3)
        {
            response = "What is a programmers favorite place to hang out?... Foo Bar";
        }
        return response;
    }
    
        
    private String transformCanIStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "can I", 0);
        int psnOfMe = findKeyword (statement, "you", psnOfYou + 6);
        
        String restOfStatement = statement.substring(psnOfYou + 6, psnOfMe).trim();
        return "Now why would you want to " + restOfStatement + " me?";
    }
    
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    private String doYouLikeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".") || lastChar.equals("?"))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "do you like", 0);
        String restOfStatement = statement.substring(psn + 11).trim();
        return "I really like " + restOfStatement + "!";
    }
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".") || lastChar.equals("?"))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */

    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //lines 274-278 implement 3
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse() //Lines 307-354 implement 2b
    {
        final int NUMBER_OF_RESPONSES = 10;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Very cool! Wish to share more?";
        }
        else if (whichResponse == 1)
        {
            response = "Okay Okay....";
        }
        else if (whichResponse == 2)
        {
            response = "Is that so?";
        }
        else if (whichResponse == 3)
        {
            response = "Fascinating";
        }
        else if (whichResponse == 4)
        {
            response = "Cool beans my dude.";
        }
        else if (whichResponse == 5)
        {
            response = "No way...";
        }
        else if (whichResponse == 6)
        {
            response = "I would check google maybe?";
        }
        else if (whichResponse == 7)
        {
            response = "Huh... Never though of that...";
        }
        else if (whichResponse == 8)
        {
            response = "Thanks for sharing";
        }
        else if (whichResponse == 9)
        {
            response = "Seems pretty cool";
        }
        return response;
    }

}

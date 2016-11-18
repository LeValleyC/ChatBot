import java.util.*;
/**
 * Write a description of class SAL9000S here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sal9000S
{
    
    public String hello()
    {
        return "Welcome to the Soccer Department of SAL9000!\nWhat can I do for you?";
    }

    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Didn't quite catch what you said...";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Alrighty";
        }
        else if (findKeyword(statement, "father") >= 0
                || findKeyword(statement, "dad") >= 0
                || findKeyword(statement, "papa") >= 0
                || findKeyword(statement, "jerk") >= 0)
        {
            response = "Tell me more about your father";
        }
        
        else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "mom") >= 0
                || findKeyword(statement, "mama") >= 0)
        {
            response = "Tell me more about your mother";
        }
        else if (findKeyword(statement, "brother") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "sibling") >= 0)
        {
            response = "Tell me more about your sibling";
        }

        // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }

        else if (findKeyword(statement, "you", 0) >=0)
        {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);
            if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
        }
        else if (findKeyword(statement, "dublin high soccer", 0) >= 0)
        {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "dublin high soccer", 0);
            if (psn >= 0 && findKeyword(statement, "team", psn) >= 0)
            {
                if (psn >= 0 && (findKeyword(statement, "good", psn) >= 0
                                || findKeyword(statement, "bad", psn) >= 0))
                {
                    response = transformDHSSoccerTeamGoodBadStatement();
                }
                else if (psn >= 0 && (findKeyword(statement, "roster", psn) >= 0))
                {
                    response = transformDHSSoccerTeamRosterStatement();
                }
                else
                {
                    response = transformDHSSoccerTeamRosterStatement();
                }
            }
            else if (psn >= 0 && findKeyword(statement, "season", psn) >= 0)
            {
                {
                    response = transformDHSSoccerSeason();
                }
            }
            else
                {
                    response = "What about Dublin high soccer?";
                }
        }
        else if (findKeyword(statement, "thank you", 0) >=0
                || findKeyword(statement, "thanks", 0) >=0)
                {
                    response = "Your Welcome!";
                }
        else
            {
                response = getRandomResponse();
            }
        return response;
        
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
        if (lastChar.equals("."))
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
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    private String transformDHSSoccerTeamGoodBadStatement()
    {
        return "The DHS Soccer team is very good and is competing in the Top Division EBAL.";
    }
    
    private String transformDHSSoccerTeamRosterStatement()
    {
        return "The Roster for Frosh, JV, and Varsity for the 2016-2017 season is as follows:\n"+ Roster();
    }
    public String Roster()
    {
        String roster = "For sake of time, info has not been added";
        return roster;
    }
    private String transformDHSSoccerSeason()
    {
        return "The DHS Soccer Season lasts from November 9th to Febuary 18th";
    }
    
    
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
            
            //  If before and after aren't letters, we've found the word
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
    private String getRandomResponse()
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

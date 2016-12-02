import java.util.*;
/**
 * Mens Soccer Department Chatbot
 * 
 * @author (Conner LeValley) 
 * v 1.1.4
 */

//Phrase = p; for soccer responses (26 of them) for 2a
public class Sal9000S
{
    
    public String hello()
    {
        return "Welcome to the Soccer Department of SAL9000!\nWhat can I do for you?";
    }

    public String getResponse(String statement)
    {
        String response = "";
        String repo = "";
        Scanner in = new Scanner (System.in);
        if (statement.length() == 0)
        {
            response = "Didn't quite catch what you said..."; //p1
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Alrighty"; //p2
        }
        else if (findKeyword(statement, "help") >= 0)
        {
            response = "Basic Question Starters:\n" +
                       "========================\n" +
                       "Is dhs soccer...\n" +
                       "How do I...\n" +
                       "Who is the...\n" +
                       "What is the...\n"; //p3
        }
        //////////////////////////////////////////////////////////////////////
        // Planning to input Markov chain to create easier parsing of text. //
        // Markov chain would also improve chatbot vocabulary to create its //
        // own responses in the future...                                   //
        //////////////////////////////////////////////////////////////////////
        else if (findKeyword(statement, "link") >= 0)
        {
            response = "www.dublinhighschoolmenssoccer.shutterfly.com"; //p4
        }
        else if (findKeyword(statement, "tell me about dhs soccer") >= 0)
        {
            //p5
            response = "The DHS Mens Soccer program is highly regarded as\na top tier program consisting of great coaching staff\nand team chemistry. ";
        }
        ////////////////////////////////////////////////////////////////////////
        // Would also like to implement a Levenshtein distance algorithim as  //
        // a form of "autocorrect" for my chatbot. I would set a threshold so //
        // if the distance was lower than the threshold, the chatbot would    //
        // correct the users input. A dictionary .txt file would be used too  //
        ////////////////////////////////////////////////////////////////////////
        // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            if (findKeyword(statement, "join", 0) >= 0)
            {
                //p6
                response = teamJoinStatement();
            }
            else
            {
                //p7
                response = transformIWantToStatement(statement);
            }
        }        
        else if (findKeyword(statement, "how", 0) >=0)
        {
            int psn = findKeyword(statement, "how", 0);
            if (psn >= 0 && (findKeyword(statement, "do I", psn) >= 0
                            || findKeyword(statement, "can I", psn) >= 0
                            || findKeyword(statement, "can you", psn) >= 0
                            || findKeyword(statement, "do you", psn) >= 0))
            {
                if (psn >= 0 && findKeyword(statement, "join", psn) >= 0)
                {
                    if (psn >= 0 && (findKeyword(statement, "the dublin high soccer team", psn) >= 0
                                || findKeyword(statement, "the team", psn) >= 0
                                || findKeyword(statement, "the dhs soccer team", psn) >= 0))
                                {
                                    //p8
                                    response = teamJoinStatement();
                                }
                                else
                                {
                                    //p9
                                    response = "join what? Be more specific please!";
                                }
                }
                            else
                            {
                                //p10
                                response = "how can you..?";
                            }
            }
            if (psn >= 0 && findKeyword(statement, "many players", psn) >= 0)
            {
                //p11
                response = "It depends each year but most teams have a \nminimum of 14 players and a maximum of 21.\n11 can be on the pitch a a time.";
            }
        }
        
        else if (findKeyword(statement, "who", 0) >=0)
        {
            int psn = findKeyword(statement, "who", 0);
            if (psn >= 0 && (findKeyword(statement, "is the", psn) >= 0
                            || findKeyword(statement, "are the", psn) >= 0))
            {
                if (psn >= 0 && findKeyword(statement, "frosh coach", psn) >= 0)
                {
                    //p12
                    response = "The Frosh coach is John Morrison";
                }

                if (psn >= 0 && findKeyword(statement, "jv coach", psn) >= 0)
                {
                    //p13
                    response = "The JV coach is Tyrone Tuell";
                }

                if (psn >= 0 && findKeyword(statement, "varsity coach", psn) >= 0)
                {
                    //p14
                    response = "The Varsity coach is James Fullwieller";
                }
 
                if (psn >= 0 && findKeyword(statement, "coaches", psn) >= 0)
                {
                    //p15
                    response = "John Morrison (Frosh), Tyrone Tuell (JV), James Fullwieller (Varsity)";
                }
            }
        }
        /** parses through the sentence to find anything dhs soccer related, will add
         * more in future versions so that more iterations be parsed. Planning to repeat
         * this for other aspects to create a more diverse response vocabulary.
         */
        else if (findKeyword(statement, "is", 0) >=0)
        {
            int psn = findKeyword(statement, "is", 0);
            if (findKeyword(statement, "dhs", 0) >= 0)
            {
                if (psn >= 0 && (findKeyword(statement, "soccer", psn) >= 0
                                || findKeyword(statement, "mens soccer", psn) >= 0))
                {
                           if (psn >= 0 && findKeyword(statement, "team", psn) >= 0)
                                {
                                    if (psn >= 0 && (findKeyword(statement, "good", psn) >= 0
                                    || findKeyword(statement, "bad", psn) >= 0))
                                    {
                                        //p16
                                        response = transformDHSSoccerTeamGoodBadStatement();
                                    }
                                }
                            
                           else if (psn >= 0 && findKeyword(statement, "season", psn) >= 0)
                           {
                                {
                                    response = transformDHSSoccerSeason(); // p17
                                }
                           }

                           else if (psn >= 0 && (findKeyword(statement, "fun", psn) >= 0
                                                || findKeyword(statement, "enjoyable", psn) >= 0
                                                || findKeyword(statement, "worth playing", psn) >= 0))
                           {
                                    //p18
                                    response = "Dublin High Soccer is very tough but enjoyably\nrewarding and completely worth the effort"; 
                           }
                         
                           else
                           {
                               //p19
                                response = "Is dhs soccer what?!";
                           }
                }
            
                else
                {
                    response = "Is dhs what?!";
                }
           }  

           else if (psn >= 0 && (findKeyword(statement, "it hard to make a team", psn) >= 0
                                 || findKeyword(statement, "it difficult to make the team", psn) >= 0
                                 || findKeyword(statement, "hard to make the dhs soccer team", psn) >= 0))
           {
               //p20
               response = "Yes, Only 1 out of every 4 players\nwho try out actually make a team"; 
           }
           else if (findKeyword(statement, "the frosh roster?",0) >= 0)
           {
               //p21
               response = transformDHSSoccerFroshRosterStatement();
           }
           else if (findKeyword(statement, "the jv roster?",0) >= 0)
           {
               //p22
               response = transformDHSSoccerJVRosterStatement();
           }
           else if (findKeyword(statement, "the varsity roster?",0) >= 0)
           {
               //p23
               response = transformDHSSoccerVarsityRosterStatement();
           }
           else
           {   //p24
               response = "What about Dublin high soccer?";
           }
      }
      else if (findKeyword(statement, "thank you", 0) >=0
                || findKeyword(statement, "thanks", 0) >=0)
                {
                    //p25
                    response = "Your Welcome!";
                }
      else if (findKeyword(statement, "you", 0) >=0)
        {
            int psn = findKeyword(statement, "you", 0);
            if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
            {
                //p26
                response = transformYouMeStatement(statement); 
            }
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
        //Remove the final period, if there is one
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
    private String teamJoinStatement()
    {
        return "Tryouts start November 8th but you can find \nmore information on the website by typing \"link\" ";
    }
    private String transformDHSSoccerJVRosterStatement()
    {
        return "The JV Roster for the 2016-2017 season is as follows:\n" +
               "Abotel, Abraham \n" + "Balajadia, Dylan \n" + "Butler, Jacob \n" + "Chopra, Manan \n" + "Costa, Dominic \n" +
               "Deol, Roman \n" + "Dorsett, Noah \n" + "Hailab, Eyobel\n" + "Heddy, Trevor\n" + "Jaggi, Raj" + "Liebetrau, Alex\n" + 
               "McCuen, Logan\n" + "Miranda, Mathew\n";
    }
    private String transformDHSSoccerFroshRosterStatement()
    {
        return "The Frosh Roster for the 2016-2017 season is as follows:\n" +
               "Alarcon, Cy \n" + "Castillo, Ty \n" + "Chiocconi, Francisco \n" + "Elenchezhean, Tharun\n" + "Eteghaei Arian \n" +
               "Furtado, Darren \n" + "Hamidi, Mustafa \n" + "Herrington, Ryan \n" + "Hunt, Elijah \n" + "Jain, Ishaan" + "Kurotori, Kevin\n" + 
               "McCuen, Ethan\n" + "Morrison, Mitchell\n";
    }
    private String transformDHSSoccerVarsityRosterStatement()
    {
        return "The Varsity Roster for the 2016-2017 season is as follows:\n" +
               "Benoit,Andrew \n" + "Calhoun, Jackson \n" + "Castillo, Tony \n" + "Costa, Nick \n" + "Dang, Matthew \n" +
               "Hailib, KC \n" + "Hamidi, Yousuf \n" + "Mendoza, Josh \n" + "Morrison, Alex \n" + "Nguyen, Allen" + "Palma, Nilson\n" + 
               "Rivera, Ivan\n" + "Staples, Grant\n" + "Staples, Victor\n" + "Theard, Zach\n" + "Tuell, Ethan\n" + "Wakaluk, Trent\n";
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
            
            //  lines 356-360 implement 3
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
    private String getRandomResponse() //lines 389-437 implement 2b
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

 

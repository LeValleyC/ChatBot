import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;
public class Sal9000Runner
{
    public static void main(String[] args)
    {
        /** Beginning text to prompt user input */
        Sal9000G sal1 = new Sal9000G();
        Sal9000S sal2 = new Sal9000S();
        System.out.print('\u000C');
        System.out.println (sal1.getGreeting());
        System.out.println ("Type salsoc to switch to Mens Soccer department of Chatbot.");
        System.out.println ("Type salgen to switch to General department of Chatbot.");
        System.out.println ("You cannot switch modes once you have choosen (switch implement soon..)");
        Scanner in = new Scanner (System.in);
        String statement = in.nextLine();
        String fileName = "out.txt";

        while (!(statement.equals("salsoc") || statement.equals("salgen")))
        {
            System.out.println("Please select which chatbot mode to use!");
            statement = in.nextLine();
        }
        /** Switch statement to switch between departments of chatbot */
        switch (statement)
        {
            case "salsoc" :
                System.out.println("You are now being transferred to the Soccer Department.");
                try 
                {
                    Thread.sleep(1500); // delays next action for 1.5 seconds
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println(sal2.hello());
                statement = in.nextLine();
                try {
                    FileWriter fWriter = null;
                    BufferedWriter writer = null;
                    fWriter = new FileWriter("outputSoccer.txt");
                    writer = new BufferedWriter(fWriter);
                while (!(statement.equals("Bye") || statement.equals("bye") || statement.equals("salgen")))
                    {
                        System.out.print('\u000C');// clears screen
                        System.out.println("///////////////////////////////////////////////////////\n" +
                                           "//              Helpful Commands                     //\n" +
                                           "// link      >>> DHS soccer website link             //\n" +
                                           "// salsoc    >>> Switch to SoccerBot  (disabled)     //\n" +
                                           "// salgen    >>> Switch to GeneralBot (disabled)     //\n" +
                                           "// bye       >>> Exit chatbot                        //\n" +
                                           "// help      >>> Basic question starters             //\n" +
                                           "///////////////////////////////////////////////////////");
                        System.out.println (sal2.getResponse(statement));
                            writer.write(statement);
                            writer.newLine();                                        
                            System.err.println("Your input of " + statement.length() + " characters was saved.");
                        statement = in.nextLine();
                        }
                        writer.close();
                    }
                catch (Exception e) {
                            System.out.println("Error!");
                        }
                System.out.println("An output.txt file has been created to log your inputs");
                System.out.println("Goodbye!");
                break;
            case "salgen" :
                System.out.println("You are now being transferred to the General Department.");
                try 
                {
                    Thread.sleep(1500); 
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println(sal1.hello());
                statement = in.nextLine();
                try {
                    FileWriter fWriter = null;
                    BufferedWriter writer = null;
                    fWriter = new FileWriter("outputGeneral.txt");
                    writer = new BufferedWriter(fWriter);
                while (!(statement.equals("Bye") || statement.equals("bye")))
                    {
                        System.out.print('\u000C');
                        System.out.println (sal1.getResponse(statement));
                        writer.write(statement);
                        writer.newLine();                                        
                        System.err.println("Your input of " + statement.length() + " characters was saved.");
                        statement = in.nextLine();
                    }
                    writer.close();
                }
                catch (Exception e) {
                            System.out.println("Error!");
                        }
                System.out.println("An output.txt file has been created to log your inputs");
                System.out.println("Goodbye!");
                break;
            default :
                System.out.println("error"); /** I have not "stupid proofed" by code yet so it
                                                 is easy to break at the moment */
        }
    }
}


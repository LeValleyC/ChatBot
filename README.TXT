------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: DHS Mens Soccer ChatterBot.
PURPOSE OF PROJECT: inform the user about the dhs soccer program while also being a regular chatbot.
VERSION or DATE: v1.1.4
HOW TO START THIS PROJECT: right click on the Sal9000Runner and run main. 
AUTHORS: Conner LeValley and Eric Tang.
USER INSTRUCTIONS: Same as HOW TO START THIS PROJECT.
Program Highlights:

+ Program is separated into 2 compenents the DHS Soccer chatbot, and the General chatbot
  - You can select which chatbot to use at the starting screen
  - Each chatbot has its own unique responses and methods.
+ The Unique code piece would be the fact the our chatbot is essentially 2 chatbots in one.
  - for the purposes of this assignment, the chatbots have not been
    coded to their maximum potential but meet the assignment requirements.
  - I would like to add more keyword statements and filters to both chatbots in the future
    along with a nicer UI.
  - I implemented a switch statement along with a block of logging code to create my unique piece of code.
+ For the Extra Credit piece I choose to do the third option, "Log of all input/response and a plan 
  for using that log to  extend or refine the domain of knowledge for your chatbot.".
  - For each chatbot, I created a unique .txt file for the computer to output the users responses to, depending
   on which chatbot they are using.
  - I plan on using the user input logs to refine my chatbot.
  - To do this I would Run my chatbots output.txt file through a markov chain, which would separate the 
    words of each of the users inputs, then add thos word to a SQL database or a database of some sort.
  - once I have all the words together, I would implement a levenshtien distance formula.The levenshtien
    formula is the amount of steps it takes to take word A and transform it into word B. The formula then takes
    each word and runs it across a database of the english dictionary. If the distance from word A 
    (the word from our output.txt file) is within a specified threshold (lets say 2 steps) than the word is
    autocorrected to the english dictionary word. If people are spelling a word wrong a lot in our chatbot, 
    as shown in our distance formula, than we would implement the levenshtien formula to our chatbot to fix 
    the specified error.
  - Another implementation we could do is if we parse through the output.txt database and we notice that 
    many people who use the chatbot are asking the same question, but there is no implemented answer in 
    our code, we could add and answer to further improve the chatbots capabilities. 
    
   
  

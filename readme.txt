Note: This README was written for Georgetown election officials who do not have programming experience, so it goes through some basics.

This application has been tested on macOS Sierra version 10.12.4.

Once you have the “Election” folder on your desktop (or wherever you’d like to put it on your computer), open Terminal.

Then, cd into the “Election” directory (folder).  On my computer, for example, my “Election” folder is on my Desktop so I enter: cd ~/Desktop/Election
Note that if you are trying to cd into a directory whose name contains a space, you will need to put the name of the directory in quotes.

Enter the following line to compile the code:
javac *.java

Then, to open the application, enter:
java Election

If you close the application and cd into a different folder you will need to cd into the “Election” folder and enter “java Election” again to reopen the application.


In the application, there are three text boxes that must be filled in:
“Input File” is the exact name of the file (ending in .txt) that contains the votes.  This file must be a .txt file containing all votes from the election that you want to run.  Each line should contain one voter’s voting preferences, in order, and separated by the “\t” character (i.e. the tab).  “input_example.txt” shows you an example of how this file should be formatted.  Note that this file should be in the “Election” folder, or, alternatively, you may type in a path to the file, as long as that path starts in the “Election” folder.

“Output File” is the exact name of the file (ending in .txt) that you want the results of the election to appear in.  The file name used must be a new file name.  This file will appear in the “Election” folder, or, alternatively, you may type in a path to where you want the file to appear, as long as that path starts in the “Election” folder.

“# Winners” is the number of open seats that are being filled in the election (i.e. the number of candidates who you want to win the election).  This number should be a whole number and it should be written in integer form (i.e. write “3” not “three”).

Once you have filled in those three fields, click the continue button.

The “Withdraw Candidates” window will be displayed, and you will see a list of checkboxes.  Click on the name of any candidates that you wish to withdraw from contention in the election.  Note that any votes for these candidates will be discounted.  This withdrawing candidates feature is most useful for eliminating write-in candidates from contention, and will result in a more neat output file because fewer candidates will need to be listed.  Note that the top checkbox says “Check all boxes.”  This box is most useful if you wish to withdraw most candidates from the election.  You can click on this checkbox and then uncheck whichever candidates you don’t want to withdraw.

Once you have finished withdrawing candidates, click the “Submit” button.  An output file with the name you gave it will appear in the “Election” folder (or in another folder if you entered a path to another folder).  The Election application will return to the original window, where you can run another election.

{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf820
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 This application has been tested on macOS Sierra version 10.12.4.\
\
Once you have the \'93Election\'94 folder on your desktop (or wherever you\'92d like to put it on your computer), open Terminal.\
\
Then, cd into the \'93Election\'94 directory (folder).  On my computer, for example, my \'93Election\'94 folder is on my Desktop so I enter: cd ~/Desktop/Election\
Note that if you are trying to cd into a directory whose name contains a space, you will need to put the name of the directory in quotes.\
\
Enter the following line to compile the code:\

\b javac *.java
\b0 \
\
Then, to open the application, enter:\

\b java Election
\b0 \
\
If you close the application and cd into a different folder you will need to cd into the \'93Election\'94 folder and enter \'93java Election\'94 again to reopen the application.\
\
\
In the application, there are three text boxes that must be filled in:\
\'93Input File\'94 is the exact name of the file (ending in .txt) that contains the votes.  This file must be a .txt file containing all votes from the election that you want to run.  Each line should contain one voter\'92s voting preferences, in order, and separated by the \'93\\t\'94 character (i.e. the tab).  \'93input_example.txt\'94 shows you an example of how this file should be formatted.  Note that this file should be in the \'93Election\'94 folder, or, alternatively, you may type in a path to the file, as long as that path starts in the \'93Election\'94 folder.\
\
\'93Output File\'94 is the exact name of the file (ending in .txt) that you want the results of the election to appear in.  The file name used must be a new file name.  This file will appear in the \'93Election\'94 folder, or, alternatively, you may type in a path to where you want the file to appear, as long as that path starts in the \'93Election\'94 folder.\
\
\'93# Winners\'94 is the number of open seats that are being filled in the election (i.e. the number of candidates who you want to win the election).  This number should be a whole number and it should be written in integer form (i.e. write \'933\'94 not \'93three\'94).\
\
Once you have filled in those three fields, click the continue button.\
\
The \'93Withdraw Candidates\'94 window will be displayed, and you will see a list of checkboxes.  Click on the name of any candidates that you wish to withdraw from contention in the election.  Note that any votes for these candidates will be discounted.  This withdrawing candidates feature is most useful for eliminating write-in candidates from contention, and will result in a more neat output file because fewer candidates will need to be listed.  Note that the top checkbox says \'93Check all boxes.\'94  This box is most useful if you wish to withdraw most candidates from the election.  You can click on this checkbox and then uncheck whichever candidates you don\'92t want to withdraw.\
\
Once you have finished withdrawing candidates, click the \'93Submit\'94 button.  An output file with the name you gave it will appear in the \'93Election\'94 folder (or in another folder if you entered a path to another folder).  The Election application will return to the original window, where you can run another election.}
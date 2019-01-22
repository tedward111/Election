import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.lang.Math.*;

/**
* The election class has a main method to call the opening of the initial
* application window, but it is primarily responsible for parsing the text
* file to be read, performing all calculations, and printing to the ouput
* file.
* @author   Teddy Willard
* @version  1.0
* @since    2017-08-09
*/

public class Election {
    /** Main method that cals the window class to open the application. */
    public static void main(String[] args){
        window.initialWindow();
    }
    
    /**
    * This method reads through the votes file and creates a list of lists
    * of votes (a list containing each voter's ranked selections) from the
    * info in the file.  It then opens the withdraw window.
    *
    * @param votesFile name of file to be read
    * @param resultsFile name of file to print results to
    * @param winnerNumber number of desired winners
    */
    public static void parseVotes(String votesFile, String resultsFile, String winnerNumber){
        int numberWinners = Integer.parseInt(winnerNumber);
        BufferedReader br = null;
        FileReader fr = null;
        try {
            
            fr = new FileReader(votesFile);
            br = new BufferedReader(fr);
            String line;
            br = new BufferedReader(new FileReader(votesFile));
            int lineNumber = 0;
            int linesBeforeVotes = 0;
            List<LinkedList<String>> votesList = new ArrayList<LinkedList<String>>();
            List<String> candidatesList = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                
                LinkedList<String> individualsVotes = new LinkedList<String>();
                if (lineNumber >= linesBeforeVotes) {
                    
                    /** Assumes "\t" (tab) will be delimiter in the votes
                    * file, may change to another delimiter
                    */
                    String[] votes = line.split("\t");
                    for (int j = 0; j < votes.length; j++) {
                        
                        if (votes[j].length() > 0){
                            
                            if (!candidatesList.contains(votes[j])){
                                
                                candidatesList.add(votes[j]);
                            }
                            
                            individualsVotes.add(votes[j]);
                        }

                    }
                    
                    if (individualsVotes.size() > 0) {
                        
                        votesList.add(individualsVotes);
                    }
                    
                }
                
                lineNumber += 1;
            }
            
            withdrawWindow.withdrawWindow(candidatesList, votesList, resultsFile, numberWinners);

        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
    * This method uses lists of votes to calculate results, and prints
    * those results to an output file.  It then reopens the initial
    * application window.
    *
    * @param voteList list of voters ranked choices
    * @param candidateList list of eligible candidates
    * @param winnersWanted number of winners wanted
    * @param outputFile name of file to print results to
    */
    public static void tabulateResults(List<LinkedList<String>> voteList, List<String> candidateList, int winnersWanted, String outputFile) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFile);
            int round = 1;
            double numberVoters = voteList.size();
            List<String> winnersList = new ArrayList<String>();
            int winnersSoFar = 0;
            List<Double> voterWeight = new ArrayList<Double>();
            for (LinkedList<String> individualVotes: voteList) {
                if (individualVotes.isEmpty()){
                    voteList.remove(individualVotes);
                    numberVoters -= 1;
                }
            }
            for (int i=0; i < numberVoters; i++){
                voterWeight.add(1.000);
            }
            fw.write("Seats Available: " + winnersWanted + "\n");
            double votesNeeded = Math.floor((numberVoters / (winnersWanted + 1)) + 1);
//            int winnersStillNeeded = 0;
            while (winnersSoFar != winnersWanted) {
                fw.write("Round " + String.valueOf(round) + ":\n\n");
                HashMap<String, Double> voteTallies = new HashMap<String, Double>();
                for (String candidate : candidateList){
                    voteTallies.put(candidate, 0.000);
                }
                int voterLocationInList = 0;
                Iterator<LinkedList<String>> iter = voteList.iterator();
                while (iter.hasNext()) {
                    LinkedList<String> individualVoteList = iter.next();
                    int voted = 0;
                    while (voted == 0){
                        String vote = individualVoteList.peekFirst();
                        if (voteTallies.containsKey(vote)){
                            voteTallies.put(vote, voteTallies.get(vote) + voterWeight.get(voterLocationInList));
                            voted = 1;
                        }
                        else if (individualVoteList.isEmpty()){
                            iter.remove();
                            voterWeight.remove(voterLocationInList);
                            voterLocationInList -= 1;
                            voted = 1;
                        }
                        else {
                            individualVoteList.remove();
                        }
                    }
                    voterLocationInList += 1;
                }
                fw.write("Threshold = " + votesNeeded + "\n\n");
                int winnersThisRound = 0;
                fw.write(String.format("%-40s |%20s\r\n", "Candidate", "Tally"));
                for (Map.Entry<String, Double> entry : voteTallies.entrySet()) {
                    String candidateName = entry.getKey();
                    Double tally = entry.getValue();
                    fw.write(String.format("%-40s |%20.3f\r\n", candidateName, tally));
                    if (tally >= votesNeeded){
                        winnersList.add(candidateName);
                        winnersSoFar += 1;
                        winnersThisRound += 1;
                        candidateList.remove(candidateName);
                        double surplusVotes = tally-votesNeeded;
                        int voterSpotInList = 0;
                        for (LinkedList<String> individualVoteList : voteList) {
                            String voteCast = individualVoteList.peekFirst();
                            if (voteCast.equals(candidateName)){
                                double currentWeight = voterWeight.get(voterSpotInList);
                                System.out.println("old" + currentWeight);
                                double newWeight = currentWeight * surplusVotes / tally;
                                System.out.println(surplusVotes);
                                System.out.println("new" + newWeight);
                                voterWeight.set(voterSpotInList, newWeight);
                            }
                            voterSpotInList += 1;
                        }
                    }
                }
                if (winnersThisRound == 0){
                    List<String> lowestVoteTotalList = new ArrayList<String>();
                    double lowestVoteTotal = -1;
                    for (Map.Entry<String, Double> entry : voteTallies.entrySet()) {
                        String candidate = entry.getKey();
                        Double voteTally = entry.getValue();
                        if ((voteTally < lowestVoteTotal) || (lowestVoteTotal == -1)){
                            lowestVoteTotal = voteTally;
                            lowestVoteTotalList.clear();
                            lowestVoteTotalList.add(candidate);
                        }
                        else if (voteTally == lowestVoteTotal){
                            lowestVoteTotalList.add(candidate);
                        }
                    }
                    int winnersStillNeeded = winnersWanted - winnersSoFar;
                    Boolean removeDone = false;
                    if (candidateList.size() > winnersStillNeeded){
                        Collections.shuffle(lowestVoteTotalList);
                        if (lowestVoteTotalList.size() > 1){
                            fw.write("\nTie broken.");
                        }
                        String candidateToRemove = lowestVoteTotalList.get(0);
                        candidateList.remove(candidateToRemove);
                        fw.write("\nRemoved " + candidateToRemove + " from election.");
                        removeDone = true;
                    }
                    if (candidateList.size() == winnersStillNeeded) {
                        for (String candidate : candidateList){
                            winnersList.add(candidate);
                            winnersSoFar += 1;
                            winnersThisRound += 1;
//                            candidateList.clear();
                        }
                    }
                }
//                    if (candidateList.size() == winnersStillNeeded) {
//                        for (String candidate : candidateList){
//                            winnersList.add(candidate);
//                            winnersSoFar += 1;
//                            winnersThisRound += 1;
//                        }
//                    }
                fw.write("\nWinners so far:");
                for (String winningCandidate : winnersList){
                    fw.write("\n" + winningCandidate);
                }
                round += 1;
                fw.write("\n\n\n\n\n");
            }
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fw != null)
					fw.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
		}
    } 
}

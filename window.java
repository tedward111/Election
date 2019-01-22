import javax.swing.*; 
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
* The window class contains one method, initialWindow, which uses JFrame
* to create an initial window, where a user enters the name of the file
* that contains votes, the name of the uncreated file that will contain
* results and the number of candidates they want to win the election.
*/

public class window {
    
    public static void initialWindow() {
        final JFrame frame = new JFrame("Election");
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(new BoxLayout(initialPanel, BoxLayout.Y_AXIS));
        initialPanel.setBorder(new EmptyBorder(new Insets(0, 0, 50, 50)));
        final JTextField inputFile = new JTextField("Input File");
        inputFile.setToolTipText("Enter the name of the .txt file containing votes.");
        inputFile.setForeground(Color.GRAY);
        inputFile.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (inputFile.getForeground() == Color.GRAY){
                    inputFile.setText("");
                    inputFile.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (inputFile.getText().equals("")){
                    inputFile.setText("Input File");
                    inputFile.setForeground(Color.GRAY);
                }
            }
        });
        final JTextField outputFile = new JTextField("Output File");
        outputFile.setToolTipText("Enter the name of the .txt file that you want the results to appear in.  Choose a file name that does not already exist.");
        outputFile.setForeground(Color.GRAY);
        outputFile.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (outputFile.getForeground() == Color.GRAY){
                    outputFile.setText("");
                    outputFile.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (outputFile.getText().equals("")){
                    outputFile.setText("Output File");
                    outputFile.setForeground(Color.GRAY);
                }
            }
        });
        final JTextField numberWinners = new JTextField("# Winners");
        numberWinners.setToolTipText("Enter the number of winners that should result from this election.");
        numberWinners.setForeground(Color.GRAY);
        numberWinners.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (numberWinners.getForeground() == Color.GRAY){
                    numberWinners.setText("");
                    numberWinners.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (numberWinners.getText().equals("")){
                    numberWinners.setText("# Winners");
                    numberWinners.setForeground(Color.GRAY);
                }
            }
        });
        JButton continuer = new JButton("Continue");
        continuer.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String input = inputFile.getText();
                final String output = outputFile.getText();
                final String winners = numberWinners.getText();
                int parseVotes = 1;
                if (!(input.endsWith(".txt"))){
                    JOptionPane.showMessageDialog(frame, "Input File Must End in \".txt\"");
                    parseVotes = 0;
                }
                if (!(output.endsWith(".txt"))){
                    JOptionPane.showMessageDialog(frame, "Output File Must End in \".txt\"");
                    parseVotes = 0;
                }
                if (!(winners.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(frame, "Number of winners must only contain numeric digits.");
                    parseVotes = 0;
                }
                if (parseVotes == 1) {
                    frame.setVisible(false);
                    frame.dispose();
                    Election.parseVotes(input, output, winners);
                }
            }
        });
        initialPanel.add(inputFile);
        initialPanel.add(outputFile);
        initialPanel.add(numberWinners);
        initialPanel.add(continuer);
        initialPanel.setPreferredSize(new Dimension(300, 110));
        frame.add(initialPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  
    }
}
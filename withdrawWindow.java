import javax.swing.*; 
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
* The withdrawWindow class contains one method, withdrawWindow, which uses
* JFrame to create a window, where a user clicks on candidates they want
* to withdraw from the election.
*/

public class withdrawWindow {
    public static void withdrawWindow(final java.util.List<String> candidatesList, final java.util.List<LinkedList<String>> votesList, final String resultsFile, final int numberWinners) {
        final JFrame frame = new JFrame("Withdraw Candidates");
        JPanel withdrawPanel = new JPanel();
        withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));
        withdrawPanel.setBorder(new EmptyBorder(new Insets(0, 0, 50, 50)));
        final JCheckBox checkAll = new JCheckBox("Check All Boxes");
        withdrawPanel.add(checkAll);
        final java.util.List<JCheckBox> checkboxList = new ArrayList<JCheckBox>();
        for (final String candidate : candidatesList){
            final JCheckBox withdrawCheckbox = new JCheckBox(candidate);
            checkboxList.add(withdrawCheckbox);
            withdrawPanel.add(withdrawCheckbox);
            withdrawCheckbox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (withdrawCheckbox.isSelected()) {
                        candidatesList.remove(candidate);
                    }
                    else {
                        candidatesList.add(candidate);
                    }
                }
            });
        }
        checkAll.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (checkAll.isSelected()) {
                    for (JCheckBox withdrawCheckbox : checkboxList) {
                        withdrawCheckbox.setSelected(true);
                    }
                }
                else {
                    for (JCheckBox withdrawCheckbox : checkboxList) {
                        withdrawCheckbox.setSelected(false);
                    }
                }
            }
        });
        JButton submit = new JButton("Submit");
        withdrawPanel.add(submit);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                Election.tabulateResults(votesList, candidatesList, numberWinners, resultsFile);
                String[] arguments = new String[] {};
                Election.main(arguments);
            }
        });
        final JScrollPane scroll = new JScrollPane(withdrawPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 0, 400, 500);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                scroll.setBounds(0, 0, frame.getBounds().width, frame.getBounds().height);
            }
        });
        scroll.getVerticalScrollBar().setUnitIncrement(3);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(400, 500));
        contentPane.add(scroll);
        frame.setLayout(new BorderLayout());
        frame.add(contentPane);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
package GraphicInterface;

import javax.swing.*;

public class UserInteraction extends JFrame{
    private JPanel panel1;
    private JButton sendButton;
    private JEditorPane userMessageEditorPane;
    private JEditorPane chatEditorPane;

    private JPanel opponentOptionPanel;
    private JLabel opponentOptionLabel;

    private JPanel rockOptionPanel;
    private JPanel papesOptionPanel;
    private JPanel scissorsOptionPanel;

    private JLabel rockImageLabel;
    private JLabel paperImageLabel;
    private JLabel scissorsImageLabel;

    private JLabel instructionalLabel;
    private JPanel instructionalPanel;

    public UserInteraction() {
        setContentPane(panel1);
        this.setTitle("Welcome");
        setSize(1055, 745);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        UserInteraction userInteraction = new UserInteraction();
    }
}


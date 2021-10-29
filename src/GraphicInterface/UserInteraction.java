package GraphicInterface;

import ControllerInterface.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInteraction extends JFrame{
    private JPanel mainPanel;
    private JButton sendButton;
    private JEditorPane userMessageEditorPane;
    private JEditorPane chatEditorPane;

    private JPanel opponentOptionPanel;
    private JLabel opponentOptionLabel;

    private JPanel rockOptionPanel;
    private JPanel paperOptionPanel;
    private JPanel scissorsOptionPanel;

    private JLabel rockImageLabel;
    private JLabel paperImageLabel;
    private JLabel scissorsImageLabel;

    private JLabel instructionalLabel;
    private JPanel instructionalPanel;

    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;

    private Controller controller;

    public ImageSetterForUser getImageSetterForUser() {
        return imageSetterForUser;
    }

    private ImageSetterForUser imageSetterForUser;

    public SetInstructionalLabel getSetInstructionalLabel() {
        return setInstructionalLabel;
    }

    public ImageSetterForOpponent getImageSetterForOpponent() {
        return imageSetterForOpponent;
    }

    private ImageSetterForOpponent imageSetterForOpponent;

    private SetInstructionalLabel setInstructionalLabel;

    public UserInteraction(Controller controller) {
        this.controller = controller;
        setContentPane(mainPanel);
        this.setTitle("Rock Paper Scissors");
        setSize(1055, 745);

        sendButton.setBorderPainted(false);

        this.imageSetterForUser = new ImageSetterForUser();

        this.imageSetterForOpponent =  new ImageSetterForOpponent();

        this.setInstructionalLabel = new SetInstructionalLabel();

        new SetLabelButtons();

        this.addWindowListener(new WindowEventHandler());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        actionListenerController();
    }

    public void actionListenerController(){
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.messageFromUser(userMessageEditorPane.getText());

                chatEditorPane.setText(chatEditorPane.getText()+ "Me: "+userMessageEditorPane.getText()+"\n" ); // send to controller
                //receiver.sendMessageFromUser( textArea1.getText() ); // string
                userMessageEditorPane.setText("");
            }
        });

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // only if action is possible

                // call function to controller

                //if(Controller.optionAccepted("rock")) {
                    setInstructionalLabel.messageSetInstructionalLabel("wait");
                    imageSetterForUser.blankImageSetterForUser();
                    new SetLabelButtons().removeButtons();
                //}
            }
        });

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // call function to controller
               // if(Controller.optionAccepted("paper")) {
                    setInstructionalLabel.messageSetInstructionalLabel("wait");
                    imageSetterForUser.blankImageSetterForUser();
                    new SetLabelButtons().removeButtons();
               // }
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // call function to controller
              // if(Controller.optionAccepted("scissors")) {
                    setInstructionalLabel.messageSetInstructionalLabel("wait");
                    imageSetterForUser.blankImageSetterForUser();
                    new SetLabelButtons().removeButtons();
               // }
            }
        });
    }

    public class ImageSetterForUser {
        // set rock image for user
        private void setRockImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/rock.png");
            imgThisImg.getImage().flush();
            rockImageLabel.setIcon(imgThisImg);
        }

        // set paper image for user
        private void setPaperImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/paper.png");
            imgThisImg.getImage().flush();
            paperImageLabel.setIcon(imgThisImg);
        }

        // set scissors image for user
        private void setScissorsImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/scissors.png");
            imgThisImg.getImage().flush();
            scissorsImageLabel.setIcon(imgThisImg);
        }

        public void optionImageSetterForUser() {
            setRockImageForUser();
            setPaperImageForUser();
            setScissorsImageForUser();
        }

        // set blank image for user
        private void setBlankRockImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/blank.png");
            imgThisImg.getImage().flush();
            rockImageLabel.setIcon(imgThisImg);
        }

        // set blank image for user
        private void setBlankPaperImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/blank.png");
            imgThisImg.getImage().flush();
            paperImageLabel.setIcon(imgThisImg);
        }

        // set blank image for user
        private void setBlankScissorsImageForUser() {
            ImageIcon imgThisImg = new ImageIcon("src/images/blank.png");
            imgThisImg.getImage().flush();
            scissorsImageLabel.setIcon(imgThisImg);
        }

        public void blankImageSetterForUser() {
            setBlankRockImageForUser();
            setBlankPaperImageForUser();
            setBlankScissorsImageForUser();
        }

        public ImageSetterForUser() {
        }
    }

    public class SetLabelButtons {

        private void setRockButton() {
            rockImageLabel.setLayout( new FlowLayout() );

            rockButton = new JButton("");

            rockButton.setPreferredSize(new Dimension(200, 200));
            rockButton.setMaximumSize(new Dimension(200, 200));
            rockButton.setMinimumSize(new Dimension(200, 200));

            rockButton.setHorizontalAlignment(JTextField.CENTER);
            rockButton.setVerticalAlignment(JTextField.CENTER);

            rockButton.setOpaque(false);
            rockButton.setContentAreaFilled(false);
            rockButton.setBorderPainted(false);

            rockImageLabel.add(rockButton);
        }

        private void setPaperButton() {
            paperImageLabel.setLayout( new FlowLayout() );

            paperButton = new JButton("");

            paperButton.setPreferredSize(new Dimension(200, 200));
            paperButton.setMaximumSize(new Dimension(200, 200));
            paperButton.setMinimumSize(new Dimension(200, 200));

            paperButton.setHorizontalAlignment(JTextField.CENTER);
            paperButton.setVerticalAlignment(JTextField.CENTER);

            paperButton.setOpaque(false);
            paperButton.setContentAreaFilled(false);
            paperButton.setBorderPainted(false);
        }

        private void setScissorsButton() {
            scissorsImageLabel.setLayout( new FlowLayout() );

            scissorsButton = new JButton("");

            scissorsButton.setPreferredSize(new Dimension(200, 200));
            scissorsButton.setMaximumSize(new Dimension(200, 200));
            scissorsButton.setMinimumSize(new Dimension(200, 200));

            scissorsButton.setHorizontalAlignment(JTextField.CENTER);
            scissorsButton.setVerticalAlignment(JTextField.CENTER);

            scissorsButton.setOpaque(false);
            scissorsButton.setContentAreaFilled(false);
            scissorsButton.setBorderPainted(false);

            scissorsImageLabel.add(scissorsButton);
        }

        private void addButtons() {
            rockImageLabel.add(rockButton);

            paperImageLabel.add(paperButton);

            scissorsImageLabel.add(scissorsButton);
        }

        private void removeButtons() {
            rockImageLabel.remove(rockButton);

            paperImageLabel.remove(paperButton);

            scissorsImageLabel.remove(scissorsButton);
        }

        public SetLabelButtons() {
            setRockButton();
            setPaperButton();
            setScissorsButton();

            addButtons();
        }

        public SetLabelButtons(String add) {
            addButtons();
        }

        public SetLabelButtons(String remove, String remove1) {
            removeButtons();
        }
    }

    public class ImageSetterForOpponent {

        // set rock / paper / scissors / blank image for Opponent
        private void setOptionImageForOpponent(String imageName) {
            ImageIcon imgThisImg = new ImageIcon("src/images/" + imageName + ".png");
            imgThisImg.getImage().flush();
            opponentOptionLabel.setIcon(imgThisImg);
        }

        public void stringImageSetterForOpponent(String option) {
            setOptionImageForOpponent(option);
        }

        public ImageSetterForOpponent() {}
    }

    public class SetInstructionalLabel {

        private void setWait(){
            instructionalLabel.setText("Wait for opponent!");
        }

        private void setChoose() {
            instructionalLabel.setText("Choose your move!");
        }

        private void setWin() {
            instructionalLabel.setText("You win!");
        }

        private void setLose() {
            instructionalLabel.setText("You lose!");
        }

        private void setDraw() {
            instructionalLabel.setText("Draw.");
        }

        private void setWelcome() { instructionalLabel.setText("Welcome! The Game will begin shortly."); }

        private void setWrong() { instructionalLabel.setText("Wrong message."); }

        public void messageSetInstructionalLabel(String message) {
            switch (message){
                case "wait":
                    setWait();
                    break;
                case "choose":
                    setChoose();
                    break;
                case "win":
                    setWin();
                    break;
                case "lose":
                    setLose();
                    break;
                case "draw":
                    setDraw();
                    break;
                case "welcome":
                    setWelcome();
                    break;
                default:
                    setWrong();
            }

        }

        public SetInstructionalLabel(){}
    }

    public void addMessageReceivedFromPeer(String message) {

        chatEditorPane.setText(chatEditorPane.getText() + "Opponent: " + message+"\n" );
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            controller.windowClosed();
        }
    }

}


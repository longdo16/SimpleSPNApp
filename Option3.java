import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Option3 {
    private ArrayList<String> list;
    private int score;
    private StringBuilder text;

    Option3(Scanner sc) throws IOException {
        score = 0;
        text = new StringBuilder("");
        File file = new File("Indi&di.txt");
        sc = new Scanner(file);
        int size = Integer.parseInt(sc.nextLine());
        list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(sc.nextLine());
        }
        while(sc.hasNextLine()) {
            text.append(sc.nextLine());
            text.append('\n');
        }
    }

    public void runOption3(Scanner sc) throws IOException {
        instruction();
        displayText();
        int count = 0;
        for(String key: list) {
            String input = JOptionPane.showInputDialog("Enter answer for # " + (count + 1) + ": ");
            if(key.equals(input)) {
                JOptionPane.showMessageDialog(null, "Correct");
                score++;
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong\n Correct answer is: " + key);
            }
            updateText(count + 1, key);
            displayText();
            count++;
        }
        JOptionPane.showMessageDialog(null, "Your score is: " + score);
    }

    private void displayText() {
        JTextArea msg = new JTextArea(50, 100);
        msg.setText(text.toString());
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(msg);
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    private void instruction() {
        JOptionPane.showMessageDialog(null, "Complete the dialogue with the appropriate pronouns.");
    }

    private void updateText(int num, String str) {
        int offset = text.indexOf("#" + num) + 3;
        text.insert(offset, str);
    }
}
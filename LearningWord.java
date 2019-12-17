import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class LearningWord {
    private Map<String, String> map;
    private int score;
    private int total;

    LearningWord(Scanner sc) throws IOException {
        total = 0;
        score = 0;
        map = new HashMap<>();
        File file = new File("vocabulary.txt");
        sc = new Scanner(file);
        while(sc.hasNextLine()) {
            map.put(sc.nextLine(), sc.nextLine());
            total++;
        }
        clearScreen();
        JOptionPane.showMessageDialog(null, "Find the word for the given definition");
    }

    void playGame(Scanner sc) throws IOException {
        sc = new Scanner(System.in);
        int count = 0;
        for(String key: map.keySet()) {
            displayText(key, count + 1);
            String answer = JOptionPane.showInputDialog("Your answer: ");
            if(map.get(key).equals(answer)) {
                JOptionPane.showMessageDialog(null, "Correct");
                score++;
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong\n" + "Correct answer is: " + map.get(key));
            }
            count++;
            clearScreen();
        }
    }

    private void displayText(String text, int num) {
        JTextArea msg = new JTextArea(25, 25);
        msg.setText("Quesion " + num + "\n" + text);
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(msg);
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    void displayInfo() {
        JOptionPane.showMessageDialog(null, "Your score is: " + score + "\n" + (double)(score) / total + "%");
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
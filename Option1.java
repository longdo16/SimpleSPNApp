import java.util.Scanner;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.*;

public class Option1 {
    private int score;
    private Map<String, String> map;

    Option1(Scanner sc) throws IOException {
        map = new HashMap<>();
        score = 0;
        File file = new File("Ser&Estar&Haber.txt");
        sc = new Scanner(file);
        String temp1, temp2;
        while(sc.hasNextLine()) {
            temp1 = sc.nextLine();
            temp2 = sc.nextLine();
            map.put(temp1, temp2);
        }
    }

    public void runOption1(Scanner sc) throws IOException {
        instruction();
        int count = 0;
        String answer;
        sc = new Scanner(System.in);
        for(String key: map.keySet()) {
            answer = JOptionPane.showInputDialog("Question" + (count + 1) +":\n" + key + "\nAnswer:");
            if(answer.equals(map.get(key))) {
                JOptionPane.showMessageDialog(null, "Correct");
                score++;
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong\n" + "Correct answer is:" + map.get(key));
            }
            count++;
        }
        JOptionPane.showMessageDialog(null, "Your score is: " + score);
    }

    public void instruction() {
        JOptionPane.showMessageDialog(null, "Choose between a form of ser, haber or estar to complete the sentences.\nSeparate answers with comma");
    }
}
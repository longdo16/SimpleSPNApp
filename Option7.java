import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.*;  

public class Option7 {
    private ArrayList<Character> key;
    private ArrayList<String[]> answer;
    private int score;
    private StringBuilder text;

    Option7(Scanner sc) throws IOException {
        key = new ArrayList<>();
        answer = new ArrayList<>();
        text = new StringBuilder("");
        score = 0;
        File file = new File("Preterit&Imperfect.txt");
        sc = new Scanner(file);
        int size = Integer.parseInt(sc.nextLine());
        String[] arr;
        for(int i = 0; i < size; i++) {
            key.add(sc.nextLine().charAt(0));
            arr = new String[3];
            arr[0] = sc.nextLine();
            arr[1] = sc.nextLine();
            arr[2] = sc.nextLine();
            answer.add(arr);
        }
        while(sc.hasNextLine()) {
            text.append(sc.nextLine());
            text.append('\n');
        }
    }

    public void runOption7(Scanner sc) throws IOException {
        JOptionPane.showMessageDialog(null, "Instuction: enter A to pick left option and B to pick right option");
        displayText();
        int count = 0;
        while(count < key.size() && count < answer.size()) {
            String input = JOptionPane.showInputDialog("Enter answer for # " + (count + 1) + ": ");
            char ch = input.toUpperCase().charAt(0);
            String[] arr = answer.get(count);
            if(ch == key.get(count)) {
                JOptionPane.showMessageDialog(null, arr[0]);
                score++;
            }
            else {
                JOptionPane.showMessageDialog(null, arr[1]);
            }
            updateText(count + 1, arr[2]);
            displayText();
            System.out.println();
            count++;
        }
        JOptionPane.showMessageDialog(null, "Your score is: " + score + "/" + count);
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
        System.out.println("Enter A to pick left option");
        System.out.println("Enter B to pick right option");
    }

    private void update(int num) {
        int offset = text.indexOf(num + "");
        text.replace(offset - 1, offset, (num + 1) + "");
    }

    private void updateText(int num, String str) {
        int offset = text.indexOf(num + ")") + 3;
        text.insert(offset, str);
        offset = text.indexOf("#" + num) + 5;
        text.insert(offset, str);
    }
}
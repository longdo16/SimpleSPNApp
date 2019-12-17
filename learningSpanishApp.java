import java.util.Scanner;
import java.io.*;
import javax.swing.*;  

public class learningSpanishApp {
    public static void main(String[] args) throws IOException {
        clearScreen();
        Scanner sc = new Scanner(System.in);
        String again;
        do {
            int choice =  intro();
            clearScreen();
            if(choice == 1) {
                LearningWord learn = new LearningWord(sc);
                learn.playGame(sc);
                learn.displayInfo();
            }
            else if(choice == 2) {
                clearScreen();
                int grammarChoice = grammarIntro();
                clearScreen();
                if(grammarChoice == 1) {
                    Option1 op1 = new Option1(sc);
                    op1.runOption1(sc);
                }
                else if(grammarChoice == 2) {
                    Option3 op3 = new Option3(sc);
                    op3.runOption3(sc);                
                }
                else {
                    Option7 op7 = new Option7(sc);
                    op7.runOption7(sc);
                }
            }
            else {
                break;
            }

            again = JOptionPane.showInputDialog("Enter y or Y to return to main menu\n Or enter n or N to quit");
        } while(again.charAt(0) == 'Y' || again.charAt(0) == 'y');
        System.out.println("Task completed successfully");
    }
    
    public static int intro() {
        String input = JOptionPane.showInputDialog("Option:\n1. Vocabulary \n2. Grammar\n3. Quit\nEnter your option:");
        return  Integer.parseInt(input);
    }

    public static int grammarIntro() {
        String input = JOptionPane.showInputDialog("Grammar options:\n1. Ser, estar and haber (hay)\n2. Indirect and direct object pronouns\n3. Preterit and Imperfect\nEnter your option:");
        return  Integer.parseInt(input);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}


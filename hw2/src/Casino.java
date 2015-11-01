import java.util.*;
import java.lang.*;
import java.io.*;

class Casino {   
	public String name;
	public Scanner scanner = new Scanner(System.in);

    public void GameStart(int money){
        System.out.println("POOCasino Jacks or Better, written by B03202017 Yi-Ting, Lee.");
        System.out.println("Welcome to POOCasino! I am Eating.");
        System.out.print("Who are you? (Please enter your name): ");
        name = scanner.nextLine();
        System.out.printf("Hello, %s.\n", name);
        System.out.println("You have " + money + " P-dollars now."); 
    } 

    public void GameFinish(String name, int round, int money){
        System.out.println("Good bye, "+ name + ".");
        System.out.print("You played for " + round + " round");
        if (round != 1)
            System.out.print("s");
        System.out.println(" and have " + money + " P-dollars now.");
    }
}
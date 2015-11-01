import java.util.*;
import java.lang.*;
import java.io.*;

class Card{ //show cards
    public static void ShowCard(int x){ 
        String suit, number;
        
        if (x%4==0) suit = "C";
        else if (x%4==1) suit = "D";
        else if (x%4==2) suit = "H";
        else suit = "S";
        if (x<36) 
            number = Integer.toString((x+8)/4); //card no.(0 to 3) for card 2, and so on.
        else if (x<40) number = "J"; //card no.(36 to 39) for card J.
        else if (x<44) number = "Q";
        else if (x<48) number = "K";
        else number = "A";
        
        System.out.printf("%s ",(suit+number)); 
    }
}
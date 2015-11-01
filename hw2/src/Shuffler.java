import java.util.*;
import java.lang.*;
import java.io.*;

class Shuffler{ //Shuffler do anything relative to draw cards 
	private int i, j, r;
	private Random ran = new Random();

    public void ShuffleCard(int deck[], int N){ //Shuffler shuffle the deck after a new round
    	int x[] = new int[N]; //permute index
        int x0[]= new int[N]; //initialized index
        
        for (i=0; i<N; i++) x0[i] = i; 
         
    	for (i=0; i<N; i++){
            r = ran.nextInt(N-i); //random choose one card 
            deck[i] = x0[r]; //put the choosen card into the i order of the deck
            for (j=r; j<x0.length-1; j++) x0[j] = x0[j+1]; //avoid repeat draw 
        }
    } 

    public void FirstDraw(int deck[], int card[], String option[]){ //first draw
        for (i=0; i<5; i++){
            r = ran.nextInt(52-i);
            card[i] = deck[r];
            for (j=r; j<52-1-i; j++){ //avoid repeat draw
                deck[j] = deck[j+1];
            }
        }
    	Arrays.sort(card); //sort the cards

        System.out.print("Your cards are ");
        for (i=0; i<5; i++){
            System.out.print(option[i]);
            Card.ShowCard(card[i]);
        }
        System.out.println();
    }

    public void Exchange(int deck[], int card[], int keep[]){ //exchange the card
        for (i=0; i<keep.length; i++){
            if (keep[i] == 0){ //card for discard
                r = ran.nextInt(52-5-i);
                card[i] = deck[r];
                for (j=r; j<52-6-i; j++){ //avoid repeat draw
                    deck[j] = deck[j+1];
                }
            }
        }
        Arrays.sort(card); //sort the cards

        System.out.print("Your new cards are ");
        for (i=0; i<5; i++) 
            Card.ShowCard(card[i]);
        System.out.println(".");
    }    
}
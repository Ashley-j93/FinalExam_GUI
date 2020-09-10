/*
Jennie Ashley  shuffle NO REPEATS

gui version from Deitel's book.
november 29 thursday

played with 52 cards
    RULES:
            one pair:        5, 5, 6, 7, 8, 
            two pairs:       5, 5, 6, 6, 7, 
            three of a kind: 5, 5, 5, 6, 7, 
            full house:      5, 5, 5, 6, 6
            four  of a kind: 5, 5, 5, 5, 7
 */

/**
 *
 * @author ashleyj
 */
import java.util.List;
public class Poker_1b {
    //CardDeck deck1 = new CardDeck();
    
   
    public static void main(String arg[]){
        //CardDeck main = deck1;
        Poker_1b test = new Poker_1b();
        
        
        GUI gameGUI = new GUI();
        CardDeck deck2 = new CardDeck();
        deck2.shuffle();
        
        /*Card hand1[];
        hand1 = new Card[5];
        Card hand2[];
        hand2 = new Card[5];
        test.getHand1(deck2, hand1);
        test.getHand2(deck2, hand2);
        //hand1 = test.getHand1(deck2, hand1);
        System.out.println("hand1[3]: " + hand1[3]);
        System.out.println("hand2[3]: " + hand2[3]);*/
        deck2.printDeck();
    }//main
    
    /*void getHand1(CardDeck a, Card h1[]){
        for (int i = 0; i < 5; i++) {
            h1[i]=  a.getCard(i);
            System.out.println("h1["+ i +"]: " + h1[i]);
      }
      //return h1;
    }
    void getHand2(CardDeck a, Card h2[]){ //Card[]
        int count = 0;
      for (int i = 5; i < 10; i++) {
          h2[count]=  a.getCard(i);
          System.out.println("h2["+ count +"]: " + h2[count]);
          count++;
      }
    }*/
}

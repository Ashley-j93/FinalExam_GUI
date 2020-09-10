/*
deck of cards
 */

/**
 *
 * @author ashleyj
 */
import java.util.*;
public class CardDeck {
    private Card deck[];
    String curFace, curSuit;
    Card hand1[]; Card hand2[];
    String faces[]={"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    String suits[]={"Hearts", "Diamonds", "Clubs", "Spades"};
    
    public CardDeck(){
        deck = new Card[52];
        hand1 = new Card[5];
        hand2 = new Card[5];
        for(int i = 0; i < deck.length; i++){
            curFace = faces[i%13];
            curSuit =  suits[i/13];
            deck[i] = new Card(faces[i%13], suits[i/13]);
            //System.out.println("i=: "+ i + "  "+ deck[i].toString()); //use this to check all output if needed
            //System.out.println(deck[i].toString());
        }
    }
    
    public void shuffle(){
        for(int i = 0; i < deck.length; i++){
            int r = (int) (Math.random()*52); // r is random number
            Card temp = deck[i];
            deck[i] = deck[r];
            deck[r]= temp;
            //System.out.println("i=: "+ i + "  "+ deck[i].toString()); //use this to check all output if needed
            //System.out.println("after: " + deck[i].toString());

        }    
    }
    void printDeck(){
        for(int i = 0; i < deck.length; i++){
            System.out.println(deck[i].toString());
        }
    }//printdeck
    
     public String toString(int i) {
        
        return deck[i].toString();
        
    }
     
    public Card getCard(int n){
        Card temp = new Card(this.deck[n].getFace(), this.deck[n].getSuit() );
        return temp;
    }
    
     
     
   /*  public void deal(){
        for(int i = 0; i < hand1.length; i++){
            Card dealt = dealCard();
            
            
            
        }    
    }
     
   public Card dealCard(){
    if(++curCard<deck.length)
        return deck[curCard];
    }*/
}

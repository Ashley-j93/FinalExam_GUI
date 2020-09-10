/*

 */

/**
 *
 * @author ashleyj
 */
public class Card {
    private String suit;
    private String face;
    
    
    Card(String f, String s){ //face suit input
        face = f;
        suit = s;
    }
    
    public void setFace(String f){ face = f;}
    public void setSuit(String s){ suit = s;}
    String getFace(){ return face; }
    String getSuit(){ return suit; }

    @Override
    public String toString() {
        return "Card{" + suit + ", " + face + '}';
    }


    
    void printCard(){
        System.out.println("Face: " + face + "suit: " + suit);
    }
}

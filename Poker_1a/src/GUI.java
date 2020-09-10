/*

final_exam poker
jennie ashley

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author ashleyj
 */
public class GUI extends JFrame{
    //private int match_count;
    Color medGray = new Color(0, 0, 0, 100);//Color(int r, int g, int b, int a)
    //Color medGray = new Color(0, 0, 0, 100);//Color(int r, int g, int b, int a)
    Color darkGray = new Color(0, 0, 0, 160);
    Color lightGray = new Color(0, 0, 0, 70);
    Color lighterGray = new Color(0, 0, 0, 20);
    private JMenuBar menu_Bar;
    private JMenu file_Menu;
    private JMenuItem exit_Poker;
    private JMenu options_Menu;
    private JMenuItem shuffleDeck_menu, dealHand1_menu, dealHand2_menu, twoKind_hand1_menu, threeKind__hand2_menu, clearText_menu, value_hand1_menu, value_hand2_menu, 
            winnerIs_menu; 
    private JMenuItem printInfoItem;
    private JScrollPane scrollDeck, scrollH1Match, scrollH2Match, hand1Scroll, hand2Scroll, winnerScroll;
    JPanel mainPanel, deckPanel, westPanel, eastPanel, matchH1Panel, matchH2Panel, centerPanel, hand1Panel, hand2Panel, 
            southPanel, winnerPanel, southInnerPanel, northPanel;
    JButton simulateBtn, shuffleBtn;
    JTextArea outputTA, hand1TA, hand2TA, matchH1TA, matchH2TA, winnerTA;
    JLabel shuffle_label, hand1_label, hand2_label, hand1_val_label, hand2_val_label, winner_label, north_label;
    private String faces[], suits[];
    int count_array [];
    Poker_1b game = new Poker_1b();
    CardDeck deck1 = new CardDeck();
    Card hand1[] = new Card[5];
    Card hand2[] = new Card[5];
    
    //boolean hand1_2kind, hand2_2kind, hand1_3kind, hand2_3kind;
    
    GUI() {
        count_array = new int[13];
        String f[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String s[] = {"Hearts", "Diamonds", "Clubs", "Spades"};
        faces = f;
        suits = s;
        setTitle("Poker");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMenuBar();
        buildPanel();
        add(mainPanel);
        setVisible(true);
    }
    void buildMenuBar(){
        menu_Bar = new JMenuBar();
        buildFileMenu();
        buildOptionsMenu();
        menu_Bar.add(file_Menu);
        menu_Bar.add(options_Menu);
        setJMenuBar(menu_Bar);
    }
    void buildFileMenu(){
        file_Menu=new JMenu("File");
        exit_Poker = new JMenuItem("Exit");
        exit_Poker.addActionListener(new ExitListener());
        file_Menu.add(exit_Poker);
    }
    private class ExitListener implements ActionListener{
       public void actionPerformed(ActionEvent e){
           System.exit(0);
       }
   }
    void buildOptionsMenu() {
        options_Menu=new JMenu("Options");
        shuffleDeck_menu = new JMenuItem("Shuffle");
        shuffleDeck_menu.addActionListener(new shuffleListener());
        dealHand1_menu = new JMenuItem("Deal#1");
        dealHand1_menu.addActionListener(new deal1Listener());
        dealHand2_menu = new JMenuItem("Deal#2");
        dealHand2_menu.addActionListener(new deal2Listener());
        value_hand1_menu= new JMenuItem("ValueHand#1");
        value_hand1_menu.addActionListener(new value1Listener());
        value_hand2_menu= new JMenuItem("ValueHand#2");
        value_hand2_menu.addActionListener(new value2Listener());
        clearText_menu =new JMenuItem("Clear Text");
        clearText_menu.addActionListener(new clearTextListener());
        winnerIs_menu= new JMenuItem("isWinner");
        winnerIs_menu.addActionListener(new winnerListener());        
        options_Menu.add(shuffleDeck_menu);
        options_Menu.addSeparator();
        options_Menu.add(dealHand1_menu);
        options_Menu.addSeparator();
        options_Menu.add(dealHand2_menu);
        options_Menu.addSeparator();
        options_Menu.add(value_hand1_menu);
        options_Menu.addSeparator();
        options_Menu.add(value_hand2_menu);
        options_Menu.addSeparator();
        options_Menu.add(winnerIs_menu);
        options_Menu.addSeparator();
        options_Menu.add(clearText_menu);
    } 
    private class shuffleListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String inputStr;
            int nr;
            deck1.shuffle();
            for (int i = 0; i < 52; i++) {
                outputTA.append(" " + deck1.toString(i) + "\n");
            }
            deck1.printDeck();
        }
    }
    private class deal1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            getHand1(deck1, hand1);
            for (int i = 0; i < 5; i++) {
                hand1TA.append(" " + hand1[i].toString() + "\n");
            }
        }
    }
    private class deal2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //getHand1(deck1, hand1);
            getHand2(deck1, hand2);
            for (int i = 0; i < 5; i++) {
                hand2TA.append(" " + hand2[i].toString() + "\n");
            }

        }
    }
    private class winnerListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            getHand2(deck1, hand2);
            getHand1(deck1, hand1);
            //boolean h1_wins = false;
            //boolean h2_wins = false;
            handMatchCount(deck1, hand1);
            int h1_arr[] = count_array;
            handMatchCount(deck1, hand2);
            int h2_arr[] = count_array;
            boolean h1_2kind = is2ofKind(h1_arr);
            boolean h1_3kind = is3ofKind(h1_arr);
            boolean h2_2kind = is2ofKind(h2_arr);
            boolean h2_3kind = is3ofKind(h2_arr);
            boolean h1_pairs = is2ofKindPair(h1_arr);
            boolean h2_pairs = is2ofKindPair(h2_arr);
            /*winnerTA.append("h1_2kind: " + h1_2kind + "\n");
            winnerTA.append("h2_2kind: " + h2_2kind+ "\n");
            winnerTA.append("h1_3kind: " + h1_3kind+ "\n");
            winnerTA.append("h2_3kind: " + h2_3kind+ "\n");
            winnerTA.append("h1_pairs: " + h1_pairs+ "\n");
            winnerTA.append("h2_pairs: " + h2_pairs+ "\n");*/
            
            
            if(h1_3kind == true && h2_3kind == true){
                winnerTA.append("Draw");
            }
            else if(h1_3kind == true && h2_3kind != true){
                winnerTA.append("hand1 wins");
            }
            else if(h1_3kind != true && h2_3kind == true){
                winnerTA.append("hand2 wins");
            }
            else if(h1_pairs == true && h2_pairs == true){
                winnerTA.append("Draw");
            }
            else if(h1_pairs == true && h2_pairs != true){
                winnerTA.append("hand1 wins");
            }
            else if(h1_pairs != true && h2_pairs == true){
                winnerTA.append("hand2 wins");
            }
            else if(h1_2kind == true && h2_2kind == true){
                winnerTA.append("Draw");
            }
            else if(h1_2kind == true && h2_2kind != true){
                winnerTA.append("hand1 wins");
            }
            else if(h1_2kind != true && h2_2kind == true){
                winnerTA.append("hand2 wins");
            }
            else{
                winnerTA.append("Draw");
            }
            winnerTA.append("\n\n");
        }
    }
     private class value1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //int h1_count = 0;//int h2_count = 0;
            //h1_count = 
            getHand1(deck1, hand1);
            
            handMatchCount(deck1, hand1);
            int h1_arr[] = count_array;
            boolean h1_2kind = is2ofKind(h1_arr);
            boolean h1_3kind = is3ofKind(h1_arr);

            for (int i = 0; i < 13; i++) {
                if (h1_arr[i] == 3) {
                    matchH1TA.append("Three of A kind: three " + faces[i] + "'s\n");
                }
            }
            matchH1TA.append("\n");

            //matchH1TA.append("Two of A kind, hand1: ");
            for (int i = 0; i < 13; i++) {
                if (h1_arr[i] == 2) {
                    matchH1TA.append("Pair of " + faces[i] + "'s\n");
                }
            }
            if(h1_3kind == false && h1_2kind == false){
                matchH1TA.append("no pairs or 3 of a kind.");
            }
            matchH1TA.append("\n");
            matchH1TA.append("\n");
        }
     }
    private class value2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            getHand2(deck1, hand2);
            handMatchCount(deck1, hand2);
            int h2_arr[] = count_array;
            boolean h2_2kind = is2ofKind(h2_arr);
            boolean h2_3kind = is3ofKind(h2_arr);

            for (int i = 0; i < 13; i++) {
                if (h2_arr[i] == 3) {
                    matchH2TA.append("Three of A kind: three " + faces[i] + "'s\n");
                }
            }
            matchH2TA.append("\n");

            //matchH2TA.append("Two of A kind, hand2: ");
            for (int i = 0; i < 13; i++) {
                if (h2_arr[i] == 2) {  matchH2TA.append("Pair of " + faces[i] + "'s\n");}   
            }
            if(h2_3kind == false && h2_2kind == false){
                matchH2TA.append("no pairs or 3 of a kind.");
            }
            matchH2TA.append("\n");
            matchH2TA.append("\n");  
        }
     }
    
    boolean is2ofKind(int a[]) {
        boolean ret = false;
        //a = count_array;
        for (int i = 0; i < 13; i++) {
            if (a[i] == 2) { 
                ret = true;
            }   
        }
        return ret;
    }
    boolean is2ofKindPair(int a[]) {
        boolean ret = false;
        int count = 0;
        //a = count_array;
        for (int i = 0; i < 13; i++) {
            if (a[i] == 2) { 
                count++;
            }   
        }
        if(count ==2) ret = true;
        return ret;
    }
    
    boolean is3ofKind(int a[]) {
        boolean ret = false;
        //int h2 a[] = count_array;
        for (int i = 0; i < 13; i++) {
            if (a[i] == 3) { ret = true;}   
        }
        return ret;
    }


    void handMatchCount(CardDeck a, Card h[]){  
        int count = 0;
        //int count_array [];
        int ret [];
        Card h_copy[] = h;
        count_array =new int[13];   
        for (int i = 0; i < 13; i++) {
            count_array[i] = 0;
        }
        for (int j = 0; j < 5; j++) {
            for (int f = 0; f < 13; f++) {
                if (h_copy[j].getFace() == faces[f]) {
                    ++count_array[f];
                    count++;
                }
            }
        }
        //return count;
    }
    private class clearTextListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            outputTA.setText("");
            matchH1TA.setText("");
            matchH2TA.setText("");
            hand1TA.setText("");
            hand2TA.setText("");
            winnerTA.setText("");
        }
    
    }
    
    void buildPanel(){
        mainPanel = new JPanel();
        northPanel = new JPanel();
        north_label = new JLabel(" Poker Game! ");
        northPanel.add(north_label);
        
//east panel
 //deck panel for shuffled deck  
        westPanel  = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        deckPanel = new JPanel();
        shuffle_label = new JLabel("Shuffled results: ");
        shuffle_label.setForeground(Color.white);
        westPanel.add(shuffle_label);
        outputTA = new JTextArea(30,10);
        scrollDeck = new JScrollPane(outputTA);
        scrollDeck.setPreferredSize(new Dimension(200,520));
        deckPanel.add(scrollDeck); 
        westPanel.add(deckPanel);
        
        
// panel for hand 1   
        centerPanel  = new JPanel();
        //BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//hand1 
        hand1Panel = new JPanel();
        hand1_label = new JLabel("hand1 deal: ");
        centerPanel.add(hand1_label);
        hand1TA = new JTextArea(30,10);
        hand1Scroll = new JScrollPane(hand1TA);
        hand1Scroll.setPreferredSize(new Dimension(200,200));
        hand1Panel.add(hand1Scroll);
        
        centerPanel.add(hand1Panel);
 //results 2/3 kind of hand1       
        matchH1Panel = new JPanel();
        hand1_val_label = new JLabel("hand1 results: ");
        centerPanel.add(hand1_val_label);
        matchH1TA= new JTextArea(30,10);
        scrollH1Match = new JScrollPane(matchH1TA);
        scrollH1Match.setPreferredSize(new Dimension(200,200));
        matchH1Panel.add(scrollH1Match);
        centerPanel.add(matchH1Panel);
        
//eastPanel for hand 2 deal and results
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        hand2Panel = new JPanel();
        hand2_label = new JLabel("hand2 results: ");
        eastPanel.add(hand2_label);
        hand2TA = new JTextArea(30,10);
        hand2Scroll = new JScrollPane(hand2TA);
        hand2Scroll.setPreferredSize(new Dimension(200,200));
        hand2Panel.add(hand2Scroll);
        eastPanel.add(hand2Panel);
//panel for results of hand2
        matchH2Panel = new JPanel();
        hand2_val_label = new JLabel("hand2 results: ");
        eastPanel.add(hand2_val_label);
        matchH2TA= new JTextArea(30,10);
        scrollH2Match = new JScrollPane(matchH2TA);
        scrollH2Match.setPreferredSize(new Dimension(200,200));
        matchH2Panel.add(scrollH2Match);
        eastPanel.add(matchH2Panel);
        
//south panel
        southPanel = new JPanel();
        southInnerPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southInnerPanel.setLayout(new BoxLayout(southInnerPanel, BoxLayout.Y_AXIS));
        winnerPanel = new JPanel();
        winner_label = new JLabel("          The winner is: ");
        southInnerPanel.add(winner_label);
        winnerTA = new JTextArea(30,10);
        winnerScroll = new JScrollPane(winnerTA);
        winnerScroll.setPreferredSize(new Dimension(200,200));
        winnerPanel.add(winnerScroll);
        southInnerPanel.add(winnerPanel);
        southPanel.add(southInnerPanel);
        setColorGUI(); 
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(southPanel, BorderLayout.SOUTH); 
    }
    
    void setColorGUI(){
        //center panel
        centerPanel.setBackground(lightGray);
        //hand1Panel.setBackground(Color.blue);
       
        //east panel
        eastPanel.setBackground(Color.gray);
        hand2Panel.setBackground(Color.gray);
        matchH2Panel.setBackground(Color.gray);
        hand2TA.setBackground(Color.white);
        matchH2TA.setBackground(Color.white);
        hand2_label.setForeground(Color.white);
        //hand2_label.setBackground(Color.white);
        matchH2Panel.setForeground(Color.black);
        hand2_val_label.setForeground(Color.white);
        
        //west panel
        deckPanel.setBackground(Color.gray);
        westPanel.setBackground(Color.gray);
        outputTA.setBackground(Color.white);
        outputTA.setForeground(Color.black);
        //matchH2Panel.setBackground(Color.gray);
        
        //southPanel
        //Color darkishGray = new Color(0, 0, 0, 140);//Color(int r, int g, int b, int a)
        //southPanel.setBackground(lightGray);
        southInnerPanel.setBackground(lightGray);//leave
        winnerPanel.setBackground(lighterGray);
        winnerTA.setBackground(Color.white);
        //winnerTA.setBackground(medGray);
        //winnerTA.setForeground(Color.white);
        //winner_label.setForeground(Color.white);
        
        //mainPanel
        //mainPanel.setBackground(Color.white);
    }
    
      
    void getHand1(CardDeck a, Card h1[]){ //Card[]
        for (int i = 0; i < 5; i++) {
            h1[i]=  a.getCard(i);
      }
    }
    void getHand2(CardDeck a, Card h2[]){ //Card[]
        int count = 0;
        for (int i = 5; i < 10; i++) {
            h2[count]=  a.getCard(i);
            System.out.println("h2["+ count +"]: " + h2[count]);
            count++;
      }
    } 
}

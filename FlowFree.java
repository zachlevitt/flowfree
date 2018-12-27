// Flow Free Applet
//
// CS 201 Final Project
//
//Zach Levitt and Zack Peters, Section B

import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class FlowFree extends Applet implements ActionListener{

    private static final long serialVersionUID = 1L; // to avoid Eclipse warning

    // instance variables
    BoxCanvas c;
    Button restartLevel;
    Button nextLevel;
    Button restartGame;
    protected Label title;
    protected Label level;
    protected Label moves;
    protected Label blank;
    protected Label score;
    protected Label levelNum;
    protected Label credit;

    //Sets up the overall layout and format of the screen
    //Uses a border layout for panels
    public void init() {

        setFont(new Font("Helvetica", Font.BOLD, 32));
        setLayout(new BorderLayout());

        //Set up top panel with title, level number, and moves
        Panel top = new Panel();
        top.setLayout(new FlowLayout());
        
        //Sets up title and name credits
        Panel name = new Panel();
        name.setLayout(new BorderLayout());
        credit = new Label("By Zack Peters and Zach Levitt");
        credit.setFont(new Font("Helvetica", Font.BOLD, 12));
        credit.setAlignment(Label.CENTER);
        credit.setBackground(Color.white);
        title = new Label("FLOW FREE"); //initializes result as 0
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        title.setAlignment(Label.CENTER);
        title.setBackground(Color.white);
        blank = new Label("  ");
        name.add("North", title);
        name.add("South", credit);

        //Sets up level and moves labels
        Panel level = new Panel();
        level.setLayout(new BorderLayout());
        
        levelNum = new Label("Level: 1");
        levelNum.setAlignment(Label.CENTER);
        levelNum.setBackground(Color.white);
        levelNum.setFont(new Font("Helvetica", Font.BOLD, 20));

        level.add("North", levelNum);
        
        moves = new Label("Moves: 0");
        moves.setAlignment(Label.CENTER);
        moves.setBackground(Color.white);
        moves.setFont(new Font("Helvetica", Font.BOLD, 14));
        level.add("South", moves);

        //Adds name and level panels to top panel
        top.add("North", blank);
        top.add("West", name);
        top.add("Center", blank);
        top.add("East", level);

        //Initialize BoxCanvas and add Canvas to center of screen
        c = new BoxCanvas(this, 1); 
        c.setBackground(Color.white);
        c.addMouseMotionListener(c);
        add("Center", c);

        //Sets up bottom panel with buttons and overall score
        Panel bottom = new Panel();
        bottom.setLayout(new GridLayout());
        
        restartLevel = new Button("Restart Level");
        restartLevel.setBackground(Color.white);
        restartLevel.setFont(new Font("Helvetica", Font.BOLD, 14));
        restartLevel.addActionListener(this);

        //set up overall score board
        score = new Label("Overall Score: 0"); //initializes result as 0
        score.setAlignment(Label.CENTER);
        score.setBackground(Color.white);
        score.setFont(new Font("Helvetica", Font.BOLD, 14));

        //set up nextLevel button
        nextLevel = new Button("Next Level");
        nextLevel.setBackground(Color.white);
        nextLevel.setFont(new Font("Helvetica", Font.BOLD, 14));
        nextLevel.addActionListener(this);

        //set up restartGame button
        restartGame = new Button("Restart Game");
        restartGame.setBackground(Color.white);
        restartGame.setFont(new Font("Helvetica", Font.BOLD, 14));
        restartGame.addActionListener(this);

        //put together bottom panel
        Panel center = new Panel();
        center.setLayout(new BorderLayout());
        center.add("South", nextLevel);
        center.add("North", score);

        //put together bottom panel
        bottom.add(restartLevel);
        bottom.add(center);
        bottom.add(restartGame);

        //Set up the entire GUI
        add("North", top);
        add("South", bottom);
        add("Center", c);
    }
    
    //action events that handle button presses
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartLevel) {
            c.restartLevel();
        }

        else if (e.getSource() == nextLevel) {
            c.nextLevel();
        }

        else if (e.getSource() == restartGame) {
            c.restartGame();
        }
    }
}

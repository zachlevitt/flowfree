//BoxCanvas Class for Flow Free Applet
//Zach Levitt and Zack Peters

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//BoxCanvas implements all methods and variables needed for 
//showing and altering the Canvas
class BoxCanvas extends Canvas implements MouseMotionListener  {

    //instance variables
    FlowFree f;
    int size;
    int border;
    Game game;
    int n;
    Square[][] matrix;
    int scoreOverall;
    Square square;
  

    //constructor
    public BoxCanvas(FlowFree f, int level) {
        this.game = new Game(level, this);
        this.size = 400/game.n;
        this.border = 20;
        this.n = game.n;
        this.matrix = game.matrix;
        this.f = f;
        this.scoreOverall = 0;
    }

    // This method is called by Java when the window is changed or when "repaint()" is called.
    //Draws the game board with all of the squares. 
    public void paint(Graphics g) {
        int rad = 30;
        int x = border;
        int y = border;
        size = 400/n;

        for (int i = 0; i < n; i++) {
            y = i * size + border;
            for (int j = 0; j < n; j++) {
                x = j * size + border;
                int z = matrix[i][j].getColor();

                if (matrix[i][j].isClicked()) {
                    setRectColor(g, z); 
                }

                else {
                    g.setColor(Color.white);
                }

                g.fillRect(x, y, size, size);
                g.setColor(Color.black);
                g.drawRect(x, y, size, size);

                if (matrix[i][j].isDot()) {
                    setDotColor(g, z);
                    drawCircle(g, x, y, rad);
                }
            }
        }

        if (isOver()==true) { //Draw win screen if entire board is filled
            g.setColor(Color.white);
            g.fillRect(70, 70, 300, 225);
            g.setColor(Color.black);
            g.drawRect(70, 70, 300, 225);
            
            youWin(g, 110, 160, game.getMoves());
        }


    }


    //Restarts game at level 1 with a new game matrix
    public void restartGame() {
        game.level = 1;
        game.overallMoves = 0;
        f.score.setText("Overall Score: 0");
        restartLevel();
    }
    
    //Restarts the current level
    public void restartLevel() {
        if (game.level <= 9) {
            game.playing = false;
            game.moves = 0;
            matrix = game.createBoard();
            n = game.n;
            f.moves.setText("Moves: " + game.getMoves());
            f.levelNum.setText("Level: " + game.getLevel());           
            repaint();
        }
    }

    //Increases the level, then restarts it at that level
    //Also, displays user's overall score.
    //On final level, displays GAME OVER and FINAL SCORE
    public void nextLevel() {
        if (game.level <= 8) {
            game.level = game.level + 1;
            if (isOver()) {
                game.overallMoves = game.getOverallMoves() + game.getMoves();
            }
            f.score.setText("Overall Score: " + game.getOverallMoves());
            restartLevel();
        }
        else if (game.level == 9) { //FINAL LEVEL
            f.levelNum.setText("GAME");
            f.moves.setText("OVER");
            f.score.setText("FINAL SCORE: " + game.getOverallMoves());
            repaint();
        }
    }

    //When you beat a level, displays "You Win" as well 
    //as your moves out of the minimum # of moves to complete it
    public void youWin(Graphics g, int x, int y, int moves) {
        String youWin = "YOU WIN!";
        String finalMoves;
        String perfect = "Perfect!";
        
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 60));
        g.drawString(youWin, 77, 175);
        
        if (game.n==5) {
            finalMoves = "Moves: " + Integer.toString(moves) + "/25";
            g.setFont(new Font("Helvetica", Font.BOLD, 25));
            if (moves==25) {
                g.drawString(perfect, 175, 260); 
            } else {
                g.setColor(Color.red);
            }

        }
        else if (game.n==6) {
            finalMoves = "Moves: " + Integer.toString(moves) + "/36";
            g.setFont(new Font("Helvetica", Font.BOLD, 25));
            if (moves==36) {
                g.drawString(perfect, 175, 260);
            } 
            else {
                g.setColor(Color.red);
            }
        }

        else {
            finalMoves = "Moves: " + Integer.toString(moves) + "/49";
            g.setFont(new Font("Helvetica", Font.BOLD, 25));
            if (moves==49) {
                g.drawString(perfect, 175, 260);
            }
            else {
                g.setColor(Color.red);
            }

        }
        g.setFont(new Font("Helvetica", Font.BOLD, 40));
        g.drawString(finalMoves, 100, 225);
    }

    //Checks each square in matrix
    //Returns true if all are clicked
    //False otherwise. Indicates if level is completed
    public boolean isOver() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrix[i][j].isClicked()) {
                    return false;
                }
            }
        }
        return true;
    }

    //returns true if the current square is
    //adjacent to the previous square, false otherwise
    public boolean isAdjacent(int oldRow, int oldCol, int newRow, int newCol) {
        if (Math.abs(newRow-oldRow) > 1) {
            return false; 
        }else if (Math.abs(newCol - oldCol) > 1) {
            return false;
        }else if ((Math.abs(newCol - oldCol) > 0) && (Math.abs(newRow - oldRow) > 0)) {
            return false;
        }else {
            return true;
        }
    }

    //Sets background shading color based on integer parameter
    public void setRectColor(Graphics g, int colorInt) {
        if (colorInt == 0) {
            g.setColor(Color.white);
        }else if (colorInt == 1) {
            g.setColor(Color.orange);
        }else if (colorInt == 2) {
            Color lblue = new Color(200, 220, 255);
            g.setColor(lblue); //LIGHTER
        }else if (colorInt == 3) {
            g.setColor(Color.yellow);
        }else if (colorInt == 4) {
            g.setColor(Color.green);
        }else if (colorInt == 5) {
            g.setColor(Color.red); //LIGHTER
        }else if (colorInt == 6) {
            g.setColor(Color.gray);
        }else if (colorInt == 7) {
            Color purple = new Color(206, 103, 218);
            g.setColor(purple);
        }
    }

    //Sets dot color based on integer parameter
    public void setDotColor(Graphics g, int colorInt) {
        if (colorInt == 0) {
            g.setColor(Color.white);
        }else if (colorInt == 1) {
            Color dorange = Color.orange.darker();
            g.setColor(dorange);
        }else if (colorInt == 2) {
            g.setColor(Color.blue);
        }else if (colorInt == 3) {
            Color dyellow = Color.yellow.darker();
            g.setColor(dyellow);
        }else if (colorInt == 4) {
            Color dgreen = Color.green.darker();
            g.setColor(dgreen);
        }else if (colorInt == 5) {
            Color dred = Color.red.darker();
            g.setColor(dred);
        }else if (colorInt == 6) {
            Color dgray = Color.gray.darker();
            g.setColor(dgray);
        }else if (colorInt == 7) {
            Color dpurple = new Color(111, 32, 119);
            g.setColor(dpurple);
        }
    }

    //method that draws a dot, location depending on x and y, size on rad
    public void drawCircle(Graphics g, int x, int y, int rad) {
        if (n == 5) {
            g.fillOval(x+25, y+25, rad, rad);
        }else if (n==6) {
            g.fillOval(x+18, y+18, rad, rad);
        }else {
            g.fillOval(x+14, y+14, rad, rad);
        }
    }

    //Unclicks and resets the color of all squares
    //that have the same colorInt as the colorInt parameter
    public void clearClicked(int colorInt) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j].getColor()==colorInt) {
                    matrix[i][j].changeClicked(false);
                    if (!matrix[i][j].isDot()){
                        matrix[i][j].changeColor(0);
                    }
                }
            }
        }
    }

    // handles mouse events
    public void mouseDragged(MouseEvent event) {
        Point p = event.getPoint();

        int x = p.x - border;
        int y = p.y - border;

        if (x >= 0 && x < n*size &&
                y >= 0 && y < n*size) { //If click is within game board

            int column = x / size;
            int row = y / size; 
            square = matrix[row][column];

            if (!game.playing) {

                if (square.isClicked()) { //If already clicked
                    repaint();
                }
                else { //If not clicked
                    if (!square.isDot()) { //if no dot
                        repaint();
                    }

                    else { //if it is a dot
                        game.increaseMetrics();
                        game.playing = true;
                        game.firstDotColor = square.getColor();
                        square.changeClicked(true);
                        game.oldRow = row;
                        game.oldCol = column;
                        repaint();
                    }

                }
            }
            else if (game.playing) {  
                //If drag is in the same square
                if (x / size == game.oldCol && y / size == game.oldRow) {
                    repaint();
                }
                else {
                    if (isAdjacent(game.oldRow, game.oldCol, row, column)) {
                        //if the square is adjacent to the previous square
                        if (!square.isDot()){ //if its not a dot                  
                            if (square.isClicked()) { //is clicked
                                if(square.getColor() == 
                                   matrix[game.oldRow][game.oldCol].getColor()) {
                                    //if you backtrack
                                        game.increaseMetrics();
                                        matrix[game.oldRow][game.oldCol].changeClicked(false);
                                        repaint();
                                        game.oldRow = row;
                                        game.oldCol = column;
                                        repaint();
                                }
                                else { //if you run into another colors path
                                    clearClicked(square.getColor());
                                    square.changeColor(game.firstDotColor);
                                }

                            } 
                            else { //if it is not clicked
                                square.changeColor(game.firstDotColor);
                                game.increaseMetrics();
                                square.changeClicked(true);
                                game.oldRow = row;
                                game.oldCol = column;
                                repaint();
                            }
                        }
                        else { //if it is a dot
                          //if it is the same color as the first one
                            if (square.getColor()==game.firstDotColor){
                                    if (square.isClicked()==true) {
                                        repaint();
                                    }
                                    else { //if you complete a connection
                                        game.playing = false; 
                                        game.increaseMetrics();
                                        square.changeClicked(true);
                                        game.oldRow = row;
                                        game.oldCol = column;
                                        repaint();
                                    }
                            }
                            else {
                                repaint();
                            }
                        }
                    }


                }

            }
            else { //Not adjacent
                repaint();
            }
        }
        f.moves.setText("Moves: " + game.getMoves());
        f.levelNum.setText("Level: " + game.getLevel());


    }
    
    // need these also because we implement a MouseListener
    public void mouseReleased(MouseEvent event) { }
    public void mouseClicked(MouseEvent event) { }
    public void mouseEntered(MouseEvent event) { }
    public void mouseExited(MouseEvent event) { }
    public void mouseMoved(MouseEvent event) { }
}

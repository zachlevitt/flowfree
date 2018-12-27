//Square class for Flow Free Applet
//Zack Peters and Zach Levitt

class Square {

    //instance variables
    protected int x;
    protected int y;
    protected int colorInt;
    protected  boolean dot;
    protected boolean clicked;

    //constructors
    public Square(int x, int y, int colorInt, boolean dot, boolean clicked) {
        this.x = x;
        this.y = y;
        this.colorInt = colorInt;
        this.dot = dot;
        this.clicked = false;
    }


    //instance methods
    //Returns true if square has been clicked, false otherwise
    public boolean isClicked() {
        return clicked;
    }
    
    //Returns the X coordinate of square 
    public int getX() {
        return x;
    }

    //Returns the Y coordinate of square 
    public int getY() {
        return y;
    }

    //Returns the integer associated with each dot/square color
    public int getColor() {
        return colorInt;
    }

    //Returns true if square has a dot, false otherwise
    public boolean isDot(){
        return dot;
    }

    //Changes the current color integer to colorInt parameter
    public void changeColor(int colorInt) {
        this.colorInt = colorInt;
    }

    //Changes clicked based on clicked parameter
    public void changeClicked(boolean clicked) {
        this.clicked = clicked;
    }
}


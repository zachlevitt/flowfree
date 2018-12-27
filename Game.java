//Game class for Flow Free Applet
//by Zack Peters and Zach Levitt

class Game {

    //instance variables
    protected Square[][] matrix;
    protected int level = 1;
    int moves = 0;
    int overallMoves;
    protected boolean playing;
    protected int oldRow;
    protected int oldCol;
    protected int firstDotColor;
    protected int n;
    BoxCanvas c;


    //constructor
    public Game(int level, BoxCanvas c) {
        this.level = level;
        this.matrix = createBoard();
        this.playing = false;
        this.moves = 0;
        this.overallMoves = 0;
        this.c = c;
    }

    //instance methods
    //Returns the game's current level
    public int getLevel() {
        return level;
    }

    //increases the number of moves
    public void increaseMetrics() {
        moves = moves + 1;
    }

    //Returns the current number of moves 
    public int getMoves() {
        return moves;
    }

    //returns the overall number of moves for all levels
    public int getOverallMoves() {
        return overallMoves;
    }

    //Creates and returns a n by n array of Square objects
    //Includes hard-coded Flow Free boards from online game and iOs app
    public Square[][] createBoard() {
        int[][] level1 = {{1, 2, 0, 0, 4}, 
                {0, 1, 0, 0, 5}, 
                {0, 3, 0, 4, 0}, 
                {0, 2, 0, 5, 0}, 
                {0, 0, 0, 0, 3}};
        int[][] level2 = {{1, 0, 0, 0, 2}, 
                {3, 0, 0, 1, 0}, 
                {4, 5, 0, 3, 0},
                {0, 0, 5, 2, 0},
                {0, 0, 0, 0, 4}};
        int[][] level3 = {{1, 5, 0, 0, 0}, 
                {0, 3, 0, 0, 0}, 
                {0, 0, 2, 0, 0},
                {1, 0, 3, 0, 5},
                {2, 0, 4, 0, 4}};
        int[][] level4 = {{0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0}, 
                {0, 3, 5, 0, 0, 0}, 
                {0, 0, 0, 4, 0, 3}, 
                {0, 4, 0, 1, 5, 2},
                {0, 0, 0, 2, 0, 0}};
        int [][] level5 = {{0, 1, 2, 0, 0, 0},
                {0, 2, 0, 0, 3, 0}, 
                {0, 5, 0, 4, 0, 4}, 
                {0, 6, 5, 3, 0, 1}, 
                {0, 0, 0, 0, 6, 0},
                {0, 0, 0, 0, 0, 0}};
        int [][] level6 = {{1, 2, 0, 0, 2, 3},
                {0, 0, 0, 0, 5, 0}, 
                {5, 0, 0, 1, 0, 0}, 
                {0, 4, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0},
                {4, 3, 6, 0, 0, 6}};
        int [][] level7 = {{1, 0, 4, 0, 4, 2, 0},
                {3, 0, 7, 0, 0, 6, 0}, 
                {0, 0, 0, 0, 7, 0, 0}, 
                {0, 0, 0, 0, 6, 0, 2}, 
                {1, 5, 3, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        int [][] level8 = {{7, 0, 4, 0, 0, 2, 0},
                {3, 0, 0, 7, 0, 5, 0}, 
                {0, 0, 0, 3, 0, 0, 0}, 
                {0, 0, 0, 5, 4, 0, 0}, 
                {6, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0},
                {1, 2, 0, 0, 0, 0, 0}};
        int [][] level9 = {{1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 7, 0, 5, 0}, 
                {0, 6, 0, 0, 5, 0, 0}, 
                {0, 0, 7, 6, 0, 0, 3}, 
                {0, 0, 0, 0, 0, 4, 0},
                {0, 2, 0, 2, 4, 0, 3},
                {0, 0, 0, 0, 0, 0, 1}};

        int levelArray[][];
        boolean dot;
        boolean clicked = false;

        if (level == 1) {
            levelArray = level1;
            n=5;

        }else if (level == 2) {
            levelArray = level2;
            n=5;
        }else if (level == 3) {
            levelArray = level3;
            n=5;
        }else if (level == 4) {
            levelArray = level4;
            n=6;
        }else if (level == 5) {
            levelArray = level5;
            n=6;
        }else if (level == 6) {
            levelArray = level6;
            n=6;
        }else if (level == 7) {
            levelArray = level7;
            n=7;
        }else if (level == 8) {
            levelArray = level8;
            n=7;
        }else{
            levelArray = level9;
            n=7;
        }

        Square[][] matrixTemp = new Square[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (levelArray[i][j] == 0){
                    dot = false;
                }else {
                    dot = true;
                }
                matrixTemp[i][j] = new Square(j, i, levelArray[i][j], dot, clicked);
            }
        }
        return matrixTemp;
    }
}


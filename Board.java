import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
import java.awt.Font;
import java.util.Random;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.util.*;
public class Board {

    int currterri = 0;
    int blackterri, whiteterri;
    int Colorstate = 1;
    int count = 0;
    int currentX;
    int currentY;
    boolean play;
    ArrayList<GoString> black;
    ArrayList<GoString> white;
    Cell[][] cellArr;
    boolean[][] arr;
    boolean[][] visited;
    HashSet<String> colors;
    
    public Board(){
        this.blackterri = 0;
        this.whiteterri = 0;
        this.colors = new HashSet<String>();
        this.visited = new boolean[19][19];
        this.arr = new boolean[19][19];
        double x = 0;
        double y = 0;
        this.play = true;
        this.cellArr = new Cell[19][19];
        for(int j = 0; j < 19; j++){
            y = 0.1 + 0.02 * 2 * j;
            for(int i = 0; i < 19; i++){
                x = 0.1 + 0.02 * 2 * i;
                this.cellArr[i][j] = new Cell(true, false, x, y, i, j);
                this.cellArr[i][j].drawCell();
                //StdDraw.pause(1000);
            }
        }
        double m = cellArr[0][0].getXpos();
        double k = cellArr[9][0].getXpos();
        double side = k - m;
        double xcenter = cellArr[9][9].getXpos();
        double ycenter = cellArr[9][9].getYpos();
        StdDraw.setPenColor(253, 187, 6);
        StdDraw.setPenRadius(0.1);
        StdDraw.square(xcenter, ycenter, side);
        StdDraw.filledSquare(xcenter, ycenter, side);
        StdDraw.setPenColor(StdDraw.BLACK);
        
        StdDraw.setPenRadius(0.001);
        for(int i = 0; i < 19; i++){
           StdDraw.line(cellArr[i][0].getXpos(), cellArr[i][0].getYpos(), cellArr[i][18].getXpos(), cellArr[i][18].getYpos() );
        }
        for(int j = 0; j < 19; j++){
            StdDraw.line(cellArr[0][j].getXpos(), cellArr[0][j].getYpos(), cellArr[18][j].getXpos(), cellArr[18][j].getYpos() );
        }

        int[] arr = {3, 9, 15};
        for(int a : arr){
            for(int b: arr){
               cellArr[a][b].drawdot();
            }
        }

        this.black = new ArrayList<GoString>();
        this.white = new ArrayList<GoString>();

        StdDraw.filledCircle(0.46, 0.92, 0.04);


        StdDraw.show();


  
    

}

    // public boolean checkPlay(int x, int y, String color){
    //     if(this.checkEat(color)){
    //         return true;
    //     }
    //     else{
    //         if(checkStones(x, y, color)){
    //             return true;
    //         }
    //         return false;
    //     }
    // }

    public boolean checkStones(int x, int y, String color){
        if(x-1 >= 0 && !cellArr[x-1][y].getcolor().equals(color)){
            return true;
        }
        if(x+1 <= 18 && !cellArr[x+1][y].getcolor().equals(color)){
            return true;
        }
        if(y+1 <= 18 && !cellArr[x][y+1].getcolor().equals(color)){
            return true;
        }
        if(y-1 >= 0 && !cellArr[x][y-1].getcolor().equals(color)){
            return true;
        }
        return false;
    }
    
    

    public void turn(String color){
        if(color.equals("black")){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(0.46, 0.92, 0.04);
        }
        else{
            StdDraw.circle(0.46, 0.92, 0.04);
        }
    }

    public void drawOver(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(0.46, 0.92, 0.04);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void erase(int x, int y){
        cellArr[x][y].erase();
    }
    
    public int getcolorState(){
        return this.Colorstate;
    }

//     public double getElementXcord(int a, int b){
//         return cellArr[a][b].getXpos();
// }
//     public double getElementYcord(int a, int b){
//         return cellArr[a][b].getYpos();

    public void updatechi(){
        
    }

    public void showStone(){
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                currentX = i;
                currentY = j;
                double m = cellArr[currentX][currentY].getXpos();
                double n = cellArr[currentX][currentY].getYpos();
                double a = m - x;
                double b = n - y;
                if(Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)) <= 0.01 && StdDraw.isMousePressed() && cellArr[currentX][currentY].getState()){ //distance
                    StdDraw.setPenRadius(0.005);
                    if(Colorstate == 1){
                        this.checkPlay("black", currentX, currentY);
                    }
                    else if(Colorstate == 0){
                        this.checkPlay("white", currentX, currentY);
                    }
                    
                    
                    //show the stone
        }
    
                }
        
    
            }
        }

    public void checkPlay(String color, int i, int j){
        if(color.equals("white")){
            this.playWhite(i, j);
            // StdDraw.pause(1);
            this.groupStrings();
            if(!(checkEat("white") || checkStones(i, j, "black"))){
                cellArr[i][j].erase();
                this.groupStrings();
                this.Colorstate = 0;
                this.drawOver();
                this.turn("white");

            }
            else{
                this.eat(color);
                this.groupStrings();
            }
        }
        else{
            this.playBlack(i, j);
            // StdDraw.pause(1000);
            this.groupStrings();
            if(!(checkEat("black") || checkStones(i, j, "white"))){
                cellArr[i][j].erase();
                this.groupStrings();
                this.Colorstate = 1;
                this.drawOver();
                this.turn("black");
            }
            else{
                this.eat(color);
                this.groupStrings();
            }
        }
        StdDraw.show();
    }

    public void playWhite(int i, int j){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(cellArr[i][j].getXpos(), cellArr[i][j].getYpos(), 0.0168);
        StdDraw.setPenColor(StdDraw.WHITE);
        cellArr[currentX][currentY].play();
        cellArr[currentX][currentY].setcolor("white");
        this.Colorstate = 1;
        this.turn("black");
        this.count+=1;
    }

    public void playBlack(int i, int j){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(cellArr[i][j].getXpos(), cellArr[i][j].getYpos(), 0.0168);
        
        cellArr[currentX][currentY].play();
        cellArr[currentX][currentY].setcolor("black");
        this.drawOver();
        this.Colorstate = 0;
        this.turn("White");
        this.count +=1;
    }
       

    public void buttonmaker(double y, String s){
        double xcord = 1.2;
        double ycord = xcord - 0.2 * y;
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(xcord, ycord, 0.11, 0.03);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("ARIAL", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(xcord, ycord, s);
    }

    public boolean checkbuttonpressed(double y){
        double x = 1.2;
        double m = x - 0.2 * y;
        double xcord = StdDraw.mouseX();
        double ycord = StdDraw.mouseY();
        if(Math.abs(xcord - x) <= 0.11 && Math.abs(ycord - m) <= 0.03 && StdDraw.isMousePressed()){
            return true;
        }
        return false;
    }
    public boolean checklocation(double x, double y){
        double xcord = StdDraw.mouseX();
        double ycord = StdDraw.mouseY();
        if(Math.sqrt(Math.pow((xcord-x), 2) + Math.pow((ycord-y), 2)) <= 0.01 && StdDraw.isMousePressed()){
            return true;
        }
        return false;
    }

    public void points(){
        if(!this.play){
            for(Cell[] arr : cellArr){
                for(Cell c : arr){
                    if(checklocation(c.getXpos(), c.getYpos())){
                        StdDraw.text(c.getXpos(), c.getYpos(), "X");
                        StdDraw.show();
                    }
                }
            }
        }
    }

    public void searcher(int x, int y){
        if(!this.cellArr[x][y].getState()){
            this.colors.add(cellArr[x][y].getcolor());
            return;
        } 
        if(this.visited[x][y]){
            return;
        }
        this.visited[x][y] = true;

        this.currterri++;

        if(x + 1 <= 18){
            this.searcher(x+1, y);
        }
        if(x - 1 >= 0){
            this.searcher(x-1, y);
        }
        if(y + 1 <= 18){
            this.searcher(x, y+1);
        }
        if(y - 1 >= 0){
            this.searcher(x, y-1);
        }


        
    }

    public void countTerritory(){
        // this.blackterri = 0;
        // this.whiteterri = 0;
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(!visited[i][j]){
                    //reset the variable to be 0.
                    this.currterri = 0;
                    this.colors.clear();
                    System.out.println("start searching");
                    this.searcher(i, j);
                    if(this.colors.size() == 1){
                        if(this.colors.contains("black")){
                            this.blackterri += this.currterri;
                        }
                        else if(this.colors.contains("white")){
                            this.whiteterri += this.currterri;
                        }
                    }
                }
            }
        }

        System.out.println(this.blackterri);
        System.out.println(this.whiteterri);

    }

    public void showbuttons(){
        this.buttonmaker(2, "Show X");
        this.buttonmaker(4, "Count");
        this.buttonmaker(3, "reset");
    }

    public void boxCreator(double x, double y, double halfwidth, double halfheight){
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledRectangle(x, y, halfwidth, halfheight);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void ClearBox(double x, double y, double halfwidth, double halfheight){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(x, y, halfwidth, halfheight);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void again(){
        this.blackterri = 0;
        this.whiteterri = 0;
        for(int c = 0; c < 19; c++){
            for(int k = 0; k < 19; k++){
                visited[c][k] = false;
            }
        }
    }
    

    public void buttonchecker(){
        if(this.checkbuttonpressed(4)){
            this.again();
            this.boxCreator(1.25, 0.15, 0.2, 0.1);
            this.countTerritory();
            Font font = new Font("ARIAL", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(1.2, 0.2, "Black Territory: " + Integer.toString(blackterri));
            StdDraw.text(1.2, 0.1, "White Territory: " + Integer.toString(whiteterri));
            StdDraw.show();
            StdDraw.pause(2000);
            this.ClearBox(1.25, 0.15, 0.2, 0.1);
        }
        if(this.checkbuttonpressed(3)){
            StdDraw.clear();
            Board board = new Board();
        }
    }

    public void update(){
        this.buttonchecker();
        this.showStone();
        
    }

    public void groupStrings(){
        //Erase both arraylist
        this.black.clear();
        this.white.clear();
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                arr[i][j] = false;
            }
        }



        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                //check the existence of a stone in the position
                //if there is a stone and it isnt visited, start the recursive call. 
                //if there isnt a stone, continue. 
                if(!cellArr[i][j].getState() && !arr[i][j]){
                    if(cellArr[i][j].getcolor().equals("black")){
                        this.black.add(new GoString());
                        recursiveCall(i, j, black.get(black.size()-1), cellArr[i][j].getcolor());
                    }
                    else{
                        this.white.add(new GoString());
                        recursiveCall(i, j, white.get(white.size()-1), cellArr[i][j].getcolor());
                    }
                    // make a new gostring once u reached an end or smth(new search).
                }
            }
        }

        System.out.println("Black");
        for(GoString gs : this.black){
            gs.changeliberties(cellArr);
            gs.print();
            System.out.println(gs.getchi());
            System.out.println(" ");
        }
        System.out.println("White");
        for(GoString gs : this.white){
            gs.changeliberties(cellArr);
            gs.print();
            System.out.println(gs.getchi());
            System.out.println(" ");
        }
        
        System.out.println();


    

        // int xcord = 0;
        // int ycord = 0;
        // while(xcord < 19 && ycord < 19){
        //     Set <Cell> arr = new HashSet<Cell> ();
        //     arr.add(cellArr[Math.min(18, xcord+1)][Math.min(18, ycord+1)]);
        //     arr.add(cellArr[Math.max(0, xcord-1)][Math.min(18, ycord-1)]);
        //     arr.add(cellArr[Math.max(0, xcord-1)][Math.max(0, ycord-1)]);
        //     arr.add(cellArr[Math.min(18, xcord-1)][Math.max(0, ycord-1)]);
        // }


    }

    public void eat(String color){
        if(color.equals("black")){
            for(GoString gs : white){
                if(gs.getchi() == 0){
                    //return true
                    gs.removeall();
                }
            }
        }
        else{
            for(GoString gs : black){
                if(gs.getchi() == 0){
                    gs.removeall();
                }
            }
        }
    }

    public boolean checkEat(String color){
        if(color.equals("white")){
            for(GoString gs : black){
                if(gs.getchi() == 0){
                    System.out.println("true");
                    return true;
                }
            }
        }
        else{
            for(GoString gs : white){
                if(gs.getchi() == 0){
                    System.out.println("true");
                    return true;
                }
            }
        }
        System.out.println("false");
        return false;
    }


    public void recursiveCall(int x, int y, GoString gs, String color){
        if(this.cellArr[x][y].getState() || this.arr[x][y]){
            return; //if we reached to an end, then stop the method
        }
        else{
            gs.addcell(this.cellArr[x][y]);
            // gs.changeliberties(this.cellArr); // add a cell once u reached and checked
            this.arr[x][y] = true;
            if(x + 1 <= 18 && cellArr[x+1][y].getcolor().equals(color)){
                this.recursiveCall(x+1, y, gs, cellArr[x+1][y].getcolor());
            }
            if(x - 1 >= 0 && cellArr[x-1][y].getcolor().equals(color)){
                this.recursiveCall(x-1, y, gs, cellArr[x-1][y].getcolor());
            }
            if(y + 1 <= 18 && cellArr[x][y+1].getcolor().equals(color)){
                this.recursiveCall(x, y+1, gs, cellArr[x][y+1].getcolor());
            }
            if(y - 1 >= 0 && cellArr[x][y-1].getcolor().equals(color)){
                this.recursiveCall(x, y-1, gs, cellArr[x][y-1].getcolor());
            }
        }
    }

    
}

import java.lang.Math; 
public class Cell {

    boolean isEye;
    int blackchi;
    int whitechi;
    String color;
    int currentchi;
    int xpos;
    int ypos;
    boolean state;
    boolean clicked;
    double x, y;
    public Cell(boolean state, boolean clicked, double x, double y, int xpos, int ypos){
        this.state = state;
        this.x = x;
        this.y = y;
        this.xpos = xpos;
        this.ypos = ypos;
        this.color = "null";
        this.isEye = false;
    }

    public void increasechi(){
        this.currentchi++;
    }

    public void setcolor(String color){
        this.color = color;
    }

    public String getcolor(){
        return this.color;
    }

    public void updatechi(){
        if(this.color.equals("BLACK")){
            this.currentchi = blackchi;
        }
        else{
            this.currentchi = whitechi;
        }
    }

    public void capture(){
        this.erase();
    }


  

    public void erase(){
        StdDraw.setPenColor(253, 187, 6);
        StdDraw.filledCircle(x, y, 0.0185);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(Math.max(0.1, x-0.0185), y, Math.min(0.82, x+0.0185), y);
        StdDraw.line(x, Math.max(0.1, y-0.0185), x, Math.min(0.82, y+0.0185));
        int[] arr = {3, 9, 15};
        for(int i : arr){
            for(int j : arr){
                if(this.xpos == i && this.ypos == j){
                    this.drawdot();
                    break;
                }
            }
        }
        this.state = true;

    }

    public void drawdot(){
        StdDraw.setPenRadius(0.01);
        StdDraw.point(this.x, this.y);
    }

    public void drawCell(){
        StdDraw.point(x, y);
        StdDraw.show();
    }

    public double getXpos(){
        return x;
    }

    public double getYpos(){
        return y;
    }

    public int getX(){
        return this.xpos;
    }

    public int getY(){
        return this.ypos;
    }

    public void setState(boolean m){
        this.state = m;
    }

    public boolean getState(){
        return this.state;
    }

    public int getChi(){
        return currentchi;
    }

    //public void showNumber(){
       // String numberstate = Integer.toString(state);
       // Font font = new Font("Arial", Font.BOLD, 50);
        //StdDraw.setFont(font);
        //StdDraw.text(x, y-0.01, numberstate, 0);
        
    //}
    public void play(){
        if(this.state)
            StdDraw.filledCircle(x, y, 0.0168);
            this.state = false;
        
        // StdDraw.show();
        }
    }

    
        


    //its state of the board
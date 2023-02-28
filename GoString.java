import java.util.ArrayList;
import java.util.HashSet;

public class GoString {
    ArrayList<Cell> arr;
    HashSet<Cell> liberties;

    public GoString(){
        this.arr = new ArrayList<Cell>();
        this.liberties = new HashSet<Cell>();
    }

    public boolean isIn(Cell c){
        if(this.arr.contains(c)){
            return true;
        }
        return false;
    }

    public void removeall(){
        for(Cell c : arr){
            c.capture();
        }
    }

    public void addcell(Cell c){
        this.arr.add(c);
    }

    public int getchi(){
        return this.liberties.size();
    }

    public void changeliberties(Cell[][] a){
        //take two parameters: x index and y index
        //add the surroundings of the position (x-1, y), (x, y-1).. into the hashset
        //
        for(Cell c : this.arr){
            if(c.getX() + 1 < 19){
                if(a[c.getX()+1][c.getY()].getState()){
                    this.liberties.add(a[c.getX()+1][c.getY()]);
                }
            }
            if(c.getX() - 1 >= 0){
                if(a[c.getX()-1][c.getY()].getState()){
                    this.liberties.add(a[c.getX()-1][c.getY()]);
                }
            }
            if(c.getY() + 1 < 19){
                if(a[c.getX()][c.getY()+1].getState()){
                    this.liberties.add(a[c.getX()][c.getY()+1]);
                }
            }
            if(c.getY() - 1 >= 0){
                if(a[c.getX()][c.getY()-1].getState()){
                    this.liberties.add(a[c.getX()][c.getY()-1]);
                }
            }
            //add the surrounding to the hashset
        }
    }

    public void print(){
        for(Cell a : this.arr){
            System.out.print(a.getX() + " " + a.getY() + "|");
        }
    }
}

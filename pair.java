public class pair {
    int steps;
    Cell reached;

    public pair(int s, Cell r){
        steps = s;
        reached = r;
    }

    public int getSteps(){
        return this.steps;
    }

    public Cell getCell(){
        return this.reached;
    }



}

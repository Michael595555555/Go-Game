public class Main {
    public static void main(String args[]){
        StdDraw.enableDoubleBuffering();
        int xSize = 1200;
        int ySize = xSize * 2/3;
        StdDraw.setCanvasSize(xSize, ySize);
        StdDraw.setXscale(0, 1.5);
        StdDraw.setPenRadius(0.0001);


        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(0.5, 0.5, 0.5);
        StdDraw.setPenColor(StdDraw.BLACK);

        
        Board board = new Board();
        board.showbuttons();
        while(true){ 
            board.update();
            StdDraw.show();
            if(StdDraw.isKeyPressed(49)){
                board.countTerritory();
                StdDraw.pause(1000);
            }


        }

}
}
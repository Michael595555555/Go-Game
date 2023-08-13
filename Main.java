public class Main {

    public static void game(){

        

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(0.5, 0.5, 0.5);
        StdDraw.setPenColor(StdDraw.BLACK);

        Board board = new Board();
        board.showbuttons();

        StdDraw.show();

        StdDraw.pause(200);

        // board.selfplay(250);

        // board.printDistArray(board.distMatrix());

        // board.end();

        // board.endgame();


       
        testing(board, 0.001);





        // while(true){ 
        //     board.update();
        //     if(StdDraw.isKeyPressed(49)){
               
        //        board.theoretical(46,52 );
        //     }
        //     // StdDraw.show();
        //     // if(StdDraw.isKeyPressed(49)){
        //     //     board.countTerritory();
        //     //     StdDraw.pause(1000);
        //     // }

        //     StdDraw.show();

        //     if(board.checkbuttonpressed(3)){
        //         StdDraw.clear();
        //         board = null;
        //         game();
        //     }



        // }

    }
    public static double grad(Board b, double k){
        double l = -1*(86-k * b.sumBlack()) * b.sumBlack() - (127-k * b.sumWhite()) * b.sumWhite();

        

        return l;
    }

    public static void testing(Board board, double rate){
        board.selfplay(200);
        double k = 0.5;
        k -= rate * grad(board, k);

        board.theoretical(94, 99, 1);

        board.printDistArray(board.distMatrix());


        for(int i = 0; i < 50; i++){
            // board.theoretical(86, 127, k);
            // System.out.println(grad(board, k));
            k -= rate * grad(board, k);
            System.out.println(k);
            
        }
        
        

  

        

        

        

        




    }
    public static void main(String args[]){
        StdDraw.enableDoubleBuffering();
        int xSize = 1200;
        int ySize = xSize * 2/3;
        StdDraw.setCanvasSize(xSize, ySize);
        StdDraw.setXscale(0, 1.5);
        StdDraw.setPenRadius(0.0001);
        game();


        
    }
}
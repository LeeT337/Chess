import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    static final int SCREEN_WIDTH = 1000;
    BufferedImage b;
    static final int SCREEN_HEIGHT = 1000;

    Thread thread;


    boolean running = false;
    Timer timer;
    Random random;
    static Board board = new Board();
    int pieceSize = 60;

    static final int BOARD_HEIGHT = board.getBoardImage().getHeight(null);
    static final int BOARD_WIDTH = board.getBoardImage().getWidth(null);

    public static Piece selectedPiece = new Piece(null, null);
    public static int[] selectedPieceCords = new int[2];


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));



    }

    public void paint(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        Image im = board.getBoardImage();
        g.drawImage(im, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);


        for (int i = 0; i < board.getBoard().length; i++) {
            for (int k = 0; k < board.getBoard()[i].length; k++) {
                Piece curPiece = board.getBoard()[i][k];
                Image curImage = curPiece.getImage();
                g.drawImage(curImage, curPiece.xPos, curPiece.yPos, 100, 100, null);



            }
        }
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void init() {
        running = true;
    }


    public void run() {
        init();

        while (running) {

            update();
            repaint();




            try {
                Thread.sleep(1000/60);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }
    }



    private void update() {
        String winMessage = board.checkWin();
        if (winMessage == "") {
        } else {
            System.out.println("");
            System.out.print(winMessage);
            System.exit(0);
        }

    }


    public void mousePressed(int y, int x) {
        //System.out.println(y);
        //working
        int[] piecePos = board.getSquareFromCords(y, x);
        selectedPiece = board.getBoard()[piecePos[0]][piecePos[1]];


    }

    public void mouseReleased(int y, int x) {
        board.movePieceOnBoard(selectedPiece, y, x);

    }

    public void mouseDragged(int y, int x) {
        if (selectedPiece != null) {
            selectedPiece.xPos = x - (pieceSize);
            selectedPiece.yPos = y - (pieceSize);
        } else {

        }

    }



}

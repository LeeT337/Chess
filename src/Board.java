import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board {
    String currentTurnColor = "W";
    static ImageIcon b = new ImageIcon("Images/board.png");
    static final Image boardImage = b.getImage();
    static final int BOARD_HEIGHT = boardImage.getHeight(null);
    static final int BOARD_WIDTH = boardImage.getWidth(null);
    static Piece[][] board = {{new Piece("R1", "B"), new Piece("N1", "B"), new Piece("B1", "B"), new Piece("Q1", "B"), new Piece("K1", "B"), new Piece("B2", "B"), new Piece("N2", "B"), new Piece("R2", "B")},
            {new Piece("P1", "B"), new Piece("P2", "B"), new Piece("P3", "B"), new Piece("P4", "B"), new Piece("P5", "B"), new Piece("P6", "B"), new Piece("P7", "B"), new Piece("P8", "B")},
            {new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null)},
            {new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null)},
            {new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null)},
            {new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null), new Piece(null, null)},
            {new Piece("P1", "W"), new Piece("P2", "W"), new Piece("P3", "W"), new Piece("P4", "W"), new Piece("P5", "W"), new Piece("P6", "W"), new Piece("P7", "W"), new Piece("P8", "W")},
            {new Piece("R1", "W"), new Piece("N1", "W"), new Piece("B1", "W"), new Piece("Q1", "W"), new Piece("K1", "W"), new Piece("B2", "W"), new Piece("N2", "W"), new Piece("R2", "W")}};;
    static String turn = "W";

    Board() {


        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                Piece curPiece = board[i][k];
                curPiece.xPos = (boardImage.getWidth(null) / 8) * k;
                curPiece.yPos = (boardImage.getWidth(null) / 8) * i;

            }
        }
    }

    public Image getBoardImage() {
        return boardImage;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public int[][] getSquareCords(int y, int x) {
        ImageIcon b = new ImageIcon("Images/board.png");
        Image bo = b.getImage();


        int topX = (BOARD_WIDTH / 8) * x;
        int topY = (BOARD_HEIGHT / 8) * y;
        int bottomX = (BOARD_WIDTH / 8) * (x + 1);
        int bottomY = (BOARD_HEIGHT / 8) * (y + 1);
        int[][] cords = {{topX, topY}, {bottomX, bottomY}};


        return cords;
    }

    public int[] getSquareFromCords(int y, int x) {

        double arrayX = (double) x / (BOARD_WIDTH / 8);


        double arrayY = (double) y / (BOARD_WIDTH / 8);

        int xPos = (int) Math.floor(arrayX);
        int yPos = (int) Math.floor(arrayY);
        int[] sq = {yPos, xPos};
        return sq;
    }

    public int[] getPieceCords(Piece piece) {


        for (int i = 0; i < board.length; ++i) {
            for(int k = 0; k < board[i].length; ++k) {
                if (board[i][k] == piece) {

                    int[] c = {i, k};
                    return c;


                }

            }

        }

        return null;
    }




    public void movePieceOnBoard(Piece piece,  int moveY, int moveX) {
        int[] curPiecePos = getPieceCords(piece);
        int[] movePiecePos = getSquareFromCords(moveY, moveX);

        if (isMoveValid(piece, movePiecePos[0], movePiecePos[1])) {



            int[] pieceCords = getPieceCords(piece);
            board[movePiecePos[0]][movePiecePos[1]] = new Piece(piece.piece, piece.color);
            board[movePiecePos[0]][movePiecePos[1]].hasMoved = true;
            piece = null;
            board[pieceCords[0]][pieceCords[1]] = new Piece(null, null);
            board[movePiecePos[0]][movePiecePos[1]].xPos = (boardImage.getWidth(null) / 8) * movePiecePos[1];
            board[movePiecePos[0]][movePiecePos[1]].yPos = (boardImage.getWidth(null) / 8) * movePiecePos[0];
            if (currentTurnColor == "W") {
                currentTurnColor = "B";
            } else {
                currentTurnColor = "W";
            }
        } else {
            piece.xPos = (boardImage.getWidth(null) / 8) * curPiecePos[1];
            piece.yPos = (boardImage.getWidth(null) / 8) * curPiecePos[0];

        }
        System.out.println("");
        System.out.print("Current turn: ");
        System.out.print(currentTurnColor);



    }

    public boolean isMoveValid(Piece piece,  int moveY, int moveX) {
        boolean isValid = false;

        int[] curPiecePos = getPieceCords(piece);
        //Cant move to tile with same color
        boolean isSameColor = piece.color != board[moveY][moveX].color;
        ArrayList<int[]> moves = new ArrayList<int[]>();
        if (piece.piece == null) {

        } else if (piece.piece.charAt(0) == 'P') {
            moves = (piece.getPawnMoves(getPieceCords(piece), board, piece.color));
        } else if (piece.piece.charAt(0) == 'R') {
            moves = (piece.getRookMoves(getPieceCords(piece), board, piece.color));
        } else if (piece.piece.charAt(0) == 'B') {
            moves = (piece.getBishopMoves(getPieceCords(piece), board, piece.color));
        } else if (piece.piece.charAt(0) == 'N') {
            moves = (piece.getKnightMoves(getPieceCords(piece), board, piece.color));
        } else if (piece.piece.charAt(0) == 'K') {
            moves = (piece.getKingMoves(getPieceCords(piece), board, piece.color));
        } else if (piece.piece.charAt(0) == 'Q') {
            moves = (piece.getQueenMoves(getPieceCords(piece), board, piece.color));
        }



        for (int i = 0; i < moves.size(); ++i) {
            int y = moves.get(i)[0];
            int x = moves.get(i)[1];
            if ((y == moveY) && (x== moveX)) {
                isValid = true;
            }


        }

        boolean areMovingRightColor = (piece.color == currentTurnColor);

        isValid = isSameColor && isValid && areMovingRightColor;



        return isValid;
    }

    public void printBoard() {
        String toPrint;
        System.out.println("------------------");
        for (int i = 0; i < board.length; ++i) {

            for (int k = 0; k < board[i].length; ++k) {
                if (board[i][k].color != null) {
                    toPrint = board[i][k].color + board[i][k].color;
                } else {
                    toPrint = "NNN";
                }
                System.out.print(toPrint);
                System.out.print(" ");
            }
            System.out.println("");

        }
    }

    public String checkWin() {
        boolean blackKing = false;
        boolean whiteKing = false;
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                Piece curPiece = board[i][k];
                if (curPiece.piece == "K1") {
                    if (curPiece.color == "W") {
                        whiteKing = true;
                    } else {
                        blackKing = true;
                    }
                }

            }
        }

        String returnMessage = "";
        if (blackKing == false) {
            returnMessage += "White won";
        } else if (whiteKing == false) {
            returnMessage += "Black won";
        }

        return returnMessage;

    }

}

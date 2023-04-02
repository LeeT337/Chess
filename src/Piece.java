

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Piece {
    String X;
    public int xPos;
    public int yPos;
    int Y;
    String color;
    String piece;


    boolean hasMoved = false;


    Piece(String id, String pColor) {

        color = pColor;
        piece = id;


    }





    public Image getImage() { //turn into switch statement
        String name = "Images/";

        if (color != null && piece != null) {
            if (color == "B") {
                name += "B";
            } else if (color == "W") {
                name += "W";
            } else {
                name += null;
            }

            if (piece.charAt(0) == 'B') {
                name += "bishop.png";

            } else if (piece.charAt(0) == 'K') {
                name += "king.png";
            } else if (piece.charAt(0) == 'N') {
                name += "knight.png";
            } else if (piece.charAt(0) == 'P') {
                name += "pawn.png";
            } else if (piece.charAt(0) == 'Q') {
                name += "queen.png";
            } else if (piece.charAt(0) == 'R') {
                name += "rook.png";
            } else {
                name = null;
            }


            if (name != null) {
                ImageIcon pieceImageIcon = new ImageIcon(name);
                Image pieceImage = pieceImageIcon.getImage();
                return pieceImage;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }



    public ArrayList<int[]> getPawnMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();


        /* Array format
        0 1 2 3 4 5 6 7
        1
        2
        3
        4
        5
        6
        7
         */
        int curY = curPos[0];
        int curX = curPos[1];

        if (curY > 0) {
            if (color == "W") {

                if (board[curY - 1][curX].piece == null) {
                    moves.add(new int[]{(curY - 1), curX});

                    if ((hasMoved == false) && (board[curY - 2][curX].piece == null)) {
                        moves.add(new int[]{(curY - 2), curX});

                    }
                }
                if (curX > 0) {
                    if (board[curY - 1][curX - 1].color == "B") {
                        moves.add(new int[]{(curY - 1), (curX - 1)});
                    }
                }

                if (curX < 7) {
                    if (board[curY - 1][curX + 1].color == "B") {
                        moves.add(new int[]{(curY - 1), (curX + 1)});
                    }
                }


            }
        }
        if (curY < 7) {
            if (color == "B") {
                if (board[curY + 1][curX].piece == null) {
                    moves.add(new int[]{(curY + 1), curX});

                    if ((hasMoved == false) && (board[curY + 2][curX].piece == null)) {
                        moves.add(new int[]{(curY + 2), curX});

                    }
                }
                if (curX > 0) {
                    if (board[curY + 1][curX - 1].color == "W") {
                        moves.add(new int[]{(curY + 1), (curX - 1)});
                    }
                }

                if (curX < 7) {
                    if (board[curY + 1][curX + 1].color == "W") {
                        moves.add(new int[]{(curY + 1), (curX + 1)});
                    }
                }
            }
        }


        return moves;


    }





    public ArrayList<int[]> getRookMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int curY = curPos[0];
        int curX = curPos[1];

        //up
        for (int i = curY - 1; i >= 0; --i) {
            int[] move = rookMovesCheck(curPos, board, i, curX);
            if (move.length == 2) {
                moves.add(move);
            } else if (move.length == 1) {
                break;
            } else if (move.length == 3) {
                moves.add(new int[] {move[0], move[1]});
                break;
            }


        }
        //down
        for (int i = curY + 1; i < 8; ++i) {
            int[] move = rookMovesCheck(curPos, board, i, curX);
            if (move.length == 2) {
                moves.add(move);
            } else if (move.length == 1) {
                break;
            } else if (move.length == 3) {
                moves.add(new int[] {move[0], move[1]});
                break;
            }
        }

        //left
        for (int i = curX - 1; i >= 0; --i) {
            int[] move = rookMovesCheck(curPos, board, curY, i);
            if (move.length == 2) {
                moves.add(move);
            } else if (move.length == 1) {
                break;
            } else if (move.length == 3) {
                moves.add(new int[] {move[0], move[1]});
                break;
            }
        }

        //right
        for (int i = curX + 1; i < 8; ++i) {
            int[] move = rookMovesCheck(curPos, board, curY, i);
            if (move.length == 2) {
                moves.add(move);
            } else if (move.length == 1) {
                break;
            } else if (move.length == 3) {
                moves.add(new int[] {move[0], move[1]});
                break;
            }
        }

        return moves;
    }

    public int[] rookMovesCheck(int[] curPos, Piece[][] board, int yToCheck, int xToCheck) {


        int curY = curPos[0];
        int curX = curPos[1];

        if ((board[yToCheck][xToCheck].color == board[curY][curX].color) && (board[yToCheck][xToCheck].color != null)) {

            return (new int[]{0});
        } else if (board[yToCheck][xToCheck].color != board[curY][curX].color && (board[yToCheck][xToCheck].color != null)) {

            return (new int[]{yToCheck, xToCheck, 0});
        } else {

            return (new int[]{yToCheck, xToCheck});
        }


    }

    public ArrayList<int[]> getBishopMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int curY = curPos[0];
        int curX = curPos[1];
        int tempX = curX;



        //top right
        for (int i = curY - 1; i >= 0; --i) {
            tempX += 1;
            if (tempX > 7) {

                break;
            }

            int[] m = bishopMoveCheck(board, curY, curX, i, tempX);
            if (m == null) {
                break;
            } else if (m.length >= 3) {
                moves.add(new int[]{m[0], m[1]});
                break;
            } else {
                moves.add(m);
            }
        }


        //bottom right
        tempX = curX;
        for (int i = curY + 1; i < 8; ++i) {
            tempX += 1;
            if (tempX > 7) {
                break;
            }


            int[] m = bishopMoveCheck(board, curY, curX, i, tempX);
            if (m == null) {
                break;
            } else if (m.length >= 3) {
                moves.add(new int[]{m[0], m[1]});
                break;
            } else {
                moves.add(m);
            }

        }


        //top left
        tempX = curX;
        for (int i = curY - 1; i >= 0; --i) {
            tempX -= 1;
            if (tempX < 0) {
                break;
            }


            int[] m = bishopMoveCheck(board, curY, curX, i, tempX);
            if (m == null) {
                break;
            } else if (m.length >= 3) {
                moves.add(new int[]{m[0], m[1]});
                break;
            } else {
                moves.add(m);
            }

        }

        //bottom left
        tempX = curX;
        for (int i = curY + 1; i <= 7; ++i) {
            tempX -= 1;
            if (tempX < 0) {
                break;
            }


            int[] m = bishopMoveCheck(board, curY, curX, i, tempX);
            if (m == null) {
                break;
            } else if (m.length >= 3) {
                moves.add(new int[]{m[0], m[1]});
                break;
            } else {
                moves.add(m);
            }

        }


        return moves;
    }

    public int[] bishopMoveCheck(Piece[][] board, int curY, int curX, int i, int tempX) {

        if ((board[i][tempX].color == board[curY][curX].color) && (board[i][tempX].color != null)) {
            return null;
        } else {
            if ((board[i][tempX].color != board[curY][curX].color) && (board[i][tempX].color != null)) {
                return (new int[]{i, tempX, -1});
            } else {
                return (new int[]{i, tempX});
            }
        }


    }

    public ArrayList<int[]> getKnightMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int curY = curPos[0];
        int curX = curPos[1];

        int y1 = curY - 2;
        int y2 = curY + 2;
        int x1And21 = curX + 1;
        int x1And22 = curX - 1;

        int x1 = curX + 2;
        int x2 = curX - 2;
        int y1And21 = curY + 1;
        int y1And22 = curY - 1;

        ArrayList<int[]> cordPairs = new ArrayList<int[]>();
        cordPairs.add(new int[]{y1, x1And21});
        cordPairs.add(new int[]{y1, x1And22});
        cordPairs.add(new int[]{y2, x1And21});
        cordPairs.add(new int[]{y2, x1And22});
        cordPairs.add(new int[]{y1And21, x1});
        cordPairs.add(new int[]{y1And22, x1});
        cordPairs.add(new int[]{y1And21, x2});
        cordPairs.add(new int[]{y1And22, x2});

        for (int i = 0; i < cordPairs.size(); i++) {
            try {
                if (board[curY][curX].color != board[cordPairs.get(i)[0]][cordPairs.get(i)[1]].color) {
                    moves.add(cordPairs.get(i));
                }
            } catch (Exception e) {}
        }



        return moves;
    }

    public ArrayList<int[]> getKingMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int curY = curPos[0];
        int curX = curPos[1];

        ArrayList<int[]> cordPairs = new ArrayList<int[]>();
        cordPairs.add(new int[]{(curY + 1), (curX)});
        cordPairs.add(new int[]{(curY + 1), (curX - 1)});
        cordPairs.add(new int[]{(curY + 1), (curX + 1)});
        cordPairs.add(new int[]{(curY), (curX + 1)});
        cordPairs.add(new int[]{(curY), (curX - 1)});
        cordPairs.add(new int[]{(curY - 1), (curX)});
        cordPairs.add(new int[]{(curY - 1), (curX - 1)});
        cordPairs.add(new int[]{(curY - 1), (curX + 1)});

        for (int i = 0; i < cordPairs.size(); ++i) {
            try {
                if (board[curY][curX].color != board[cordPairs.get(i)[0]][cordPairs.get(i)[1]].color) {
                    moves.add(cordPairs.get(i));
                }
            } catch (Exception e) {}
        }

        return moves;

    }

    public ArrayList<int[]> getQueenMoves(int[] curPos, Piece[][] board, String color) {
        ArrayList<int[]> moves = new ArrayList<int[]>();



        ArrayList<int[]> rookMoves = getRookMoves(curPos, board, color);
        ArrayList<int[]> bishopMoves = getBishopMoves(curPos, board, color);

        for (int i = 0; i < rookMoves.size(); ++i) {
            moves.add(rookMoves.get(i));
        }
        for (int i = 0; i < bishopMoves.size(); ++i) {
            moves.add(bishopMoves.get(i));
        }
        return moves;
    }

}

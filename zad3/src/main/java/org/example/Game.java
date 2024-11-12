package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    static final int SIZE = 5;
    static final char EMPTY = '.';
    static final char PLAYER = 'A';
    static final char GOAL = 'B';
    static final char OBSTACLE = 'X';

    static char[][] board = new char[SIZE][SIZE];
    static int playerRow = 0;
    static int playerCol = 0;

    public static void main(String[] args) {
        initializeBoard();
        placeObstacles();
        placeGoal();
        gameLoop();
    }

    public static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        board[playerRow][playerCol] = PLAYER;
    }

    public static void placeObstacles() {
        Random random = new Random();
        int obstacles = SIZE;

        while (obstacles > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (board[row][col] == EMPTY && !(row == 0 && col == 0)) {
                board[row][col] = OBSTACLE;
                obstacles--;
            }
        }
    }

    public static void placeGoal() {
        board[SIZE - 1][SIZE - 1] = GOAL;
    }

    public static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("Move (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            int newRow = playerRow;
            int newCol = playerCol;

            switch (move) {
                case 'W':
                    newRow--;
                    break;
                case 'S':
                    newRow++;
                    break;
                case 'A':
                    newCol--;
                    break;
                case 'D':
                    newCol++;
                    break;
                default:
                    System.out.println("Invalid move! Use W, A, S, or D.");
                    continue;
            }

            if (isValidMove(newRow, newCol)) {
                board[playerRow][playerCol] = EMPTY;
                playerRow = newRow;
                playerCol = newCol;
                board[playerRow][playerCol] = PLAYER;

                if (playerRow == SIZE - 1 && playerCol == SIZE - 1) {
                    printBoard();
                    System.out.println("Congratulations! You've reached the goal!");
                    break;
                }
            } else {
                System.out.println("Invalid move! You can't go there.");
            }
        }

        scanner.close();
    }

    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] != OBSTACLE;
    }
}


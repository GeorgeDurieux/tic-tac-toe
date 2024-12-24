package gr.georgedurieux;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static final int[][] WINNING_CONDITIONS = {
            {0, 0, 0, 1, 0, 2},
            {1, 0, 1, 1, 1, 2},
            {2, 0, 2, 1, 2, 2},
            {0, 0, 1, 0, 2, 0},
            {0, 1, 1, 1, 2, 1},
            {0, 2, 1, 2, 2, 2},
            {0, 0, 1, 1, 2, 2},
            {0, 2, 1, 1, 2, 0}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            char[][] board = new char[][]{
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };

            chooseGameMode(board, scanner);

            System.out.println("Do you want to play again? (yes/no)");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }
        scanner.close();
    }

    private static void chooseGameMode(char[][] board, Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("Press 1 to play vs AI, or 2 to play vs another player.");
            choice = scanner.nextLine();

            if (Objects.equals(choice, "1")) {
                chooseAIMode(board, scanner);
                break;
            } else if (Objects.equals(choice, "2")) {
                playPvP(board, scanner);
                break;
            } else {
                System.out.println("Choose 1 or 2.");
            }
        }
    }

    private static void chooseAIMode(char[][] board, Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("Choose 1 for easy mode and 2 for hard mode.");
            choice = scanner.nextLine();

            if (Objects.equals(choice, "1")) {
                playSinglePlayerEasy(board, scanner);
                break;
            } else if (Objects.equals(choice, "2")) {
                playSinglePlayerHard(board, scanner);
                break;
            } else {
                System.out.println("Choose 1 or 2.");
            }
        }
    }

    private static void printBoard(char[][] board) {
        System.out.printf("%c|%c|%c%n", board[0][0], board[0][1], board[0][2]);
        System.out.println("-+-+-");
        System.out.printf("%c|%c|%c%n", board[1][0], board[1][1], board[1][2]);
        System.out.println("-+-+-");
        System.out.printf("%c|%c|%c%n", board[2][0], board[2][1], board[2][2]);
    }

    private static void playSinglePlayerEasy(char[][] board, Scanner scanner) {
        printBoard(board);
        while (true) {
            player1Turn(board, scanner);
            if (isGameFinished(board)) {
                break;
            }
            computerTurnEasyMode(board);
            if (isGameFinished(board)) {
                break;
            }
        }
    }

    private static void playSinglePlayerHard(char[][] board, Scanner scanner) {
        printBoard(board);
        while (true) {
            player1Turn(board, scanner);
            if (isGameFinished(board)) {
                break;
            }
            computerTurnHardMode(board);
            if (isGameFinished(board)) {
                break;
            }
        }
    }

    private static void playPvP(char[][] board, Scanner scanner) {
        printBoard(board);
        while (true) {
            player1Turn(board, scanner);
            if (isGameFinished(board)) {
                break;
            }
            player2Turn(board, scanner);
            if (isGameFinished(board)) {
                break;
            }
        }
    }

    private static void player1Turn(char[][] board, Scanner scanner) {
        playerTurn(board, scanner, 'X');
    }

    private static void player2Turn(char[][] board, Scanner scanner) {
        playerTurn(board, scanner, 'o');
    }

    private static void playerTurn(char[][] board, Scanner scanner, char symbol) {
        String cell;
        while (true) {
            System.out.printf("Player '%c', choose a cell to play (1-9 based on numpad layout):%n", symbol);
            cell = scanner.nextLine();

            if (cell.matches("[1-9]") && isLegalMove(board, cell)) {
                break;
            } else {
                System.out.println(cell + " is not a legal move. Try again.");
            }
        }
        play(board, cell, symbol);
        printBoard(board);
    }

    private static void computerTurnEasyMode(char[][] board) {
        Random random = new Random();
        String cell;

        do {
            int cellNum = random.nextInt(9) + 1;
            cell = Integer.toString(cellNum);
        } while (!isLegalMove(board, cell));

        System.out.println("Computer chose " + cell);
        play(board, cell, 'O');
        printBoard(board);
    }

    private static void computerTurnHardMode(char[][] board) {
        System.out.println("Computer is thinking...");
        for (int i = 1; i <= 9; i++) {
            String cell = Integer.toString(i);
            if (isLegalMove(board, cell)) {
                play(board, cell, 'O');
                if (hasContestantWon(board, 'O')) {
                    System.out.println("Computer chose " + cell);
                    printBoard(board);
                    return;
                }
                undoMove(board, cell);
            }
        }
        for (int i = 1; i <= 9; i++) {
            String cell = Integer.toString(i);
            if (isLegalMove(board, cell)) {
                play(board, cell, 'X');
                if (hasContestantWon(board, 'X')) {
                    play(board, cell, 'O');
                    System.out.println("Computer chose " + cell);
                    printBoard(board);
                    return;
                }
                undoMove(board, cell);
            }
        }
        computerTurnEasyMode(board);
    }

    private static void undoMove(char[][] board, String cell) {
        int[] indices = mapCellToIndices(cell);
        if (indices != null) {
            board[indices[0]][indices[1]] = ' ';
        }
    }

    private static void play(char[][] board, String cell, char symbol) {
        int[] indices = mapCellToIndices(cell);
        if (indices != null) {
            board[indices[0]][indices[1]] = symbol;
        }
    }

    private static boolean isLegalMove(char[][] board, String cell) {
        int[] indices = mapCellToIndices(cell);
        return indices != null && board[indices[0]][indices[1]] == ' ';
    }

    private static int[] mapCellToIndices(String cell) {
        return switch (cell) {
            case "7" -> new int[]{0, 0};
            case "8" -> new int[]{0, 1};
            case "9" -> new int[]{0, 2};
            case "4" -> new int[]{1, 0};
            case "5" -> new int[]{1, 1};
            case "6" -> new int[]{1, 2};
            case "1" -> new int[]{2, 0};
            case "2" -> new int[]{2, 1};
            case "3" -> new int[]{2, 2};
            default -> null;
        };
    }

    private static boolean isGameFinished(char[][] board) {
        if (hasContestantWon(board, 'X')) {
            printBoard(board);
            System.out.println("Player 'X' wins!");
            return true;
        }

        if (hasContestantWon(board, 'o')) {
            printBoard(board);
            System.out.println("Player 'o' wins!");
            return true;
        }

        if (hasContestantWon(board, 'O')) {
            printBoard(board);
            System.out.println("Computer wins!");
            return true;
        }

        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }

        printBoard(board);
        System.out.println("The game ended in a tie!");
        return true;
    }

    private static boolean hasContestantWon(char[][] board, char symbol) {
        for (int[] condition : WINNING_CONDITIONS) {
            if (board[condition[0]][condition[1]] == symbol &&
                    board[condition[2]][condition[3]] == symbol &&
                    board[condition[4]][condition[5]] == symbol) {
                return true;
            }
        }
        return false;
    }
}


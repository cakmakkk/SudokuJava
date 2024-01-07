import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Sudoku  {
    List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final int SIZE = 9;
    private final int[][] board;
    private final int[][] board1;
    String zorlukTutucu;

    public Sudoku(String zorlukTutucu) {
        this.zorlukTutucu=zorlukTutucu;
        board = new int[SIZE][SIZE];
        board1 = new int[SIZE][SIZE];
        sudokuOlustur();
        copy();
        bosBirak();
    }
    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean kontrolSudoku() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    Collections.shuffle(nums);
                    for (int i = 0; i < SIZE; i++) {
                        int num = nums.get(i);
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (kontrolSudoku()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void sudokuOlustur() {
        kontrolSudoku();
    }

    private void sudokuOlusturBos() {
        kontrolSudoku();
    }

    private void copy() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {

                board1[x][y] = board[x][y];

            }
        }

    }

    private void bosBirak() {
        Random random = new Random();
        int bosAdet;
        switch (zorlukTutucu){

            case "kolay":
                bosAdet = 1;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
            case "orta":
                bosAdet = 20;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
            case "zor":
                bosAdet = 30;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
        }
    }
    public int[][] getBoard1() {
        return board1;
    }
    public int[][] getBoard() {
        return board;
    }
    public String kutuya(int x , int y ){
        int a = 0;
        String sayi = null;
        a= board1[x][y];
        if(board1[x][y]==0){
            sayi=" ";
        }
        else {
            sayi=String.valueOf(a);}

        return sayi;
    }


    public void tahtaciz() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
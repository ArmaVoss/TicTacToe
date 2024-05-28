import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static boolean mainLoop = true;
    static Scanner console = new Scanner(System.in);
    static int currentPlayer;

    static char[] arr =  {
        ' ', '|', ' ', '|', ' ',
        '-', '-', '-', '-', '-',
        ' ', '|', ' ', '|', ' ',
        '-', '-', '-', '-', '-',
        ' ', '|', ' ', '|', ' ',
    };

    //checks winning conditions
    public static boolean checkWin(char[] arrGame) {
        int[][] winConditions = {
            {0, 2, 4}, {10, 12, 14}, {20, 22, 24},
            {0, 10, 20}, {2, 12, 22}, {4, 14, 24}, 
            {0, 12, 24}, {4, 12, 20} 
        };

        for (int[] condition : winConditions) {
            if (arrGame[condition[0]] != ' ' && 
                arrGame[condition[0]] == arrGame[condition[1]] && 
                arrGame[condition[1]] == arrGame[condition[2]]) {
                return true;
            }
        }
        return false;
    }

    //checks if row/column is valid input
    public static boolean checkValid(int num){
        if(num < 1 || num > 3){
            System.out.println("Please enter valid row/column");
            return false; 
        }
        return true;
    }

    //converts row and column input into valid array index
    public static int convertRowColumn(int row, int column){
        if(row == 1 && column == 1){
            return 0;
        }
        else if(row == 2 && column == 1){
            return 10;
        }
        else if(row == 3 && column == 1){
            return 20;
        }
        else if(row == 1 && column == 2){
            return 2;
        }
        else if(row == 2 && column == 2){
            return 12;
        }
        else if(row == 3 && column == 2){
            return 22;
        }
        else if(row == 1 && column == 3){
            return 4;
        }
        else if(row == 2 && column == 3){
            return 14;
        }
        else{
            return 24; 
        }
    }

    //updates board with input
    public static void updateBoard(int row, int column, char[] arrGame){
        int num = convertRowColumn(row, column);
        if(currentPlayer == 1){
            arrGame[num] = 'x';
        }
        else{
            arrGame[num] = 'o';
        }
    }

    //checks if something is already in position in board
    public static boolean isValidPlacement(int row, int column, char[] arrGame){
        int num = convertRowColumn(row, column);
        if(arrGame[num] != ' '){
            System.out.println("Can't place there, location already taken");
            return false;
        }
        return true;
    }

    //takes in inputs from player and checks if they are valid
    public static boolean gameInputs(char[] arrGame){
        System.out.print("Enter Row: ");
        int row = console.nextInt();
        if(checkValid(row) == false){
            return false;
        }
        System.out.print("Enter Column: ");

        int column = console.nextInt();  
        if(checkValid(column) == false){
            return false;
        }
        if(isValidPlacement(row, column, arrGame)){
            updateBoard(row, column, arrGame);
        }
        else{
            return false;
        }
        return true;                   
    }

    //prints original board
    public static void PrintBoard(){
        int count = 0;
        for(int i = 0; i<25; i++){
            if(count <5){
                System.out.print(arr[i]);
                count++;
            }
            else if(count == 5){
                count = 0;
                System.out.println();
                System.out.print(arr[i]);
                count++;
            }
        }
    }

    //prints board during game
    public static void PrintBoardGame(char[] arrGame){
        int count = 0;
        for(int i = 0; i<25; i++){
            if(count <5){
                System.out.print(arrGame[i]);
                count++;
            }
            else if(count == 5){
                count = 0;
                System.out.println();
                System.out.print(arrGame[i]);
                count++;
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        //main loop
        while(mainLoop == true){
            System.out.println("Hi Welcome to Tic Tac Toe");
            System.out.println("1. Play (1)");
            System.out.println("2. Instructions (2)");
            System.out.println("3. Exit (3)");
            int choice = console.nextInt();
            if(choice == 1){
                boolean gameNotOver = true;
                int count = 0;
                int elements = 0;
                char[] arrayGame = Arrays.copyOf(arr, arr.length);

                //game loop
                while(gameNotOver){
                    //if count has remainder 0, its player 1 turn
                    if(count % 2 == 0){
                        currentPlayer = 1;
                        System.out.println("Player 1");
                        if(!gameInputs(arrayGame)){
                            continue;
                        }
                        else{
                            elements++;
                        }
                        PrintBoardGame(arrayGame);
                        if(checkWin(arrayGame)){
                            System.out.println("Player 1 Wins!");
                            gameNotOver = false; 
                        }
             
                        if(elements == 9){
                            System.out.println("Game Over No Winner");
                            gameNotOver = false; 
                        }
                        count++;
                    }
                    else{
                        System.out.println("Player 2");
                        currentPlayer = 2;
                        if(!gameInputs(arrayGame)){
                            continue;
                        }
                        else{
                            elements++;
                        }
                        PrintBoardGame(arrayGame);
                        if(checkWin(arrayGame)){
                            System.out.println("Player 2 Wins!");
                            gameNotOver = false; 
                        }
                        if(elements == 9){
                            System.out.println("Game Over No Winner");
                            gameNotOver = false; 
                        }
                        count++;
                    }
                }

                //asks user if they want to play game again
                boolean isOver = true;
                System.out.print("Do you want to play again (y/n): ");
                while(isOver){
                    char playAgain = console.next().charAt(0);
                    if(playAgain == 'y'){
                        isOver = false;
                      
                    }
                    else if(playAgain == 'n'){
                        isOver = false;
                        mainLoop = false;
                    }
                    else{
                        System.out.println("Enter valid character (y/n)");
                    }
                }
       
            }
            //tells player instructions on how to play
            else if(choice == 2){
                System.out.println();
                System.out.println("1st input is row, 2nd is column");
                System.out.println("3 rows, 3 columns (1,2,3)");
                PrintBoard();
                System.out.println();
                System.out.println();
            }
            //exits game
            else if(choice == 3){
                mainLoop = false;
            }
        }
        System.out.println("Thanks for playing");
    }
}
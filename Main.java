// import java.util.ArrayList;
// import java.util.Scanner;
// import java.lang.Thread;  

// class Main {
//     public static void main(String args[]){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("---INITIAL SETTINGS---");
//         System.out.println("rows (number of rows to place cards)\nsets (number of card sets to solve)\nInput cardCol (number of columns that the card will be initialized)\ncloseCol (number of columns that will be closed first)");
//         System.out.println("Example:");
//         System.out.println("■ ■ K");
//         System.out.println("■ ■ 5");
//         System.out.println("■ ■ A");
//         System.out.println("The rows is 3 with cardCol is 3 and closeCol is 2");
//         // System.out.print("Input rows (number of rows to place cards): ");
//         // int rows = Integer.parseInt(scanner.nextLine());
//         // System.out.print("Input sets (number of card sets to solve): ");
//         // int sets = Integer.parseInt(scanner.nextLine());
//         // System.out.print("Input cardCol (number of columns that the card will be initialized): ");
//         // int nCardCol = Integer.parseInt(scanner.nextLine());
//         // System.out.print("Input closeCol (number of columns that will be closed first): ");
//         // int nCloseCol = Integer.parseInt(scanner.nextLine());
//         int rows = 10;
//         int sets = 1;
//         int nCardCol = 1;
//         int nCloseCol = 0;

//         // initial boardgame
//         BoardGame b = new BoardGame(rows, sets);
//         b.initialize(nCardCol, nCloseCol);
//         b.printBoard();

//         // initial DFS
//         DFS dfs = new DFS(b, new ArrayList<DFS>());

//         // global states
//         ArrayList<DFS> states = new ArrayList<DFS>();
//         states.add(dfs);
//         ArrayList<BoardGame> path = new ArrayList<BoardGame>();

//         // state
//         // State s = new State(dfs);
//         dfs.generate(states, path);

//         // for(int i = 0; i < path.size(); i++){
//         //     try{
//         //         Thread.sleep(1000);
//         //     }
//         //     catch(InterruptedException e){
//         //         System.out.println("Interrupted");
//         //     }
//         //     path.get(i).printBoard();
//         // }

//         scanner.close();
//     }
// }

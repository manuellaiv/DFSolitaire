import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class BoardGame {
    private Card[][] board;
    private int m;
    private int n;
    private int sets;
    private Stocks stocks;
    private Map<String, Integer> dict;
    private int card_count;

    public BoardGame(int m, int sets){
        this.m = m;
        this.sets = sets;
        this.n = sets*13;
        this.board = new Card[m][n];
        this.card_count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                this.board[i][j] = new Card("x");
            }
        }
        this.stocks = new Stocks(this.sets);
        dict = new HashMap<String, Integer>();
        dict.put("K", 13);
        dict.put("Q", 12);
        dict.put("J", 11);
        dict.put("10", 10);
        dict.put("9", 9);
        dict.put("8", 8);
        dict.put("7", 7);
        dict.put("6", 6);
        dict.put("5", 5);
        dict.put("4", 4);
        dict.put("3", 3);
        dict.put("2", 2);
        dict.put("A", 1);
        dict.put("x", 9999);
    }

    public Card[][] getBoard(){
        return this.board;
    }

    public void setBoard(Card[][] board){
        this.board = board;
    }

    public int getM(){
        return this.m;
    }

    public int getSets(){
        return this.sets;
    }

    public void setSets(int sets){
        this.sets = sets;
    }

    public int getCardCount(){
        return this.card_count;
    }

    public void setCardCount(int card_count){
        this.card_count = card_count;
    }

    public Map<String, Integer> getDict(){
        return this.dict;
    }

    public Stocks getStocks(){
        return this.stocks;
    }

    public boolean validBoardIdx(int i, int j){
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

    public boolean validCardIdx(int i, int j){
        return (i >= 0 && i < m && j >= 0 && j <= getLastIdxRow(i));
    }

    public boolean validOpenedCardIdx(int i, int j){
        return (validCardIdx(i,j) && getIdx(i,j).getOpen());
    }

    public Card getIdx(int i, int j){
        return this.board[i][j];
    }

    public void setIdx(int i, int j, Card card){
        this.board[i][j] = card;
    }

    public void setIdxOopen(int i, int j){
        this.board[i][j].setOpen(true);
    }

    public int getLastIdxRow(int i){
        boolean empty = false;
        int j = 0;
        while (j < n && !empty){
            if (this.board[i][j].getName() == "x"){
                empty = true;
            }
            else{
                j++;
            }
        }
        return j-1;
    }

    public void initialize(int nCardCol, int nCloseCol){
        if (nCardCol > nCloseCol){
            for(int j = 0; j < nCloseCol; j++){
                for(int i = 0; i < m; i++){
                    String name = this.stocks.take();
                    this.board[i][j] = new Card(name);
                }
            }
            for(int j = nCloseCol; j < nCardCol; j++){
                for(int i = 0; i < m; i++){
                    String name = this.stocks.take();
                    this.board[i][j] = new Card(name);
                    this.board[i][j].setOpen(true);
                }
            }
        }
    }

    public ArrayList<int[]> getFinished(){
        ArrayList<int[]> finished = new ArrayList<int[]>();
        boolean valid = true;
        int test;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < getLastIdxRow(i); j++){
                if (this.board[i][j].getOpen() && this.board[i][j].getName() == "K"){
                    valid = true;
                    test = j+1;
                    while (test < getLastIdxRow(i) && valid){
                        if (dict.get(this.board[i][test].getName()) != (dict.get(this.board[i][test-1].getName()) + 1)){
                            valid = false;
                        }
                        else{
                            test++;
                        }
                    }
                    if (valid){
                        finished.add(new int[] {i,j});
                        this.card_count++;
                    }
                }
            }
        }
        return finished;
    }

    public void checkFinished(){
        ArrayList<int[]> finished = getFinished();
        for(int[] el: finished){
            for(int j = el[1]; j < getLastIdxRow(el[0]); j++){
                this.board[el[1]][j] = new Card("x");
            }
        }
    }

    public void open(){
        if (!this.stocks.isEmpty()){
            int i = 0;
            while(!this.stocks.isEmpty() && i < m){
                String name = this.stocks.take();
                this.board[i][getLastIdxRow(i)+1].setName(name);
                this.board[i][getLastIdxRow(i)].setOpen(true);
                i++;
            }
            System.out.println("opened");
        }
    }

    public boolean validMove(int i, int j){
        if (validOpenedCardIdx(i, j)){
            boolean valid = true;
            boolean empty = false;
            int j_check = j+1;
                while(j_check <= getLastIdxRow(i) && valid && !empty){
                    if (this.board[i][j_check].getName() == "x"){
                        empty = true;
                    }
                    if (!empty && (dict.get(this.board[i][j_check].getName()) != dict.get(this.board[i][j_check-1].getName())+1)){
                        valid = false;
                    }
                    j_check++;
                }

            return valid;
        }
        else{
            return false;        
        }
    }

    public boolean validPut(int i, int j, int in){
        return (dict.get(this.board[in][getLastIdxRow(in)].getName()) == (dict.get(this.board[i][j].getName()) + 1));
    }

    public void move(int i, int j, int in){
        if (validMove(i, j) && validPut(i,j,in)){
            for (int jj = j; jj < getLastIdxRow(i)+1; jj++){
                this.board[in][getLastIdxRow(in)+1].setName(this.board[i][jj].getName());
                this.board[in][getLastIdxRow(in)].setOpen(true);
                this.board[i][jj].setName("x");
                this.board[i][jj].setOpen(false);
            }
            if (j > 0){
                this.board[i][j-1].setOpen(true);
            }
            System.out.println("moved from " + i + ", " + j + " to " + in);
        }
    }

    public void printBoard(){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (this.board[i][j].getName() != "x"){
                    if (this.board[i][j].getOpen()){
                        System.out.print(this.board[i][j].getName() + " ");
                    }
                    else{
                        System.out.print("■ ");
                    }
                }
            }
            System.out.println();
        }
    }

    public BoardGame doAct(Action act){
        BoardGame boardnew = new BoardGame(this.m, this.sets);
        boardnew.board = this.board;
        boardnew.n = this.n;
        boardnew.stocks = this.stocks;
        boardnew.dict = this.dict;
        boardnew.card_count = this.card_count;
        if (act.getType() == "open"){
            boardnew.open();
        }
        else{
            boardnew.move(act.getInit()[0], act.getInit()[1], act.getFin());
        }
        return boardnew;
    }

    public static void main(String args[]){
        BoardGame b = new BoardGame(3, 1);
        b.initialize(3, 2);
        System.out.println("--Initial--");
        b.setIdx(0,2, new Card("J"));
        b.setIdx(1,2, new Card("10"));
        b.setIdxOopen(0, 2);
        b.setIdxOopen(1, 2);
        b.printBoard();
        b.move(1,2,0);
        b.printBoard();
    }
}

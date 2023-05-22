import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

class DFS {
    private BoardGame board;
    private Map<Action, Boolean> acts;


    public DFS(BoardGame board, ArrayList<DFS> s){
        this.board = board;
        this.acts = new HashMap<Action, Boolean>();
        generateActs(s);
    }

    public DFS(DFS dfs){
        this.board = dfs.getBoard();
        this.acts = dfs.getActs();
    }

    public BoardGame getBoard(){
        return this.board;
    }

    public void setBoard(BoardGame board){
        this.board = board;
    }

    public Map<Action, Boolean> getActs(){
        return this.acts;
    }

    public void setActsTrue(Action key){
        this.acts.put(key, true);
    }

    public ArrayList<int[]> getMovable(){
        Map<String, Integer> dict = this.board.getDict();
        Card[][] board = this.board.getBoard();
        ArrayList<int[]> cons = new ArrayList<int[]>();
        boolean valid;
        int test;
        for(int i = 0; i < this.board.getM(); i++){
            for(int j = 0; j < this.board.getLastIdxRow(i)+1; j++){
                if (board[i][j].getOpen()){
                    test = j;
                    valid = true;
                    while (test < this.board.getLastIdxRow(i) && valid){
                        if (dict.get(board[i][test].getName()) != (dict.get(board[i][test+1].getName()) + 1)){
                            valid = false;
                        }
                        else{
                            test++;
                        }
                    }
                    if (valid){
                        cons.add(new int[] {i,j});
                    }
                }
            }
        }
        Collections.sort(cons, new intComparator());
        return cons;
    }

    public boolean containsAct(ArrayList<DFS> s, Action act){
        boolean found = false;
        int i = 0;
        while (i < s.size()-1 && !found){
            if (s.get(i).getBoard() == this.board && s.get(i).getActs().containsKey(act)){
                found = true;
            }
            else{
                i++;
            }
        }
        return found;
    }

    public void generateActs(ArrayList<DFS> s){
        Card[][] board = this.board.getBoard();
        Map <String, Integer> dict = this.board.getDict();
        ArrayList<int[]> movable = getMovable();
        for(int[] el: movable){
            for(int i = 0; i < this.board.getM(); i++){
                if (dict.get(board[i][this.board.getLastIdxRow(i)].getName()) == (dict.get(board[el[0]][el[1]].getName()) + 1)){
                    Action act = new Action("move", el, i);
                    if (!containsAct(s, act)){
                        this.acts.put(act, false);
                    }
                }
            }
        }
        if (!this.board.getStocks().isEmpty()){
            this.acts.put(new Action("open"), false);
        }
    }

    public void generate(ArrayList<DFS> history, ArrayList<BoardGame> path){
        path.add(this.board);
        Map<Action, Boolean> acts = this.acts;
        boolean found_acts = false;
        Iterator<Entry<Action, Boolean> > new_Iterator = acts.entrySet().iterator();

        while(new_Iterator.hasNext() && !found_acts){
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Interrupted");
            }
            Map.Entry<Action, Boolean> new_Map = (Map.Entry<Action, Boolean>) new_Iterator.next();
            if ((!new_Map.getValue())){ // not yet executed action
                found_acts = true;
                Action key = new_Map.getKey();
                this.acts.put(key, true); // set true
                // do action
                BoardGame generated = this.board.doAct(key);
                DFS newdfs = new DFS(generated, history);
                if (!history.contains(newdfs)){
                    generated.printBoard();
                    newdfs.generate(history, path);
                    history.add(newdfs);
                    generated.printBoard();
                }
            }
        }
    }

    public boolean isEmptyActs(){
        return this.acts.isEmpty();
    }

    public boolean finished(ArrayList<DFS> s){
        boolean end = true;
        int i = 0;
        while (i < s.size() && end){
            if(s.get(i).getActs().containsValue(false)){
                end = false;
            }
            else{
                i++;
            }
        }
        return end;
    }

    public static void main(String args[]){
        int rows = 3;
        int sets = 1;
        int nCardCol = 3;
        int nCloseCol = 2;
        BoardGame b = new BoardGame(rows, sets);
        b.initialize(nCardCol, nCloseCol);
        b.setIdx(0,2, new Card("J"));
        b.setIdx(1,2, new Card("10"));
        b.setIdxOopen(0, 2);
        b.setIdxOopen(1, 2);
        b.printBoard();
        DFS dfs = new DFS(b, new ArrayList<DFS>());

        System.out.println("movable: ");
        ArrayList<int[]> movable = dfs.getMovable();
        for(int[] i: movable){
            System.out.println(i[0] + ", " + i[1]);
        }

        System.out.println("acts: ");
        Set<Action> a = dfs.getActs().keySet();
        for(Action el: a){
            System.out.println(el.getType());
            if (el.getType() == "move"){
                System.out.println(el.getInit()[0] + ", " + el.getInit()[1] + " to row " + el.getFin());
            }
        }
    }
}

class intComparator implements Comparator<int[]>{
    public int compare(int[] a, int[] b){
        if (a[1] == b[1]){
            return 0;
        }
        else if (a[1] > b[1]){
            return 1;
        }
        else{
            return -1;
        }
    }
}
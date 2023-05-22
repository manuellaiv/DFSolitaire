import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Stocks {
    private ArrayList<String> l;
    private Map<String,Integer> stocks;

    public Stocks(int sets){
        this.l = new ArrayList<String>();
        this.stocks = new HashMap<String, Integer>();
        this.stocks.put("K", sets);
        this.stocks.put("Q", sets);
        this.stocks.put("J", sets);
        this.stocks.put("10", sets);
        this.stocks.put("9", sets);
        this.stocks.put("8", sets);
        this.stocks.put("7", sets);
        this.stocks.put("6", sets);
        this.stocks.put("5", sets);
        this.stocks.put("4", sets);
        this.stocks.put("3", sets);
        this.stocks.put("2", sets);
        this.stocks.put("A", sets);
        for(int i = 0; i < sets; i++){
            this.l.add("K");
            this.l.add("Q");
            this.l.add("J");
            this.l.add("10");
            this.l.add("9");
            this.l.add("8");
            this.l.add("7");
            this.l.add("6");
            this.l.add("5");
            this.l.add("4");
            this.l.add("3");
            this.l.add("2");
            this.l.add("A");
        }
        Collections.shuffle(l);
    }

    public ArrayList<String> getL(){
        return this.l;
    }

    public String take(){
        String taken = this.l.get(0);
        if (this.stocks.get(this.l.get(0)) > 0){
            this.stocks.put(this.l.get(0), this.stocks.get(this.l.get(0))-1);
            this.l.remove(0);
        }else{
            System.out.println("Not valid.");
        }
        return taken;
    }

    public boolean isEmpty(){
        return this.l.isEmpty();
    }

    public int getSize(){
        return this.l.size();
    }
}

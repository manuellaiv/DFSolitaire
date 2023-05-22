// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.Map;
// import java.util.Map.Entry;

// class State {
//     private ArrayList<DFS> states;
//     private DFS current;
//     private ArrayList<BoardGame> path;

//     public State(DFS current){
//         this.states = new ArrayList<DFS>();
//         this.states.add(current);
//         this.current = current;
//         this.path = new ArrayList<BoardGame>();
//     }

//     public ArrayList<DFS> getStates(){
//         return this.states;
//     }

//     public DFS getCurrent(){
//         return this.current;
//     }

//     public void setCurrent(DFS current){
//         this.current = current;
//     }

//     public ArrayList<BoardGame> getPath(){
//         return this.path;
//     }

//     public void generate(){
//         this.path.add(this.current.getBoard());
//         int i;
//         while (!done()){
//             i = this.states.size()-1;
//             boolean found = false;
//             while (i >= 0 && !found){
//                 System.out.println("aman " + i);
//                 try{
//                     Thread.sleep(1000);
//                 }
//                 catch(InterruptedException e){
//                     System.out.println("Interrupted");
//                 }
//                 if (!this.states.get(i).isDone()){
//                     Map<Action, Boolean> acts = this.states.get(i).getActs();
//                     boolean found_acts = false;
//                     Iterator<Entry<Action, Boolean> > new_Iterator = acts.entrySet().iterator();
//                     while(new_Iterator.hasNext() && !found_acts){
//                         Map.Entry<Action, Boolean> new_Map = (Map.Entry<Action, Boolean>) new_Iterator.next();
//                         if (!new_Map.getValue()){
//                             Action key = new_Map.getKey();
//                             System.out.println(key.getType());
//                             found_acts = true;
//                             DFS newdfs = new DFS(this.states.get(i));
//                             newdfs.setActsTrue(key);
//                             this.states.set(i, newdfs);
//                             BoardGame generated = (this.states.get(i).getBoard()).doAct(key);
//                             path.add(generated);
//                             this.states.add(new DFS(generated, this.states));
//                             generated.printBoard();
//                         }
//                     }
//                     found = true;
//                 }
//                 else{
//                     i--;
//                 }
//             }
//         }
//     }

//     public void generate(ArrayList<DFS> s, ArrayList<BoardGame> path){
//         this.path.add(this.current.getBoard());
//         for(int i = this.states.size()-1; i > 0; i--){
            
//         }
//     }

//     public boolean done(){
//         boolean completed = true;
//         int i = 0;
//         while(i < this.states.size() && completed){
//             if (!this.states.get(i).isEmptyActs()){
//                 completed = false;
//             }
//             else{
//                 i++;
//             }
//         }
//         return completed;
//     }
// }

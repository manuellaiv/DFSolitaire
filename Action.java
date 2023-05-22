class Action {
    private String type;
    private int[] init;
    private int fin;
    private boolean checked;

    public Action(String type){
        this.type = type;
        this.checked = false;
    }

    public Action(String type, int[] init, int fin){
        this.type = type;
        this.init = init;
        this.fin = fin;
        this.checked = false;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int[] getInit(){
        return this.init;
    }

    public void setInit(int[] init){
        this.init = init;
    }

    public int getFin(){
        return this.fin;
    }

    public void setFin(int fin){
        this.fin = fin;
    }

    public boolean getChecked(){
        return this.checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }
}

class Card{
    private String name;
    private boolean open;

    public Card(String name){
        this.name = name;
        this.open = false;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Boolean getOpen(){
        return this.open;
    }

    public void setOpen(boolean open){
        this.open = open;
    }
}
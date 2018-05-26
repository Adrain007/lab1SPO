package StackMachine;

class Variable {

    private int value;
    private String type;

    Variable(int value, String type){
        this.value = value;
        this.type = type;
    }
    void setValue(int value){
        this.value = value;
    }

    int getValue(){
        return value;
    }
    String getType(){
        return type;
    }
}
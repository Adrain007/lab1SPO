package StackMachine;

class Variable extends VarList{

    private float value;
    private String type;

    Variable(int value, String type){
        this.value = value;
        this.type = type;
    }

    void setValue(float value){
        this.value = value;
    }
    float getValue(){
        return value;
    }
    String getType(){
        return type;
    }
}
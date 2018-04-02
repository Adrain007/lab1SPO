class Token {
    private String type;
    private String value;

    Token(String type, String value){
        this.type = type;
        this.value = value;
    }

    String getValue(){
        return value;
    }
    String getType(){
        return type;
    }
}

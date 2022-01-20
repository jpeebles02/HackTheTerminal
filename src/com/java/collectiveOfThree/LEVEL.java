package com.java.collectiveOfThree;

public enum LEVEL {
    //user will input a number. we use the input as a key to the filename
    //industry stardard in associating details with identifier
    LEVEL_1("level_1.txt", 1),
    LEVEL_2("level_2.txt", 2),
    LEVEL_3("level_3.txt", 3),
    LEVEL_4("level_4.txt", 4),
    LEVEL_5("level_5.txt", 5);

    //instance variables
    private String filename;
    private int num;

    //constructor
    LEVEL(String filename, int num) {
        this.filename = filename;
        this.num = num;
    }

    //getters
    public String getFilename() {
        return filename;
//        return this.filename;
    }

    public int getNum() {
        return num;
//        return this.num;
    }

    //the user provides number, how do we get the level from that?
    //iterate through all the values from the level, gets the num, if that num is equal to the level(number) that somebody provided, return level or that ENUM
    public static LEVEL getEnumFromNum(int num){
        for(LEVEL level: LEVEL.values()){
            if(level.getNum() == num) {
                return level;
            }
        }
        return null;
    }

}

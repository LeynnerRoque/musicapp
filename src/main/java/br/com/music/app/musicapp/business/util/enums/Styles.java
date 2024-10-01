package br.com.music.app.musicapp.business.util.enums;

public enum Styles {

    ROCK("Rock"),
    INDIE_ROCK("INDIE ROCK"),
    METAL("METAL"),
    HEAVY_METAL("HEAVY METAL"),
    INDIE("INDIE"),
    COUNTRY("COUNTRY"),
    BALAD("BALAD"),
    POP("POP"),
    ELETRONIC("ELETRONIC"),
    DISCO("DISCO");


    private String description;

    private Styles(String value){
        description = value;
    }

    public String toString(){
        return description;
    }
}

package br.com.music.app.musicapp.business.util.enums;

public enum Types {
   SOLO("SOLO"),DUO("DUO"),TRIO("TRIO"),BAND("BAND")

   private String name;
   private Types(String value){
      name = value;
   }

   public String toString(){
      return name;
   }

}

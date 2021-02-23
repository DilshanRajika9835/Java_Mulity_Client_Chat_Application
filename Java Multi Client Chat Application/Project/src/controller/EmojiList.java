package controller;

import java.util.ArrayList;

public class EmojiList {
   public  ArrayList<String> emoji=new ArrayList<>();
   public  String getimoji(int index){
        return emoji.get(index);
   }

    public EmojiList() {
        emoji.add("\uD83D\uDE00");
        emoji.add("\uD83D\uDE03 ");
        emoji.add("\uD83D\uDC68\u200D⚕️");
        emoji.add("\uD83D\uDE0A");
        emoji.add("\uD83D\uDE07 ");
        emoji.add("\uD83D\uDE09 ");
        emoji.add("\uD83D\uDE0D ");
        emoji.add("\uD83D\uDC69\u200D\uD83C\uDF93 ");
        emoji.add(" \uD83D\uDC69\u200D\uD83D\uDE92 ");
        emoji.add("  \uD83D\uDC69\u200D\uD83D\uDD2C");
    }
}

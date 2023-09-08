package com.mycom.word;

public class WordManager {

    WordCRUD word = new WordCRUD();

    public void start() {
        while(true) {
            int menu = word.select();
            if(menu == 0) break;
            if(menu == 1) {
                word.listAll();
            }
            else if(menu == 2) {
                //listLevel
            }
            else if(menu == 3) {
                //search
            }
            else if(menu == 4) {
                word.add();
            }
            else if(menu == 5) {
                //update
            }
            else if(menu == 6) {
                //delete
            }
            else if(menu == 7) {
                //saveFile
            }
        }
    }
}

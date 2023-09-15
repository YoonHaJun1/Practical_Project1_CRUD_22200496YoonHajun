package com.mycom.word;

public class WordManager {

    WordCRUD word = new WordCRUD();

    public void start() {

        word.loadFile();
        while(true) {
            int menu = word.select();
            if(menu == 0) {
                System.out.println("프로그램이 종료되었습니다!");
                break;
            }
            if(menu == 1) {
                word.listAll();
            }
            else if(menu == 2) {
                word.listLevel();
            }
            else if(menu == 3) {
                word.search();
            }
            else if(menu == 4) {
                word.add();
            }
            else if(menu == 5) {
                word.updateItem();
            }
            else if(menu == 6) {
                word.deleteItem();
            }
            else if(menu == 7) {
                word.saveFile();
            }
        }
    }
}

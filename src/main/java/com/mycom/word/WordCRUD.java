package com.mycom.word;

import java.util.Scanner;
import java.util.ArrayList;


public class WordCRUD implements ICRUD {

    ArrayList<Word> list = new ArrayList<>();

    Scanner s = new Scanner(System.in);

    /*
    번호를 입력하세요. 1
    ================
    1 *    apple   사과
    2 **    ship   배
    ================
    */
    public void listAll() {
        System.out.println("================");
        for(int i =0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("================");

    }
    /*
    번호를 입력하세요. 4
    난이도(1, 2, 3) & 새 단어 입력 : 1 driveway
    뜻 입력 : 차고 진입로
    새 단어가 단어장에 추가되었습니다.
    */
    @Override
    public void add() {
        System.out.print("난이도 (1, 2, 3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        Word Word = new Word(0, level, word, meaning);

        list.add(Word);

        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    /*
    ---영단어 단어장---
    ================
    1. 모든 단어 보기
    2. 수준 별 단어 보기
    3. 단어 검색
    4. 단어 추가
    5. 단어 수정
    6. 단어 삭제
    7. 파일 저장
    0. 나가기
    ================
    번호를 입력하세요.
    */
    @Override
    public int select() {
        System.out.print("---영단어 단어장---\n" +
                "================\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준 별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "================\n" +
                "번호를 입력하세요. ");
        return s.nextInt();
    }
}

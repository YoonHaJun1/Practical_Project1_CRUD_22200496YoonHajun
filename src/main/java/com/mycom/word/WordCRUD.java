package com.mycom.word;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class WordCRUD implements ICRUD {

    ArrayList<Word> list = new ArrayList<>();

    Scanner s = new Scanner(System.in);

    final String fname = "Dictionary.txt";

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

    public void listAll(int level) {
        int j = 0;

        System.out.println("================");

        for(int i = 0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("================");
    }

    public ArrayList<Integer> listAll(String keyword) {

        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;

        System.out.println("================");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("================");
        return idlist;
    }


    /*
    번호를 입력하세요. 4
    난이도(1, 2, 3) & 새 단어 입력 : 1 driveway
    뜻 입력 : 차고 진입로
    새 단어가 단어장에 추가되었습니다.
    */
    @Override
    public void add() {
        System.out.print("난이도 (1:초급, 2:중급, 3:고급 ) & 새 단어 입력 : ");
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

    /*
    수정할 단어 검색 : er
    ================
    1 *      trasfer  옮기다, 이동하다
    2 *      scatter  흩뿌리다, 살포하다
    ================
    수정할 번호를 입력하세요 : 1
    뜻 입력 : 옮기다, 이동하다, 이동, 전송
    단어가 수정되었습니다.
    */
    public void updateItem() {

        System.out.print("수정할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("수정할 번호를 입력하세요. ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();


        Word word = list.get(idlist.get(id-1));

        word.setMeaning(meaning);

        System.out.println("단어가 수정되었습니다. ");
    }

    @Override
    public void delete() {

    }

    /*
    삭제할 단어 검색 : er
    ================
    1 *    transfer  옮기다, 이동하다
    2 *     scatter  흩뿌리다, 살포하다
    ================
    삭제할 번호를 선택하세요. 1
    정말로 삭제하시겠습니가? ( Y / N ) Y
    단어가 삭제되었습니다.
    */

    public void deleteItem() {
        int j = 0;
        System.out.print("삭제할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("삭제할 번호를 선택하세요. ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("정말로 삭제하시겠습니까? ( Y / N ) ");
        String answer = s.next();

        if(answer.equalsIgnoreCase("Y")) {
            list.remove((int)idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다.");
        } else
            System.out.println("취소되었습니다. ");
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

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));

            String line;
            int count = 0;

            while(true) {
                line = br.readLine();
                if(line == null) break;

                String[] data = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }

            br.close();
            System.out.println("단어 " + count + "개 로딩 완료!");


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fname));
            for(Word one : list) {
                pw.write(one.toFileString() + "\n");
            }
            pw.close();
            System.out.println("데이터 저장을 완료했습니다. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listLevel() {
        System.out.print("원하는 레벨을 입력하세요. (1 ~ 3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void search() {
        System.out.print("원하는 단어를 입력하세요. ");
        String keyword = s.next();
        listAll(keyword);
    }
}

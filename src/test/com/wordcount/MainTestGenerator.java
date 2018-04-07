package test.com.wordcount;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainTestGenerator extends BaseMainTestcaseGenerator {
    @Override
    public void generateOne(int testcaseNum, int randomWordNum, int wordNum) {
        try {
            testCaseWriter = new FileWriter(new File("Main_testcase" + testcaseNum + ".txt"));
            answerWriter=new FileWriter(new File("Main_answer" + testcaseNum + ".txt"));
            randomWords = new WordCountPair[randomWordNum];
            currentRandomWord = 0;
            for (int i = 0; i < randomWordNum; ++i) {
                generateNewWord();
            }
            generateContent(wordNum);
        }catch (IOException e){
            System.err.println("Failed to generate testcase:"+e.getMessage());
        }

    }

    @Override
    public void generate() {
        generateOne(6,20,150);
        generateOne(7,9,150);
        generateOne(8,100,20);
        generateOne(9,1,120);
        generateOne(10,20,150);

        generateOne(11,20,514);
        generateOne(12,70,495);
        generateOne(13,50,600);
        generateOne(14,99,500);
        generateOne(15,150,500);

        generateOne(16,9,1200);
        generateOne(17,99,1200);
        generateOne(18,514,1200);
        generateOne(19,100,1200);
        generateOne(20,999,1200);
    }
    
    public static void main(String[] args){
        MainTestGenerator generator=new MainTestGenerator();
        generator.generate();
    }
}

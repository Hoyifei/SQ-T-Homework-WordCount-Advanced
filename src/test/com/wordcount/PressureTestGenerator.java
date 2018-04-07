package test.com.wordcount;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PressureTestGenerator extends BaseMainTestcaseGenerator {
    @Override
    public void generateOne(int testcaseNum, int randomWordNum, int wordNum) {
        try {
            testCaseWriter = new FileWriter(new File("Pressure_testcase" + testcaseNum + ".txt"));
            answerWriter=null;
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
        generateOne(6,495,10492);
        generateOne(7,495,10388);
        generateOne(8,514,10492);
        generateOne(9,514,10388);
        generateOne(10,999,17362);

        generateOne(11,1500,100007);
        generateOne(12,1500,495514);
        generateOne(13,1500,203677);
        generateOne(14,1500,514495);
        generateOne(15,1500,388492);

        generateOne(16,5000,1000007);
        generateOne(17,5000,4959514);
        generateOne(18,5000,2039677);
        generateOne(19,5000,5149495);
        generateOne(20,5000,10388492);
    }

    public static void main(String[] args){
        PressureTestGenerator generator=new PressureTestGenerator();
        generator.generate();
    }
}

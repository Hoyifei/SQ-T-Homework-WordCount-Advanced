package test.com.wordcount.trie.testcasegenerator;
import test.com.wordcount.PairComparator;
import test.com.wordcount.WordCountPair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Random;
public class TrieTestcaseGenerator {
    private static final int MAX_WORD_LENGTH=32;

    public static void generate(int testcaseNum, int randomWordNum, int wordNum){
        String testcaseName="Trie_testcase"+testcaseNum+".txt";
        String answerName="Trie_answer"+testcaseNum+".txt";
        Random random=new Random();
        int currentWordLen;
        StringBuilder builder;
        WordCountPair[] words=new WordCountPair[randomWordNum];
        for(int i=0;i<randomWordNum;++i){
            boolean newword=false;
            String word="";
            while(!newword) {
                newword=true;
                builder = new StringBuilder();
                currentWordLen = random.nextInt(MAX_WORD_LENGTH) + 1;
                for (int j = 0; j < currentWordLen; ++j) {
                    builder.append((char) ('a' + random.nextInt(26)));
                }
                word=builder.toString();
                for(int j=0;j<i;++j){
                    if(word.compareTo(words[j].word)==0){
                        newword=false;
                        break;
                    }
                }
            }
            words[i]=new WordCountPair();
            words[i].word=word;
            words[i].count=0;
        }
        File testcaseFile=new File(testcaseName);
        File answerFile=new File(answerName);

        try{
            Writer testcaseWriter=new FileWriter(testcaseFile);
            Writer answerWriter=new FileWriter(answerFile);
            int curWord;
            for(int i=0;i<wordNum;++i){
                curWord=random.nextInt(randomWordNum);
                words[curWord].count++;
                for(int j=0;j<words[curWord].word.length();++j){
                    if(random.nextInt(2)==0){
                        testcaseWriter.write(words[curWord].word.charAt(j));
                    }else{
                        testcaseWriter.write(words[curWord].word.toUpperCase().charAt(j));
                    }
                }
                testcaseWriter.write('\n');
            }
            Arrays.sort(words,new PairComparator());
            for(int i=0;i<randomWordNum;++i){
                if(words[i].count<=0){
                    break;
                }
                answerWriter.write(words[i].word);
                answerWriter.write(' ');
                answerWriter.write(((Integer)(words[i].count)).toString());
                answerWriter.write('\n');
            }
            testcaseWriter.close();
            answerWriter.close();
        }catch (IOException e){
            System.err.println("Generate testcase failed:"+e.getMessage());
        }
    }

    public static void main(String[] args) {
        generate(6,20,100);
        generate(7,9,100);
        generate(8,100,20);
        generate(9,1,100);
        generate(10,20,100);

        generate(11,20,500);
        generate(12,70,500);
        generate(13,50,600);
        generate(14,99,500);
        generate(15,150,500);

        generate(16,9,1200);
        generate(17,99,1200);
        generate(18,514,1200);
        generate(19,100,1200);
        generate(20,999,1200);
    }
}

package test.com.wordcount;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Random;

public abstract class BaseMainTestcaseGenerator {
    protected WordCountPair[] randomWords;
    protected int currentRandomWord;
    protected Random random;
    protected static final int MAX_WORD_LENGTH=16;
    protected static final int MAX_SEPARATOR_LENGTH=5;
    protected Writer testCaseWriter,answerWriter;
    protected static final char[] OTHER_CHARS={'~','`','!','#','%','^','&','*','_','(',')'
            ,'[',']','+','=',':',';','"','\'','|','<','>'
            ,',','.','/','?',' ','\n','\t','-'};
    protected static final int OTHER_CHAR_BOUND=OTHER_CHARS.length;
    protected static final int NON_CONNECT_BOUND=OTHER_CHAR_BOUND-1;
    protected void generateNewWord(){
        boolean finished=false;
        int randomLength,randomChar;
        StringBuffer buffer=new StringBuffer();
        while(!finished){
            randomLength=random.nextInt(MAX_WORD_LENGTH)+1;
            buffer=new StringBuffer();

            randomChar=random.nextInt(26);
            buffer.append((char)('a'+randomChar));

            for(int i=0;i<randomLength;++i){
                randomChar=random.nextInt(27);
                if(randomChar<26){
                    buffer.append((char)('a'+randomChar));
                }else{
                    buffer.append('-');
                    randomChar=random.nextInt(26);
                    buffer.append((char)('a'+randomChar));
                }
            }

            randomChar=random.nextInt(26);
            buffer.append((char)('a'+randomChar));

            finished=true;
            for(int i=0;i<currentRandomWord;++i){
                if(randomWords[i].word.compareTo(buffer.toString())==0){
                    finished=false;
                    break;
                }
            }

        }
        randomWords[currentRandomWord]=new WordCountPair();
        randomWords[currentRandomWord].word=buffer.toString();
        randomWords[currentRandomWord].count=0;
        currentRandomWord++;
    }
    protected void generateSeparators(int len) throws IOException{
        if(len==1){
            testCaseWriter.write(OTHER_CHARS[random.nextInt(NON_CONNECT_BOUND)]);
        }else if(len>1){
            for(int i=0;i<len;++i){
                testCaseWriter.write(OTHER_CHARS[random.nextInt(OTHER_CHAR_BOUND)]);
            }
        }
    }
    protected void generateContent(int wordNum) throws IOException{
        generateSeparators(random.nextInt(MAX_SEPARATOR_LENGTH));
        int randomWord;
        for(int i=0;i<wordNum;++i){
            randomWord=random.nextInt(currentRandomWord);
            randomWords[randomWord].count++;
            testCaseWriter.write(randomWords[randomWord].word);
            generateSeparators(random.nextInt(MAX_SEPARATOR_LENGTH)+1);
        }
        testCaseWriter.close();
        if(answerWriter!=null) {
            Arrays.sort(randomWords, new PairComparator());
            int answerLen = 100;
            if (answerLen > randomWords.length) {
                answerLen = randomWords.length;
            }
            for (int i = 0; i < answerLen; ++i) {
                if(randomWords[i].count<=0){
                    break;
                }
                answerWriter.write(randomWords[i].word);
                answerWriter.write(' ');
                answerWriter.write(((Integer) randomWords[i].count).toString());
                if (i != answerLen - 1 && randomWords[i+1].count>0) {
                    answerWriter.write('\n');
                }
            }
            answerWriter.close();
        }
    }
    public BaseMainTestcaseGenerator(){
        random=new Random();
    }

    /**
     * Generate one testcase
     * @param testcaseNum The number of this testcase. Affects the filename of generated testcase
     * @param randomWordNum First generate some random word, then use them to make a testcase.
     * @param wordNum The total num of words in the whole testcase
     */
    public abstract void generateOne(int testcaseNum,int randomWordNum,int wordNum);

    /**
     * Generate all testcases.
     */
    public abstract void generate();
}

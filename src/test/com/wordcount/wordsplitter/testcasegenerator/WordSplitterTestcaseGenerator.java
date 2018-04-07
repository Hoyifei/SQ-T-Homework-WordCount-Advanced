package test.com.wordcount.wordsplitter.testcasegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class WordSplitterTestcaseGenerator {
    private static final char[] OTHER_CHARS={'~','`','!','#','%','^','&','*','_','(',')'
                                            ,'[',']','+','=',':',';','"','\'','|','<','>'
                                            ,',','.','/','?',' ','\n','\t'};
    private static final int OTHER_CHAR_BOUND=OTHER_CHARS.length;
    private static final int LOWER_ALPHABET_BOUND=OTHER_CHAR_BOUND+26;
    private static final int UPPER_ALPHABET_BOUND=LOWER_ALPHABET_BOUND+26;
    private static final int CONNECT_BOUND=UPPER_ALPHABET_BOUND+20;
    private static final int NON_CONNECT_BOUND=CONNECT_BOUND+20;
    public static void generate(int testcaseNum,int randomStep){
        String testcaseName="Splitter_testcase"+testcaseNum+".txt";
        String answerName="Splitter_answer"+testcaseNum+".txt";
        Random random=new Random();
        File testcaseFile=new File(testcaseName);
        File answerFile=new File(answerName);
        StringBuilder builder=null;
        int randomResult;
        try{
            Writer testcaseWriter=new FileWriter(testcaseFile);
            Writer answerWriter=new FileWriter(answerFile);
            for(int i=0;i<randomStep;++i){
                randomResult=random.nextInt(NON_CONNECT_BOUND);
                if(randomResult<OTHER_CHAR_BOUND){
                    if(builder!=null){
                        answerWriter.write(builder.toString());
                        answerWriter.write('\n');
                        builder=null;
                    }
                    testcaseWriter.write(OTHER_CHARS[random.nextInt(OTHER_CHAR_BOUND)]);
                }else if(randomResult>=OTHER_CHAR_BOUND&&randomResult<LOWER_ALPHABET_BOUND){
                    if(builder==null){
                        builder=new StringBuilder();
                    }
                    char alphabet=(char)('a'+random.nextInt(26));
                    builder.append(alphabet);
                    testcaseWriter.write(alphabet);
                }else if(randomResult>=LOWER_ALPHABET_BOUND&&randomResult<UPPER_ALPHABET_BOUND){
                    if(builder==null){
                        builder=new StringBuilder();
                    }
                    char alphabet=(char)('A'+random.nextInt(26));
                    builder.append(alphabet);
                    testcaseWriter.write(alphabet);
                }else if(randomResult>=UPPER_ALPHABET_BOUND&&randomResult<CONNECT_BOUND){
                    char alphabet;
                    if(builder==null){
                        builder=new StringBuilder();
                        if(random.nextInt(2)==0){
                            alphabet=(char)('a'+random.nextInt(26));
                        }else{
                            alphabet=(char)('A'+random.nextInt(26));
                        }
                        builder.append(alphabet);
                        testcaseWriter.write(alphabet);
                    }

                    builder.append('-');
                    testcaseWriter.write('-');

                    if(random.nextInt(2)==0){
                        alphabet=(char)('a'+random.nextInt(26));
                    }else{
                        alphabet=(char)('A'+random.nextInt(26));
                    }
                    builder.append(alphabet);
                    testcaseWriter.write(alphabet);
                }else{
                    testcaseWriter.write('-');
                    if(builder!=null) {
                        answerWriter.write(builder.toString());
                        answerWriter.write('\n');
                        if (random.nextInt(OTHER_CHAR_BOUND + 1) == 0) {
                            testcaseWriter.write('-');
                        } else {
                            testcaseWriter.write(OTHER_CHARS[random.nextInt(OTHER_CHAR_BOUND)]);
                        }
                    }
                    builder=null;
                }
            }
            if(builder!=null){
                answerWriter.write(builder.toString());
                answerWriter.write('\n');
            }
            answerWriter.close();
            testcaseWriter.close();
        }catch (IOException e){
            System.err.println("Generate testcase failed:"+e.getMessage());
        }
    }

    public static void main(String[] args){
        generate(6,50);
        generate(7,100);
        generate(8,150);
        generate(9,200);
        generate(10,250);
        generate(11,500);
        generate(12,550);
        generate(13,700);
        generate(14,750);
        generate(15,900);
        generate(16,950);
        generate(17,1250);
        generate(18,3450);
        generate(19,5650);
        generate(20,7850);

    }
}

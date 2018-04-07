package test.com.wordcount.wordsplitter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.wordcount.wordsplitter.WordSplitter;
/**
 * WordSplitter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 6, 2018</pre>
 */
@RunWith(Parameterized.class)
public class WordSplitterTest {


    /**
     * Method: split()
     */
    private ArrayList<String> answers,results;

    private String inputFile;

    public WordSplitterTest(String inputFile,String answerFile) throws Exception{
        this.inputFile=inputFile;
        results=new ArrayList<>();
        answers=new ArrayList<>();
        Scanner ansScanner=new Scanner(new File(answerFile));
        while(ansScanner.hasNext()){
            answers.add(ansScanner.next());
        }
    }

    private static final int TEST_CASE_NUM=20;

    @Parameters
    public static Object[][] testCases(){
        String[][] filenames=new String[TEST_CASE_NUM][2];
        for(int i=1;i<=TEST_CASE_NUM;++i){
            filenames[i-1][0]="Splitter_testcase"+i+".txt";
            filenames[i-1][1]="Splitter_answer"+i+".txt";
        }
        return(filenames);
    }

    @Test
    public void testSplit() throws Exception {
        WordSplitter splitter=new WordSplitter(new WordSplitterTestCallback(results),inputFile);
        splitter.split();
        assertEquals("Num of words is incorrect.",
                     answers.size(),results.size());
        for(int i=0;i<answers.size();++i){
            assertEquals("One of splitted word is incorrect.",
                         0,results.get(i).compareTo(answers.get(i)));
        }
    }


} 

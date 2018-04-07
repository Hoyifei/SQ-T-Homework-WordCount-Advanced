package test.com.wordcount.trie;

import com.wordcount.trie.TrieNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.wordcount.trie.Trie;
import test.com.wordcount.WordCountPair;

/**
 * Trie Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 6, 2018</pre>
 */

@RunWith(Parameterized.class)
public class TrieTest {
    private String inputFile;
    private ArrayList<WordCountPair> answers;
    private Trie trie;

    public TrieTest(String inputFile,String answerFile) throws Exception{
        this.inputFile=inputFile;

        Scanner ansScanner=new Scanner(new File(answerFile));
        answers=new ArrayList<>();
        trie=new Trie();
        WordCountPair currentPair;
        while(ansScanner.hasNext()){
            currentPair=new WordCountPair();
            currentPair.word=ansScanner.next();
            currentPair.count=ansScanner.nextInt();
            answers.add(currentPair);
        }
    }

    private static final int TEST_CASE_NUM=20;

    @Parameters
    public static Object[][] testCases(){
        String[][] filenames=new String[TEST_CASE_NUM][2];
        for(int i=1;i<=TEST_CASE_NUM;++i){
            filenames[i-1][0]="Trie_testcase"+i+".txt";
            filenames[i-1][1]="Trie_answer"+i+".txt";
        }
        return(filenames);
    }

    @Test
    public void testTrie() throws Exception{
        String word;
        Scanner wordScanner=new Scanner(new File(inputFile));
        while(wordScanner.hasNext()){
            word=wordScanner.next();
            for(int i=0;i<word.length();++i){
                trie.curWordAppendChar(word.charAt(i));
            }
            trie.curWordEnd();
        }
        TrieNode[] words=trie.sortWords();
        assertEquals("The number of different words is incorrect.",
                answers.size(),words.length);
        for(int i=0;i<words.length;++i){
            assertEquals("The word order is incorrect.",
                         0,trie.getWord(words[i]).compareTo(answers.get(i).word));
            assertEquals("The count is incorrect.",
                    answers.get(i).count,words[i].getCount());
        }
    }

} 

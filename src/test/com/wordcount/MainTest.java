package test.com.wordcount;

import com.wordcount.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * Main Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 6, 2018</pre>
 */
@RunWith(Parameterized.class)
public class MainTest {

    private static final int TEST_CASE_NUM = 20;
    private String testCaseFile,testAnsFile;

    public MainTest(String testCaseFile, String testAnsFile) {
        this.testCaseFile=testCaseFile;
        this.testAnsFile=testAnsFile;
    }

    @Parameters
    public static Object[][] testCases() {
        String[][] filenames = new String[TEST_CASE_NUM][2];
        for (int i = 1; i <= TEST_CASE_NUM; ++i) {
            filenames[i - 1][0] = "Main_testcase" + i + ".txt";
            filenames[i - 1][1] = "Main_answer" + i + ".txt";
        }
        return (filenames);
    }


    @Test
    public void testMain() throws Exception {
        String[] args=new String[1];
        args[0]=testCaseFile;
        Main.main(args);

        File answerFile = new File(testAnsFile);
        Long ansLen = answerFile.length();
        byte[] ansFileContent = new byte[ansLen.intValue()];
        FileInputStream ansFS = new FileInputStream(answerFile);
        ansFS.read(ansFileContent);
        ansFS.close();

        File resultFile = new File("result.txt");
        Long resultLen = resultFile.length();
        byte[] resultFileContent = new byte[resultLen.intValue()];
        FileInputStream resultFS = new FileInputStream(resultFile);
        resultFS.read(resultFileContent);
        resultFS.close();

        assertEquals("Output incorrect.",0,
                     new String(ansFileContent).compareTo(new String(resultFileContent)));
    }


} 

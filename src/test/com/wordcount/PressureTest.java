package test.com.wordcount;

import com.wordcount.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Main Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 6, 2018</pre>
 */
@RunWith(Parameterized.class)
public class PressureTest {

    private static final int TEST_CASE_NUM = 20;
    private String testCaseFile;

    public PressureTest(String testCaseFile) {
        this.testCaseFile=testCaseFile;
    }

    @Parameters
    public static Object[][] testCases() {
        String[][] filenames = new String[TEST_CASE_NUM][1];
        for (int i = 1; i <= TEST_CASE_NUM; ++i) {
            filenames[i - 1][0] = "Pressure_testcase" + i + ".txt";
        }
        return (filenames);
    }


    @Test
    public void testMain() {
        String[] args=new String[1];
        args[0]=testCaseFile;
        long startTime=System.currentTimeMillis();
        Main.main(args);
        long endTime=System.currentTimeMillis();
        System.out.println((endTime-startTime)+" milliseconds consumed.");
    }


} 

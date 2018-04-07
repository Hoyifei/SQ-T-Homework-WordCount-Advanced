package test.com.wordcount.wordsplitter;

import com.wordcount.wordsplitter.WordSplitterCallback;
import java.util.ArrayList;

public class WordSplitterTestCallback implements WordSplitterCallback {

    private StringBuffer buffer;
    private ArrayList<String> outputWords;

    public WordSplitterTestCallback(ArrayList<String> outputWords) {
        this.outputWords=outputWords;
        buffer = new StringBuffer();
    }

    @Override
    public void appendCharCallback(char ch) {
        buffer.append(ch);
    }

    @Override
    public void endOfWordCallback() {
        outputWords.add(buffer.toString());
        buffer = new StringBuffer();
    }


}

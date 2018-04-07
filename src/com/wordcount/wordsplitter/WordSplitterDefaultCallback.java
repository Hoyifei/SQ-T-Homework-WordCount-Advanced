package com.wordcount.wordsplitter;

public class WordSplitterDefaultCallback implements WordSplitterCallback {
    private StringBuffer buffer;

    public WordSplitterDefaultCallback() {
        buffer = new StringBuffer();
    }

    @Override
    public void appendCharCallback(char ch) {
        buffer.append(ch);
    }

    @Override
    public void endOfWordCallback() {
        System.out.println(buffer.toString());
        buffer = new StringBuffer();
    }
}

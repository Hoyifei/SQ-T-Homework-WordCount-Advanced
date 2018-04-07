package com.wordcount.wordsplitter;

public interface WordSplitterCallback {
    /**
     * When the WordSplitter decided that a char belongs to current word, it will call this to append the char to current word.
     *
     * @param ch The char to be append.
     */
    void appendCharCallback(char ch);

    /**
     * When thed WordSplitter find the end of current word, it will call this to inform that the current word is over.
     */
    void endOfWordCallback();
}

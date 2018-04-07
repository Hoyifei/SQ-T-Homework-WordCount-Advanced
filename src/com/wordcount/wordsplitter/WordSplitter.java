package com.wordcount.wordsplitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WordSplitter {
    private final WordSplitterCallback callback;
    private String content;

    public WordSplitter(WordSplitterCallback cb, String filename) throws IOException {
        if (cb == null) {
            callback = new WordSplitterDefaultCallback();
        } else {
            callback = cb;
        }

        File file = new File(filename);
        Long len = file.length();
        byte[] fileContent = new byte[len.intValue()];
        FileInputStream fs = new FileInputStream(file);
        fs.read(fileContent);
        fs.close();
        content = new String(fileContent);
    }

    private boolean isAlphabet(char ch) {
        return (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')));
    }

    public void split() {
        char ch;
        boolean inWord = false;
        for (int i = 0; i < content.length(); ++i) {
            ch = content.charAt(i);
            if (isAlphabet(ch)) {
                inWord = true;
                callback.appendCharCallback(ch);
            } else if (ch == '-') {
                if (i == 0 || i == content.length() - 1) {
                    //either charAt(i-1) || charAt(i+1) not exists
                    if (inWord) {
                        inWord = false;
                        callback.endOfWordCallback();
                    }
                } else {
                    //charAt(i-1) && charAt(i+1) exists
                    if (isAlphabet(content.charAt(i - 1)) && isAlphabet(content.charAt(i + 1))) {
                        inWord = true;
                        callback.appendCharCallback(ch);
                    } else {
                        if (inWord) {
                            inWord = false;
                            callback.endOfWordCallback();
                        }
                    }
                }
            } else {
                if (inWord) {
                    inWord = false;
                    callback.endOfWordCallback();
                }
            }
        }
        if (inWord) {
            callback.endOfWordCallback();
        }
    }
}

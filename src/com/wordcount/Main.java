package com.wordcount;

import com.wordcount.wordsplitter.WordSplitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Invalid argument");
            return;
        }else if(args.length != 1){
            System.err.println("Warning: Ignoring argument(s) except " + args[0]);
        }
        if (!Pattern.matches(".*\\.txt", args[0])) {
            System.err.println("Warning: This program is designed only to processes .txt files.");
        }

        WordSplitter splitter;
        WordCounter counter = new WordCounter();
        try {
            splitter = new WordSplitter(counter, args[0]);
        } catch (IOException e) {
            System.err.println("Read file failed");
            System.err.println(e.getMessage());
            return;
        }
        splitter.split();
        WordCounter.CountResult[] words = counter.sortWords();
        File outputFile = new File("result.txt");

        try {
            Writer out = new FileWriter(outputFile);
            for (int i = 0; i < words.length; ++i) {
                out.write(words[i].word);
                out.write(' ');
                out.write(((Integer) words[i].count).toString());
                if (i != words.length - 1) {
                    out.write('\n');
                }
            }
            out.close();
        } catch (IOException e) {
            System.err.println("Output to file Failed.");
            System.err.println(e.getMessage());
            System.err.println("The result will be put in the console.");
            for (int i = 0; i < words.length; ++i) {
                System.err.printf("%s %d\n", words[i].word, words[i].count);
            }
        }
    }
}

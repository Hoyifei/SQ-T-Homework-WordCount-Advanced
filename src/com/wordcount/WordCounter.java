package com.wordcount;

import com.wordcount.trie.Trie;
import com.wordcount.trie.TrieNode;
import com.wordcount.wordsplitter.WordSplitterCallback;

public class WordCounter implements WordSplitterCallback {
    private final Trie trie;

    public WordCounter() {
        trie = new Trie();
    }

    @Override
    public void appendCharCallback(char ch) {
        trie.curWordAppendChar(ch);
    }

    @Override
    public void endOfWordCallback() {
        trie.curWordEnd();
    }

    public CountResult[] sortWords() {
        TrieNode[] orderedWords = trie.sortWords();

        int numOutputWord = 100;
        if (orderedWords.length < numOutputWord) {
            numOutputWord = orderedWords.length;
        }

        CountResult[] words = new CountResult[numOutputWord];

        for (int i = 0; i < numOutputWord; ++i) {
            words[i] = new CountResult();
            words[i].word = trie.getWord(orderedWords[i]);
            words[i].count = orderedWords[i].getCount();
        }
        return (words);
    }

    public class CountResult {
        public String word;
        public int count;
    }
}

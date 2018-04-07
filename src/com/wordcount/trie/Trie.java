package com.wordcount.trie;

import java.util.ArrayList;
import java.util.Random;

public class Trie {
    private static final int TRIE_NODE_BRANCH = 27;
    private final TrieNode root;
    private final ArrayList<TrieNode> words;
    private final Random rand_source;
    private TrieNode current;
    private TrieNode[] orderedWords;

    public Trie() {
        root = new TrieNode();
        current = root;
        words = new ArrayList<>();
        orderedWords = null;
        rand_source = new Random();
    }

    public void curWordAppendChar(char ch) {
        current = current.setSuccessive(ch);
    }

    public void curWordEnd() {
        if (!current.isWord()) {
            words.add(current);
        }
        current.markWord();
        current = root;
    }

    private boolean compareMoreEqual(TrieNode node1, TrieNode node2) {
        if (node1.getCount() != node2.getCount()) {
            return (node1.getCount() > node2.getCount());
        } else {
            return (node1.getDictOrder() <= node2.getDictOrder());
        }
    }

    private void swap(int position1, int position2) {
        if (position1 == position2) {
            return;
        }
        TrieNode node1 = orderedWords[position1], node2 = orderedWords[position2];
        orderedWords[position1] = node2;
        orderedWords[position2] = node1;
    }

    private int partition(int leftBound, int rightBound) {
        int i = leftBound - 1;
        for (int j = leftBound; j < rightBound; ++j) {
            if (compareMoreEqual(orderedWords[j], orderedWords[rightBound])) {
                ++i;
                swap(i, j);
            }
        }
        swap(i + 1, rightBound);
        return (i + 1);
    }

    private void doSort(int leftBound, int rightBound) {
        if (rightBound <= leftBound) {
            return;
        }

        int randResult = rand_source.nextInt(rightBound - leftBound + 1) + leftBound;
        swap(randResult, rightBound);

        int partitionPos = partition(leftBound, rightBound);

        doSort(leftBound, partitionPos - 1);
        doSort(partitionPos + 1, rightBound);
    }

    private int dfsDictOrder(int currentDictOrder, TrieNode node) {
        int curDictOrder = currentDictOrder;
        if (node.isWord()) {
            node.setDictOrder(curDictOrder);
            ++curDictOrder;
        }
        for (int i = 0; i < TRIE_NODE_BRANCH; ++i) {
            if (node.getSuccessive(i) != null) {
                curDictOrder = dfsDictOrder(curDictOrder, node.getSuccessive(i));
            }
        }
        return curDictOrder;
    }

    public TrieNode[] sortWords() {
        dfsDictOrder(0, root);
        orderedWords = new TrieNode[words.size()];
        for (int i = 0; i < words.size(); ++i) {
            orderedWords[i] = words.get(i);
        }

        doSort(0, words.size() - 1);
        return (orderedWords);
    }

    public String getWord(TrieNode tail) {
        TrieNode currentPtr = tail;
        StringBuffer wordBuf = new StringBuffer();
        while (currentPtr != root) {
            wordBuf.append(currentPtr.getMyChar());
            currentPtr = currentPtr.getParent();
        }
        return (wordBuf.reverse().toString());
    }
}

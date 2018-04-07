package com.wordcount.trie;

public class TrieNode {
    private static final int TRIE_NODE_BRANCH = 28;
    private final char my_char;
    private final TrieNode[] successes;
    private final TrieNode parent;
    private int count;
    private int dictOrder;

    TrieNode() {
        successes = new TrieNode[TRIE_NODE_BRANCH];
        for (int i = 0; i < TRIE_NODE_BRANCH; ++i) {
            successes[i] = null;
        }
        my_char = '#';
        count = 0;
        parent = null;
    }


    private TrieNode(char ch, TrieNode parentPtr) {
        successes = new TrieNode[TRIE_NODE_BRANCH];
        for (int i = 0; i < TRIE_NODE_BRANCH; ++i) {
            successes[i] = null;
        }
        my_char = Character.toLowerCase(ch);
        count = 0;
        parent = parentPtr;
    }

    //represent chars in integer. Only alphabet and '-' appears in a word.a~z->0~25 '-'->26
    private static int charToIndex(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return ((int) ch - (int) 'A' + 1);
        } else if (ch >= 'a' && ch <= 'z') {
            return ((int) ch - (int) 'a' + 1);
        } else if (ch == '-') {
            return (0);
        } else {
            return (27);
            //this should not be executed.
        }
    }

    public boolean isWord() {
        return (count > 0);
    }

    void markWord() {
        ++count;
    }

    public char getMyChar() {
        return (my_char);
    }

    public TrieNode getParent() {
        return parent;
    }

    public int getCount() {
        return (count);
    }

    TrieNode setSuccessive(char ch) {
        int index = charToIndex(ch);
        if (successes[index] == null) {
            successes[index] = new TrieNode(ch, this);
        }
        return (successes[index]);
    }

    TrieNode getSuccessive(int num) {
        return (successes[num]);
    }

    int getDictOrder() {
        return (dictOrder);
    }

    void setDictOrder(int order) {
        dictOrder = order;
    }
}

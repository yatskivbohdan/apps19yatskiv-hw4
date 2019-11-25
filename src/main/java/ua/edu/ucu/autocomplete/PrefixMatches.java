package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for (int i = 0; i < strings.length; i++) { //String str : strings) {
            String[] words =  strings[i].split("\\s");
            for (int j = 0; j < words.length; j++) {//(String word : words) {
                trie.add(new Tuple(words[j].toLowerCase(), words[j].length()));
                counter ++;
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        else {
            return Collections::emptyIterator;
        }

    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2) {
            ArrayList<String> toReturn = new ArrayList<>();
            int lengths_number = 0;
            int curr_len = 0;
            for (String word : wordsWithPrefix(pref)) {
                if (word.length() >= 3) {
                    if (curr_len != word.length()) {
                        lengths_number++;
                        curr_len = word.length();
                    }
                    if (lengths_number > k) {
                        break;
                    }
                    toReturn.add(word);
                }
            }
            return toReturn;
        }
        else {
            return Collections::emptyIterator;
        }
    }
    public int size() {
        return trie.size();
    }
}

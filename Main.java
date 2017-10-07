import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = null;
        PrintWriter out = null;

        MyHashTable<String, Integer> table = new MyHashTable<>();

        try {

            in = new Scanner(new FileReader("input.txt"));
            out = new PrintWriter(new FileWriter("output.txt"));

            while (in.hasNext()) {
                String word = in.next();
                /* Solving problem with right way of output.*/
                word = word.toLowerCase();
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '.' || word.charAt(i) == ',') { // Check for words like "word," or "word."
                        word = word.substring(0, i);
                    }
                }
                if (word.length() > 1 && word.charAt(word.length() - 2) == '\'') { // Check for words like "word's"
                    if (table.get(word.substring(0, word.length() - 2)) != null) {
                        if (isRightWord(word.substring(0, word.length() - 2)))
                            table.put(word.substring(0, word.length() - 2), table.get(word.substring(0, word.length() - 2)) + 1);
                    } else {
                        table.put(word.substring(0, word.length() - 2), 1);
                    }
                } else if (word.charAt(word.length() - 1) == 's') { // Check for words like "words"
                    if (table.get(word) != null) {
                        if (isRightWord(word))
                            table.put(word, table.get(word) + 1);
                    } else if (table.get(word.substring(0, word.length() - 1)) != null) {
                        if (isRightWord(word.substring(0, word.length() - 1)))
                            table.put(word.substring(0, word.length() - 1), table.get(word.substring(0, word.length() - 1)) + 1);
                    } else {
                        table.put(word, 1);
                        table.put(word.substring(0, word.length() - 1), 1);
                    }
                } else if (word.length() > 2 && word.charAt(word.length() - 3) == '\'') { // Check for words like "they're"
                    if (table.get(word.substring(0, word.length() - 3)) != null) {
                        if (isRightWord(word.substring(0, word.length() - 3)))
                            table.put(word.substring(0, word.length() - 3), table.get(word.substring(0, word.length() - 3)) + 1);
                    } else {
                        table.put(word.substring(0, word.length() - 3), 1);
                    }
                } else {
                    if (isRightWord(word)) {
                        if (table.get(word) != null) {
                            table.put(word, table.get(word) + 1); // If there is already word in the table - make value++
                        } else {
                            table.put(word, 1); // If there if no such word - put it into the table
                        }
                    }
                }
            }

            /* Still solving problem with right way of output.*/
            ArrayList<String> outputList = new ArrayList<>();
            for (MyEntry<String, Integer> entry : table.getEntrySet()) {
                if (entry != null && entry.getValue() > 1) { // Checking if word contains value more than one
                    outputList.add("<\"" + entry.getKey() + "\", " + entry.getValue().toString() + ">");
                }
            }

            /* Cleansing of hash table.*/
            Iterator<MyEntry<String, Integer>> iterator = table.getEntrySet().iterator();
            while (iterator.hasNext()) {
                MyEntry<String, Integer> entry = iterator.next();
                if (entry != null) {
                    table.remove(entry.getKey());
                }
            }
            while (iterator.hasNext()) {
                MyEntry<String, Integer> entry = iterator.next();
                if (entry != null) {
                    table.getEntrySet().remove(entry.getKey());
                }
            }

            /* Sorting to make alphabet order.*/
            outputList.sort(Comparator.naturalOrder());

            /* Final output.*/
            for (int i = 0; i < outputList.size() - 1; i++) {
                out.print(outputList.get(i) + "\n");
            }
            out.print(outputList.get(outputList.size() - 1));

        } finally {

            if (in != null) in.close();
            if (out != null) out.close();

        }

    }

    /** Checks if given word is satisfies conditions of the task.
     *  @return true if word hasn't to be skipped.*/
    private static boolean isRightWord (String s) {
        return !s.equals("a") &&
                !s.equals("in") &&
                !s.equals("at") &&
                !s.equals("to") &&
                !s.equals("on") &&
                !s.equals("not") &&
                !s.equals("for") &&
                !s.equals("is") &&
                !s.equals("are") &&
                !s.equals("am") &&
                !s.equals("has") &&
                !s.equals("I") &&
                !s.equals("we") &&
                !s.equals("you");
    }

}

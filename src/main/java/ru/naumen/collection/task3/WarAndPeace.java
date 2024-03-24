package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    /**
     * <p>LinkedHashMap хранит слово в качестве ключа и количество повторений в качестве значения.
     * Я заглянул в метод addAll() класса TreeSet. В нём используется итератор по добавляемому сету.
     * На моей машине итерирация по ключам LinkedHashMap происходит приблизительно в 5 раз быстрее чем HashMap.
     * <br/>
     * TreeSet получает список всех слов. В компаратор передаётся количество повторений слова.
     * В данном случае сет должен реализовать интерфейс NavigableSet, чтобы иметь возможность перебирать его с конца</p>
     * <p>Сложность алгоритма O(n * log(n)).
     * <br/>
     * Сложность перебора всех значений для добавления O(n).
     * Сложность добавления в TreeSet O(log(n)).
     * Сложность сравнения элементов O(1).</p>
     */
    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();

        Consumer<String> consumer = word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        wordParser.forEachWord(consumer);

        NavigableSet<String> sortedSet = new TreeSet<>(Comparator.comparing(wordCountMap::get));
        sortedSet.addAll(wordCountMap.keySet());

        System.out.println("наиболее используемые (TOP)");
        sortedSet.descendingSet().stream().limit(10).forEach(System.out::println);

        System.out.println("\nнаименее используемые (LAST)");
        sortedSet.stream().limit(10).forEach(System.out::println);
    }
}

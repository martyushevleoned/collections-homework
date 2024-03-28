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
     * <p>Как и в прошлом решении все слова собираются в ключи LinkedHashMap, а значениями являются количества повторений.
     * Сложность заполнения LinkedHashMap O(n).</p>
     * <p>Создаётся компаратор по количеству повторений и метод, который возвражает заданное количество максимальных значений.
     * Сложность метода O(n * log(k+1)), где k - количество максимумов, которое требуется найти (k=10).
     * Коэффициент log(k+1) представляет из себя константу. Следственно сложность метода O(n).</p>
     *
     * <p>Итоговая сложность алгоритма O(n)</p>
     *
     * <p>В методе getMaxSet используется реализация SortedSet т.к.
     * для быстрого поиска масимумов легче держать элементы в упорядоченном виде и выкидывать минимальный за O(1)</p>
     *
     * <p>По тексту задания не требуется вывести слова в каком либо порядке, поэтому TreeSet приводится к Set.
     * Для вывода в порядке убывания количества повторений можно использовать NavigableSet ради метода descendingSet</p>
     */
    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();

        Consumer<String> consumer = word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        wordParser.forEachWord(consumer);

        int countOfResults = 10;
        Comparator<String> mapComparator = Comparator.comparing(wordCountMap::get);

        Set<String> maxSet = getMaxSet(wordCountMap.keySet(), mapComparator, countOfResults);
        Set<String> minSet = getMaxSet(wordCountMap.keySet(), mapComparator.reversed(), countOfResults);

        System.out.println("MOST USED");
        maxSet.forEach(System.out::println);
        System.out.println("\nLEAST USED");
        minSet.forEach(System.out::println);
    }

    private static Set<String> getMaxSet(Set<String> words, Comparator<String> comparator, int count) {
        SortedSet<String> strings = new TreeSet<>(comparator);
        words.forEach(w -> {
            strings.add(w);
            if (strings.size() > count)
                strings.remove(strings.first());
        });
        return strings;
    }
}
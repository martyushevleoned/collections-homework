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
     * <p>Все слова собираются в ключи LinkedHashMap, а значениями являются количества повторений.
     * Сложность заполнения LinkedHashMap O(n).</p>
     *
     *<p>В прошлых решениях в mapComparator лежал баг, связанный с тем,
     * что компаратор возвращал 0, когда у двух слов одинаковое количество повторений.
     * Из-за это TreeSet заменял значение вместо того чтобы добавить его</p>
     *
     * <p>Для нахождения слов с наибольшим/наименьшим количеством повторений созданы 2 TreeSet.
     * Размер данных сетов поддерживается в пределах 10, чтобы не усложнять операцию вставки и удаления.
     * Сложность нахождения наиболее и наименее повторяющихся слов: O(n).</p>
     */
    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();

        Consumer<String> consumer = word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        wordParser.forEachWord(consumer);

        Comparator<String> mapComparator = (o1, o2) -> {
            if (Objects.equals(o1, o2))
                return 0;
            if (wordCountMap.get(o1) > wordCountMap.get(o2))
                return 1;
            return -1;
        };

        TreeSet<String> maxSet = new TreeSet<>(mapComparator);
        TreeSet<String> minSet = new TreeSet<>(mapComparator.reversed());

        int countOfResults = 10;
        for (String w : wordCountMap.keySet()) {
            maxSet.add(w);
            minSet.add(w);

            if (maxSet.size() > countOfResults) {
                maxSet.remove(maxSet.first());
                minSet.remove(minSet.first());
            }
        }

        System.out.println("TOP");
        maxSet.forEach(w -> System.out.println(wordCountMap.get(w) + "\t" + w));
        System.out.println("\nLAST");
        minSet.forEach(w -> System.out.println(wordCountMap.get(w) + "\t" + w));
    }
}
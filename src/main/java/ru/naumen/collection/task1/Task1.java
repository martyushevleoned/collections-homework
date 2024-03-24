package ru.naumen.collection.task1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * <p>Решение принято на паре.
     * Для решения необходимо переопределить методы equals и hashCode в классе User</p>
     *
     * <p>Выбраны сеты тк нас не интересует порядок, а быстрая вставка и получения в данном с случе важны.
     * LinkedHashSet был выбран тк setA необходимо перебрать.
     * HashSet используется только для нахождения пересечения.</p>
     *
     * <p>Сложность алгоритма O(n) тк результат собирается в один цикл,
     * а сложность проверки наличия элемента в сете O(1)</p>
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        Set<User> setA = new LinkedHashSet<>(collA);
        Set<User> setB = new HashSet<>(collB);
        return setA.stream().filter(setB::contains).collect(Collectors.toList());
    }
}

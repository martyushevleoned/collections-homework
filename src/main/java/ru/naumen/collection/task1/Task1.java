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
     * <p>Для решения переопределены методы equals и hashCode в классе User</p>
     *
     * <p>Из второй коллекции за O(n) был создан сет для константной сложности метода contains.
     * Итерация по коллекции A занимает O(n).
     * Доп действий совершать не нужно тк по заданию дубликаты можно не учитывать</p>
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        Set<User> setB = new HashSet<>(collB);
        return collA.stream().filter(setB::contains).collect(Collectors.toList());
    }
}

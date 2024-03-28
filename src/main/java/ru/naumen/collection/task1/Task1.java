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
     * <p>Решение было доработано для ликвидации лишнего O(n) по времени и памяти.</p>
     *
     * <p>Иттерация по collA без создания LinkedHashSet привела бы к тому,
     * что в итоговом листе могли бы содержаться повторения,
     * что не укладывается в математическое понимание множества.
     * Метод contains в collB без преобразования в сет не гарантирует константную сложность.</p>
     *
     * <p>В данном случае была решена проблема возможного добавления дупликатов.
     * Для этого был переписан фильтр в stream, так что при наличии элемента в сете из второй коллекции
     * элемент удаляется из сета.
     * В решении избегается создание LinkedHashSet, что избавлет код от лишнего O(n) по времени и по памяти.</p>
     *
     * <p>Также в новом решении иттерируется коллекция меньшего размера,
     * чтобы уменьшить количество операций contains и remove.</p>
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {

        if (collA.size() < collB.size()) {
            Set<User> setB = new HashSet<>(collB);
            return collA.stream().filter(u -> {
                if (!setB.contains(u))
                    return false;
                setB.remove(u);
                return true;
            }).collect(Collectors.toList());

        } else {
            Set<User> setA = new HashSet<>(collA);
            return collB.stream().filter(u -> {
                if (!setA.contains(u))
                    return false;
                setA.remove(u);
                return true;
            }).collect(Collectors.toList());
        }
    }
}
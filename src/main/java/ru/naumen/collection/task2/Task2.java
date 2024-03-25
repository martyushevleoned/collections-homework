package ru.naumen.collection.task2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    /**
     * <p>Предыдущее решение содержало много кода не относящегося к задаче.
     * В новом решении был создан лишь один класс для описания товара.</p>
     *
     * <p>Для быстрого доступа к сету товаров по билету хорошо подходит Map<Ticket, Set<Product>>.
     * Товары находятся именно по номеру билета тк на его основе переопределены методы equals и hashCode.</p>
     *
     * <p>Сложность получения сета O(1).</p>
     */
    public static void main(String[] args) {
        Map<Ticket, Set<Product>> map = new HashMap<>();

        Ticket ticket = new Ticket("Mark");

        map.put(ticket, new HashSet<>(Set.of(new Product("Water"), new Product("Tea"))));
        map.get(ticket).addAll(Set.of(new Product("Cola"), new Product("Pizza")));

        map.get(ticket).forEach(System.out::println);
    }
}

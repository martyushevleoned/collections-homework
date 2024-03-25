package ru.naumen.collection.task2;

import ru.naumen.collection.task2.products.Product;
import ru.naumen.collection.task2.products.ProductFactory;

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
     * <p>Коллекции находятся в классе {@link Service}.
     * Сет билетов нет причин перебирать, поэтому выбран HashSet.
     * Сеты товаров хранятся в HashMap для быстрого доступа по ключу.
     * В задании сказано, что товары необходимо находить по номеру билета,
     * но ключом в HashMap является билет тк его equals и hashCode переопределены только по номеру билета.
     * При первом добавлении товаров создаеётся LinkedHashSet тк
     * вывод всех товаров клиента предполагает перебор.</p>
     *
     * <p>Уникальность экземпляров класса {@link Product}
     * обеспечивается идентефикатором purchaseId,
     * а уникальность билетов идентефикатором id.
     * Экземпляры обоих классов неизменяемы.
     * Поэтому методы equals и hashCode переопределены только на основе идентефикаторов
     * задающихся случайным числом в конструкторе.</p>
     *
     * <p>Создание объектов типа {@link Product} ограничено protected конструктором
     * и вынесено в класс {@link ProductFactory} для создания схожих по цене и названию товаров.</p>
     *
     * <p>Сложность нахождения товаров по билету O(1) тк это сложность нахождения значения по ключу в HashMap.</p>
     *
     * <p>PS При первой попытке решения данного задания я зацепился за фразу "хранятся отдельно от билета".
     * И соответственно написал хранение товаров в сете, чтобы каждый отдельный товар хранил id билета по которму он был куплен.
     * Сложность нахождения товаров в таком решении составляла O(n).
     * Это решение было отброшено тк это всё же задание по теме "коллекции" и оно не связано с хранением сущностей в БД</p>
     */
    public static void main(String[] args) {

        Ticket t1 = new Ticket("Mark");
        Ticket t2 = new Ticket("Carl");
        Ticket t3 = new Ticket("Bart");

        Service service = new Service();
        service.saveTicket(t1);
        service.saveTicket(t2);
        service.saveTicket(t3);

        ProductFactory productFactory = new ProductFactory();
        service.buyByTicket(t1, productFactory.getFrenchFries());
        service.buyByTicket(t1, productFactory.getCombo());
        service.buyByTicket(t2, productFactory.getSoup());
        service.buyByTicket(t2, productFactory.getSoup());

        System.out.println("\n" + t1);
        service.getProductsByTicket(t1).forEach(System.out::println);
        System.out.println("\n" + t2);
        service.getProductsByTicket(t2).forEach(System.out::println);
        System.out.println("\n" + t3);
        service.getProductsByTicket(t3).forEach(System.out::println);
    }
}

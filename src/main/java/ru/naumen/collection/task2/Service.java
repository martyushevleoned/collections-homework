package ru.naumen.collection.task2;

import ru.naumen.collection.task2.products.Product;

import java.util.*;

public class Service {
    private final Set<Ticket> tickets = new HashSet<>();
    private final Map<Ticket, Set<Product>> products = new HashMap<>();

    public void saveTicket(Ticket ticket) {
        if (tickets.contains(ticket))
            throw new IllegalArgumentException("ticket already saved");
        tickets.add(ticket);
    }

    public void buyByTicket(Ticket ticket, Set<Product> newProducts) {
        if (products.containsKey(ticket))
            products.get(ticket).addAll(newProducts);
        else
            products.put(ticket, new LinkedHashSet<>(newProducts));
    }

    public void buyByTicket(Ticket ticket, Product newProduct) {
        if (products.containsKey(ticket))
            products.get(ticket).add(newProduct);
        else {
            products.put(ticket, new LinkedHashSet<>(Set.of(newProduct)));
        }

    }

    public Set<Product> getProductsByTicket(Ticket ticket){
        return products.getOrDefault(ticket, Set.of());
    }
}

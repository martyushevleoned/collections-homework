package ru.naumen.collection.task2.products;

import java.util.Objects;
import java.util.UUID;

public class Product {

    private final long purchaseId;
    private final int cost;
    private final String name;

    protected Product(int cost, String name) {
        this.purchaseId = UUID.randomUUID().getLeastSignificantBits();
        this.cost = cost;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return purchaseId == product.purchaseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "purchaseId=" + purchaseId +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}

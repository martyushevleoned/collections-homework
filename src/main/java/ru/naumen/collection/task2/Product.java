package ru.naumen.collection.task2;

import java.util.Objects;
import java.util.UUID;

public class Product {

    private final long purchaseId;
    private final String name;

    public Product(String name) {
        this.purchaseId = UUID.randomUUID().getLeastSignificantBits();
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
                ", name='" + name + '\'' +
                '}';
    }
}

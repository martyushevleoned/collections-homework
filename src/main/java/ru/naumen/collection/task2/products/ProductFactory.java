package ru.naumen.collection.task2.products;

import java.util.Set;

public class ProductFactory {
    public Product getCola(){
        return new Product(100, "cola");
    }
    public Product getFrenchFries(){
        return new Product(90, "french fries");
    }

    public Product getBurger(){
        return new Product(120, "burger");
    }

    public Product getTea(){
        return new Product(70, "tea");
    }

    public Product getPasta(){
        return new Product(200, "pasta");
    }

    public Product getSoup(){
        return new Product(200, "soup");
    }

    public Set<Product> getBusinessLunch(){
        return Set.of(
                getTea(),
                getPasta(),
                getSoup()
        );
    }

    public Set<Product> getCombo(){
        return Set.of(
                getCola(),
                getFrenchFries(),
                getBurger()
        );
    }
}

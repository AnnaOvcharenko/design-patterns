package com.example.builder;

/**
 * @author Anna Ovcharenko
 */
public class Product {
    private String name;
    private int price;
    private String description;

    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static class ProductBuilder {
        private String name;
        private int price;
        private String description;

        public ProductBuilder(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public static void main(String[] args) {
        Product product = new ProductBuilder("Example Product", 100)
                .description("This is an example product.")
                .build();

        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Description: " + product.getDescription());
    }
}


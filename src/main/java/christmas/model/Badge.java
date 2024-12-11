package christmas.model;

import java.util.Arrays;

public enum Badge {
    SANTA("산타" ,20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static String getBadge(int favorPrice){
        return Arrays.stream(values())
                .filter(value -> value.price <= favorPrice)
                .findFirst()
                .orElse(Badge.NONE)
                .getName();
    }

    public String getName() {
        return name;
    }
}

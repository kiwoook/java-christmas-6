package christmas.model;

public enum Category {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESERT("디저트"),
    BEVERAGE("음료");

    private final String name;

    Category(String name) {
        this.name = name;
    }
}

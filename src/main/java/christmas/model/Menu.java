package christmas.model;

import christmas.exception.CustomIllegalArgumentException;
import java.util.Arrays;
import java.util.Set;

public enum Menu {
    SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    SALAD("시저샐러드", 8_000, Category.APPETIZER),
    STAKE("티본스테이크", 55_000, Category.MAIN),
    BBQ("바비큐립", 54_000, Category.MAIN),
    PASTA1("해산물파스타", 35_000, Category.MAIN),
    PASTA2("크리스마스파스타", 25_000, Category.MAIN),
    CAKE("초코케이크", 15_000, Category.DESERT),
    ICECREAM("아이스크림", 5_000, Category.DESERT),
    COLA("제로콜라", 3_000, Category.BEVERAGE),
    WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu from(String input) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER));
    }

    public static boolean checkOnlyBeverage(Set<Menu> menus) {
        for (Menu menu : menus) {
            if (menu.category != Category.BEVERAGE) {
                return false;
            }
        }
        return true;
    }

    public boolean isCategory(Category category) {
        return this.category == category;
    }

    public int getTotalPrice(int quantity) {
        return this.price * quantity;
    }

    public String getName() {
        return name;
    }
}

package christmas.model;

import static christmas.utils.Constants.ENTER;

import christmas.exception.CustomIllegalArgumentException;
import christmas.utils.StringUtils;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class Orders {

    private static final int DISCOUNT = 2023;
    private final Map<Menu, Integer> map;

    private Orders(Map<Menu, Integer> map) {
        this.map = map;
    }

    public static Orders from(String input) {
        Map<Menu, Integer> map = new LinkedHashMap<>();
        List<String> list = Arrays.stream(StringUtils.split(",", input, null, ErrorMessage.INVALID_ORDER)).toList();

        for (String value : list) {
            addOrder(value, map);
        }

        validOrder(map);

        return new Orders(map);
    }

    private static void validOrder(Map<Menu, Integer> map) {
        if (map.size() > 20) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }

        if (Menu.checkOnlyBeverage(map.keySet())) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static void addOrder(String value, Map<Menu, Integer> map) {
        String[] split = StringUtils.split("-", value, 2, ErrorMessage.INVALID_ORDER);

        Menu menu = Menu.from(split[0]);
        int quantity = parseQuantity(split[1]);

        validQuantity(quantity);

        if (map.containsKey(menu)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }

        map.put(menu, quantity);
    }

    private static int parseQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static void validQuantity(int quantity) {
        if (quantity <= 0) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    public int discountByWeekEnd() {
        return discountByCategory(Category.MAIN);
    }

    public int discountByWeekDay() {
        return discountByCategory(Category.DESERT);
    }

    private int discountByCategory(Category category) {
        int discount = 0;
        for (Entry<Menu, Integer> entry : map.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            if (menu.isCategory(category)) {
                discount += DISCOUNT * quantity;
            }
        }
        return discount;
    }


    public int sum() {
        return map.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getTotalPrice(entry.getValue()))
                .sum();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(ENTER);

        for (Entry<Menu, Integer> entry : map.entrySet()) {
            String name = entry.getKey().getName();
            String quantity = String.valueOf(entry.getValue());

            joiner.add(name + " " + quantity + "개");
        }

        return joiner.toString();
    }
}

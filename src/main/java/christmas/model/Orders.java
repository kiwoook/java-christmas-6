package christmas.model;

import christmas.exception.CustomIllegalArgumentException;
import christmas.utils.StringUtils;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Orders {

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
}

package christmas.model;

import static christmas.utils.Constants.ENTER;

import christmas.utils.StringUtils;
import java.util.StringJoiner;

public class Result {

    private Orders orders;
    private Day day;
    private int discount;

    private String toOrderMenu() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<주문 메뉴>")
                .add(orders.toString())
                .toString();
    }

    private String totalOrder() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<할인 전 총주문 금액>")
                .add(StringUtils.numberFormat(orders.sum()))
                .toString();
    }

    private String offerEvent() {
        StringJoiner joiner = new StringJoiner(ENTER)
                .add(ENTER + "<증정 메뉴>");

        if (orders.sum() >= 120000) {
            discount += Menu.CHAMPAGNE.getTotalPrice(1);
            return joiner.add("샴페인 1개").toString();
        }

        return joiner.add("없음").toString();
    }


}

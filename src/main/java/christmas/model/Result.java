package christmas.model;

import static christmas.utils.Constants.ENTER;

import christmas.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Result {

    private static final int MIN_BENEFIT_PRICE = 1_0000;
    private static final int MIN_GIFT_PRICE = 12_0000;

    private final Orders orders;
    private final Day day;
    private int discount;

    public Result(Orders orders, Day day) {
        this.orders = orders;
        this.day = day;
    }

    public static Result of(Orders orders, Day day) {
        return new Result(orders, day);
    }

    public String toResult() {
        return new StringJoiner(ENTER)
                .add(toOrderMenu())
                .add(toTotalOrder())
                .add(toGiftEvent())
                .add(toBenefit())
                .add(totalDiscount())
                .add(toFinalPrice())
                .add(toBadge()).toString();
    }

    private String toOrderMenu() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<주문 메뉴>")
                .add(orders.toString())
                .toString();
    }

    private String toTotalOrder() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<할인 전 총주문 금액>")
                .add(toPrice(orders.sum()))
                .toString();
    }

    private String toGiftEvent() {
        StringJoiner joiner = new StringJoiner(ENTER)
                .add(ENTER + "<증정 메뉴>");

        if (orders.sum() >= MIN_GIFT_PRICE) {
            return joiner.add("샴페인 1개").toString();
        }

        return joiner.add("없음").toString();
    }

    public String toBenefit() {
        StringJoiner joiner = new StringJoiner(ENTER).add(ENTER + "<혜택 내역>");

        if (orders.sum() < MIN_BENEFIT_PRICE) {
            return joiner.add("없음").toString();
        }

        List<String> discountMessages = new ArrayList<>();
        discountEvent(discountMessages);

        return toBenefit(joiner, discountMessages);
    }

    private void discountEvent(List<String> discountMessages) {
        discountDDayEvent(discountMessages);
        discountByWeekOfDay(discountMessages);
        discountSpecialDay(discountMessages);
        discountGiftEvent(discountMessages);
    }

    private String toBenefit(StringJoiner joiner, List<String> discountMessages) {
        if (discountMessages.isEmpty()) {
            return joiner.add("없음").toString();
        }

        for (String discountMessage : discountMessages) {
            joiner.add(discountMessage);
        }

        return joiner.toString();
    }

    private void discountDDayEvent(List<String> discountMessages) {
        int dDayDiscount = day.dDayDiscount();
        if (dDayDiscount > 0) {
            discount += dDayDiscount;
            discountMessages.add("크리스마스 디데이 할인: -" + toPrice(dDayDiscount));
        }
    }

    private void discountGiftEvent(List<String> discountMessages) {
        if (orders.sum() >= MIN_GIFT_PRICE) {
            int champagnePrice = Menu.CHAMPAGNE.getTotalPrice(1);
            discount += champagnePrice;
            discountMessages.add("증정 이벤트: -" + toPrice(champagnePrice));
        }
    }

    private void discountSpecialDay(List<String> discountMessages) {
        int specialDayDiscount = day.discountSpecialDay();
        if (specialDayDiscount > 0) {
            discount += specialDayDiscount;
            discountMessages.add("특별 할인: -" + toPrice(specialDayDiscount));
        }
    }

    private void discountByWeekOfDay(List<String> discountMessages) {
        if (!day.isWeekend()) {
            int discountByWeekDay = orders.discountByWeekDay();
            discount += discountByWeekDay;
            discountMessages.add("평일 할인: -" + toPrice(discountByWeekDay));
        }

        if (day.isWeekend()) {
            int discountByWeekEnd = orders.discountByWeekEnd();
            discount += discountByWeekEnd;
            discountMessages.add("주말 할인: -" + toPrice(discountByWeekEnd));
        }
    }

    private String totalDiscount() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<총혜택 금액>")
                .add("-" + toPrice(discount)).toString();
    }

    private String toFinalPrice() {
        int toFinalPrice = orders.sum() - discount;

        if (orders.sum() >= MIN_GIFT_PRICE) {
            toFinalPrice += Menu.CHAMPAGNE.getTotalPrice(1);
        }

        return new StringJoiner(ENTER)
                .add(ENTER + "<할인 후 예상 결제 금액>")
                .add(toPrice(toFinalPrice))
                .toString();
    }

    private String toBadge() {
        return new StringJoiner(ENTER)
                .add(ENTER + "<12월 이벤트 배지>")
                .add(Badge.getBadge(discount))
                .toString();
    }


    private String toPrice(int value) {
        return StringUtils.numberFormat(value) + "원";
    }

}

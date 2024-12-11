package christmas.view;

import christmas.model.Day;

public class OutputViewer {

    private static final String ERROR_SIGN = "[ERROR] ";

    public void printError(Exception e) {
        System.out.println(ERROR_SIGN + e.getMessage());
    }

    public void printResult(Day day, String result) {
        System.out.println("12월" + day.toString() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println(result);
    }
}
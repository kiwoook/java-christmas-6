package christmas.controller;

import christmas.model.Day;
import christmas.model.Orders;
import christmas.model.Result;
import christmas.utils.RecoveryUtils;
import christmas.view.InputViewer;
import christmas.view.OutputViewer;

public class ChristmasController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;

    public ChristmasController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    public void execute() {
        Day day = getDay();
        Orders orders = getOrder();

        Result result = Result.of(orders, day);

        outputViewer.printResult(day, result.toResult());
    }

    private Day getDay() {
        return RecoveryUtils.executeWithRetry(inputViewer::promptDay, Day::from);
    }

    private Orders getOrder() {
        return RecoveryUtils.executeWithRetry(inputViewer::promptOrder, Orders::from);
    }

}

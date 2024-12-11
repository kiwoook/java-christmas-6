package christmas.controller;

import christmas.model.Day;
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

    private void getDay() {
        Day day = RecoveryUtils.executeWithRetry(inputViewer::promptDay, Day::from);
    }

}

package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputViewer;
import christmas.view.OutputViewer;

public class Application {
    public static void main(String[] args) {
        InputViewer inputViewer = new InputViewer();
        OutputViewer outputViewer = new OutputViewer();

        ChristmasController christmasController = new ChristmasController(inputViewer, outputViewer);

        christmasController.execute();
    }
}

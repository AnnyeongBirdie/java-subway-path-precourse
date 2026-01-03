package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        SubwayController controller = new SubwayController(inputView, outputView);
        controller.run();
    }
}
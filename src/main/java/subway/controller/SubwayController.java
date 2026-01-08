package subway.controller;

import subway.domain.PathResult;
import subway.service.PathService;
import subway.view.InputView;
import subway.view.OutputView;
import subway.domain.SearchType;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PathService pathService;

    public SubwayController(InputView inputView, OutputView outputView, PathService pathService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pathService = pathService;
    }

    public void run() {
        while (true) {
            outputView.showMain();
            outputView.askMainMenuChoice();

            String choice = inputView.readLine();

            if (choice.equalsIgnoreCase("Q")) {
                return;
            }
            handleMainMenuChoice(choice);
        }
    }

    private void handleMainMenuChoice(String choice) {
        if (choice.equals("1")) {
            handlePathSearchMenu();
            return;
        }
        outputView.printError("메뉴를 다시 선택해주세요.");
    }

    private void handlePathSearchMenu() {
        while (true) {
            outputView.showSearchTypeMenu();
            outputView.askSearchTypeChoice();

            String typeChoice = inputView.readLine();

            if (typeChoice.equalsIgnoreCase("B")) {
                return;
            }

            if (typeChoice.equals("1") || typeChoice.equals("2")) {
                SearchType searchType = SearchType.from(typeChoice);

                outputView.askDeparture();
                String departure = inputView.readLine().trim();

                outputView.askArrival();
                String arrival = inputView.readLine().trim();

                if (departure.equals(arrival)) {
                    outputView.printError("출발역과 도착역이 동일합니다.");
                    continue;
                }

                try {
                    PathResult result = pathService.findPath(departure, arrival, searchType);
                    outputView.printPathResult(result);
                } catch (IllegalArgumentException e) {
                    outputView.printError(e.getMessage());
                }

                return;
            }
            outputView.printError("경로 기준을 다시 선택해주세요.");
        }
    }
}
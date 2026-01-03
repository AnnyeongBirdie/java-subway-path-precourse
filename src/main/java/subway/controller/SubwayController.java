package subway.controller;

import subway.view.InputView;
import subway.view.OutputView;
import subway.domain.SearchType;

public class SubwayController {
    private final InputView in;
    private final OutputView out;

    public SubwayController(InputView in, OutputView out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (true) {
            out.showMain();
            out.askMainMenuChoice();

            String choice = in.readLine().trim();

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
        out.printError("메뉴를 다시 선택해주세요.");
    }

    private void handlePathSearchMenu() {
        while (true) {
            out.showSearchTypeMenu();
            out.askSearchTypeChoice();

            String typeChoice = in.readLine().trim();

            if (typeChoice.equalsIgnoreCase("B")) {
                return;
            }

            if (typeChoice.equals("1") || typeChoice.equals("2")) {
                SearchType searchType = SearchType.DISTANCE;

                if (typeChoice.equals("2")) {
                    searchType = SearchType.TIME;
                }

                out.askDeparture();
                String departure = in.readLine().trim();

                out.askArrival();
                String arrival = in.readLine().trim();

                if (departure.equals(arrival)) {
                    out.printError("출발역과 도착역이 동일합니다.");
                    continue;
                }

                //TO Do: 임시 메세지는 나중에 수정
                out.printError("임시 메세지 : " + departure + " " + arrival + " " + "(기준 " + searchType + ")");
                return;
            }
            out.printError("경로 기준을 다시 선택해주세요.");
        }
    }
}
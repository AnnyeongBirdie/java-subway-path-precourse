package subway.view;

import java.util.List;

public class OutputView {

    public void showMain() {
        System.out.println("## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
        System.out.println();
    }

    public void askMainMenuChoice() {
        System.out.println("## 원하는 기능을 선택하세요.");
    }

    public void showSearchTypeMenu() {
        System.out.println();
        System.out.println("## 경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
        System.out.println();
    }

    public void askSearchTypeChoice() {
        System.out.println("## 원하는 기능을 선택하세요.");
        System.out.println();
    }

    public void askDeparture() {
        System.out.println();
        System.out.println("## 출발역을 입력하세요.");
    }

    public void askArrival() {
        System.out.println();
        System.out.println("## 도착역을 입력하세요.");
    }

    public void printError(String msg) {
        System.out.println();
        System.out.println("[ERROR] " + msg);
        System.out.println();
    }

    //TO DO: path result 만들고 돌아와서 나중에 수정
    public void printPathResult(int totalDistanceKm, int totalTimeMin, List<String> stations) {
        System.out.println();
        System.out.println("## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + totalDistanceKm + "km");
        System.out.println("[INFO] 총 소요 시간: " + totalTimeMin + "분");
        System.out.println("[INFO] ---");

        for (String station : stations) {
            System.out.println("[INFO] " + station);
        }
        System.out.println();
    }
}

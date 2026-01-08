package subway.domain;

public class DataInitializer {

    public static void init() {
        StationRepository.deleteAll();
        LineRepository.deleteAll();

        // 역 생성
        Station kyodae = new Station("교대역");
        Station gangnam = new Station("강남역");
        Station yeoksam = new Station("역삼역");
        Station seolleung = new Station("선릉역"); // ✅ 2-step 테스트용 추가
        Station nambuterminal = new Station("남부터미널역");
        Station yangjae = new Station("양재역");
        Station yangjaesimineuisoop = new Station("양재시민의숲역");
        Station maebong = new Station("매봉역");

        // 역 저장
        StationRepository.addStation(kyodae);
        StationRepository.addStation(gangnam);
        StationRepository.addStation(yeoksam);
        StationRepository.addStation(seolleung); // ✅
        StationRepository.addStation(nambuterminal);
        StationRepository.addStation(yangjae);
        StationRepository.addStation(yangjaesimineuisoop);
        StationRepository.addStation(maebong);

        // 노선 생성 + 저장
        Line line2 = new Line("2호선");
        LineRepository.addLine(line2);

        Line line3 = new Line("3호선");
        LineRepository.addLine(line3);

        Line lineShinBundang = new Line("신분당선");
        LineRepository.addLine(lineShinBundang);

        // 구간 연결 (✅ 양방향 등록)
        addBothDirections(line2, kyodae, gangnam, 2, 3);
        addBothDirections(line2, gangnam, yeoksam, 2, 3);
        addBothDirections(line2, yeoksam, seolleung, 2, 3); // ✅ 강남 -> 역삼 -> 선릉 2-step 가능

        addBothDirections(line3, kyodae, nambuterminal, 3, 2);
        addBothDirections(line3, nambuterminal, yangjae, 6, 5);
        addBothDirections(line3, yangjae, maebong, 1, 1);

        addBothDirections(lineShinBundang, gangnam, yangjae, 2, 8);
        addBothDirections(lineShinBundang, yangjae, yangjaesimineuisoop, 10, 3);
    }

    private static void addBothDirections(Line line, Station a, Station b, int distance, int duration) {
        line.addSection(new Section(a, b, distance, duration));
        line.addSection(new Section(b, a, distance, duration));
    }
}

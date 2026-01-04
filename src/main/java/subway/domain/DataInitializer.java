package subway.domain;

public class DataInitializer {
    public static void init() {
        StationRepository.deleteAll();
        LineRepository.deleteAll();

        //역 생성
        Station kyodae = new Station("교대역");
        Station gangnam = new Station("강남역");
        Station yeoksam = new Station("역삼역");
        Station nambuterminal = new Station("남부터미널역");
        Station yangjae = new Station("양재역");
        Station yangjaesimineuisoop = new Station("양재시민의숲역");
        Station maebong = new Station("매봉역");

        StationRepository.addStation(kyodae);
        StationRepository.addStation(gangnam);
        StationRepository.addStation(yeoksam);
        StationRepository.addStation(nambuterminal);
        StationRepository.addStation(yangjae);
        StationRepository.addStation(yangjaesimineuisoop);
        StationRepository.addStation(maebong);

        //노선 생성
        Line line2 = new Line("2호선");
        LineRepository.addLine(line2);

        Line line3 = new Line("3호선");
        LineRepository.addLine(line3);

        Line lineShinBundang = new Line("신분당선");
        LineRepository.addLine(lineShinBundang);

        //구간 연결 (line 이 section 을 가졌을 때)
        line2.addSection(new Section(kyodae, gangnam, 2, 3));
        line2.addSection(new Section(gangnam, yeoksam, 2, 3));
        line3.addSection(new Section(kyodae, nambuterminal, 3, 2));
        line3.addSection(new Section(nambuterminal, yangjae, 6, 5));
        line3.addSection(new Section(yangjae, maebong, 1, 1));
        lineShinBundang.addSection(new Section(gangnam, yangjae, 2, 8));
        lineShinBundang.addSection(new Section(yangjae, yangjaesimineuisoop, 10, 3));
    }
}

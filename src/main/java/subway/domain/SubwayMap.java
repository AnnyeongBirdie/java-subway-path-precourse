package subway.domain;

import static javax.management.remote.JMXConnectorFactory.connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwayMap {
    Map<Station, List<Section>> adjacencyMap = new HashMap<>();

    public SubwayMap(List<Line> Lines){
        for (Line line : Lines) {
            for (Section section : line.getSections()) {
                connect(section);
            }
        }
    }

    private void connect(Section section) {
        Station from = section.getUpStation();
        adjacencyMap
                .computeIfAbsent(from, k -> new ArrayList<>())
                .add(section);
    }

    public List<Section> getConnectedSections(Station station){
        return List.copyOf(adjacencyMap.getOrDefault(station, List.of()));
    }

    public List<Section> getConnectedSectionsByName(String stationName) {
        Station station = StationRepository.stations().stream()
                .filter(s -> s.getName().equals(stationName))
                .findFirst()
                .orElse(null);

        if (station == null) {
            return List.of();
        }
        return getConnectedSections(station); // 기존 메서드 재사용
    }

}

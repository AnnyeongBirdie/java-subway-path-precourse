package subway.domain;

import java.util.List;

public class PathResult {
    private final List<String> stations; // 경로 역 이름들 (출발..도착)
    private final int totalDistance;
    private final int totalDuration;

    public PathResult(List<String> stations, int totalDistance, int totalDuration) {
        this.stations = List.copyOf(stations);
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    public List<String> getStations() {
        return stations;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalDuration() {
        return totalDuration;
    }
}

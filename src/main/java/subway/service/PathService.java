package subway.service;

import subway.domain.LineRepository;
import subway.domain.SubwayMap;

public class PathService {

    private final SubwayMap subwayMap;

    public PathService() {
        this.subwayMap = new SubwayMap(LineRepository.lines());
    }
}

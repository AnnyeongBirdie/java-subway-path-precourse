package subway.domain;

import java.util.List;

public class OneStepResult {

    private final boolean reached;
    private final String arrivedStationName;     // reached=true 일 때 의미 있음
    private final Section usedSection;           // reached=true 일 때 의미 있음
    private final List<String> nextStationNames; // reached=false 일 때 후보 목록

    private OneStepResult(boolean reached, String arrivedStationName, Section usedSection, List<String> nextStationNames) {
        this.reached = reached;
        this.arrivedStationName = arrivedStationName;
        this.usedSection = usedSection;
        this.nextStationNames = nextStationNames;
    }

    public static OneStepResult reached(String arrivedStationName, Section usedSection) {
        return new OneStepResult(true, arrivedStationName, usedSection, List.of());
    }

    public static OneStepResult nextCandidates(List<String> nextStationNames) {
        return new OneStepResult(false, null, null, List.copyOf(nextStationNames));
    }

    public boolean isReached() {
        return reached;
    }

    public String getArrivedStationName() {
        return arrivedStationName;
    }

    public Section getUsedSection() {
        return usedSection;
    }

    public List<String> getNextStationNames() {
        return nextStationNames;
    }
}

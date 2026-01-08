package subway.domain;

import java.util.List;

public class TwoStepResult {
    private final boolean reached;
    private final int steps;              // 0, 1, 2 (0은 not reached)
    private final String viaStationName;  // 2-step일 때 경유역
    private final List<String> step1Candidates;

    private TwoStepResult(boolean reached, int steps, String viaStationName, List<String> step1Candidates) {
        this.reached = reached;
        this.steps = steps;
        this.viaStationName = viaStationName;
        this.step1Candidates = List.copyOf(step1Candidates);
    }

    public static TwoStepResult reachedInOneStep(List<String> step1Candidates) {
        return new TwoStepResult(true, 1, null, step1Candidates);
    }

    public static TwoStepResult reachedInTwoSteps(String viaStationName, List<String> step1Candidates) {
        return new TwoStepResult(true, 2, viaStationName, step1Candidates);
    }

    public static TwoStepResult notReached(List<String> step1Candidates) {
        return new TwoStepResult(false, 0, null, step1Candidates);
    }

    public boolean isReached() { return reached; }
    public int getSteps() { return steps; }
    public String getViaStationName() { return viaStationName; }
    public List<String> getStep1Candidates() { return step1Candidates; }
}

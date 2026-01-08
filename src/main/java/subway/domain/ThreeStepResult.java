package subway.domain;

public class ThreeStepResult {
    private final boolean reached;
    private final int steps; // reached=true일 때 1~3

    private ThreeStepResult(boolean reached, int steps) {
        this.reached = reached;
        this.steps = steps;
    }

    public static ThreeStepResult reached(int steps) {
        return new ThreeStepResult(true, steps);
    }

    public static ThreeStepResult notReached() {
        return new ThreeStepResult(false, 0);
    }

    public boolean isReached() {
        return reached;
    }

    public int getSteps() {
        return steps;
    }
}

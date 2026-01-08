package subway.domain;

public enum SearchType {
    DISTANCE,
    TIME;

    public static SearchType from(String input) {
        if (input.equals("1")){
            return DISTANCE;
        }
        if (input.equals("2")){
            return TIME;
        }
        throw new IllegalArgumentException("경로 기준을 다시 선택해주세요");
    }
}
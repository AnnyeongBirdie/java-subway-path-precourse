package subway.service;

import java.util.*;
import subway.domain.*;

public class PathService {

    private final SubwayMap subwayMap;

    public PathService() {
        this.subwayMap = new SubwayMap(LineRepository.lines());
    }

    public PathResult findPath(String departureName, String arrivalName, SearchType type) {
        log("findPath 시작: %s -> %s (%s)", departureName, arrivalName, type);

        // 유효성: 역 존재 확인
        requireStationExists(departureName);
        requireStationExists(arrivalName);

        // Dijkstra 자료구조
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>(); // 경로 복원용 parent
        Map<String, Section> prevEdge = new HashMap<>(); // 합산(거리/시간)용

        for (Station s : StationRepository.stations()) {
            dist.put(s.getName(), Integer.MAX_VALUE);
        }
        dist.put(departureName, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.add(new Node(departureName, 0));

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited.contains(cur.name)) {
                continue;
            }
            visited.add(cur.name);

            log("POP: %s cost=%d", cur.name, cur.cost);

            if (cur.name.equals(arrivalName)) {
                break; // 도착하면 종료(다익스트라 성질)
            }

            List<Section> edges = subwayMap.getConnectedSectionsByName(cur.name);

            for (Section edge : edges) {
                String next = edge.getDownStation().getName();
                int w = weight(edge, type);

                if (dist.get(cur.name) == Integer.MAX_VALUE) {
                    continue;
                }

                int newCost = dist.get(cur.name) + w;
                int oldCost = dist.getOrDefault(next, Integer.MAX_VALUE);

                if (newCost < oldCost) {
                    dist.put(next, newCost);
                    prev.put(next, cur.name);
                    prevEdge.put(next, edge);
                    pq.add(new Node(next, newCost));

                    log("RELAX: %s -> %s (w=%d) new=%d", cur.name, next, w, newCost);
                }
            }
        }

        if (!prev.containsKey(arrivalName) && !departureName.equals(arrivalName)) {
            throw new IllegalArgumentException("[ERROR] 경로를 찾을 수 없습니다.");
        }

        List<String> pathStations = rebuildPath(departureName, arrivalName, prev);

        // 거리/시간은 최종 경로 기준으로 둘 다 합산 (출력에 필요)
        Totals totals = sumTotals(pathStations, prevEdge);

        log("RESULT path=%s dist=%d dur=%d", pathStations, totals.totalDistance, totals.totalDuration);

        return new PathResult(pathStations, totals.totalDistance, totals.totalDuration);
    }

    private int weight(Section edge, SearchType type) {
        if (type == SearchType.DISTANCE) {
            return edge.getDistance();
        }
        return edge.getDuration();
    }

    private void requireStationExists(String stationName) {
        boolean exists = StationRepository.stations().stream()
                .anyMatch(s -> s.getName().equals(stationName));
        if (!exists) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 역입니다.");
        }
    }

    private List<String> rebuildPath(String start, String end, Map<String, String> prev) {
        List<String> reversed = new ArrayList<>();
        String cur = end;
        reversed.add(cur);

        while (!cur.equals(start)) {
            cur = prev.get(cur);
            if (cur == null) {
                break;
            }
            reversed.add(cur);
        }

        Collections.reverse(reversed);
        return reversed;
    }

    private Totals sumTotals(List<String> pathStations, Map<String, Section> prevEdge) {
        int distSum = 0;
        int durSum = 0;

        // prevEdge는 "각 노드에 도달한 마지막 간선"이므로,
        // 경로 상에서 두 번째 역부터 간선을 누적하면 됨.
        for (int i = 1; i < pathStations.size(); i++) {
            String node = pathStations.get(i);
            Section e = prevEdge.get(node);
            if (e != null) {
                distSum += e.getDistance();
                durSum += e.getDuration();
            }
        }

        return new Totals(distSum, durSum);
    }

    private static final boolean DEBUG = false;

    private void log(String format, Object... args) {
        if (!DEBUG) return;
        System.out.println("[DEBUG] " + String.format(format, args));
    }

    private static class Node {
        private final String name;
        private final int cost;

        private Node(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    private static class Totals {
        private final int totalDistance;
        private final int totalDuration;

        private Totals(int totalDistance, int totalDuration) {
            this.totalDistance = totalDistance;
            this.totalDuration = totalDuration;
        }
    }
}

# 구현 완료
* 메인 메뉴 출력 및 종료(Q) 처리
* 경로 기준 선택(최단 거리 / 최소 시간)
* 잘못된 입력 시 에러 출력 및 재입력
* 출발역 = 도착역 예외 처리
* 역 / 노선 / 구간 초기 데이터 설정
* 경로 계산 로직(PathService) 구현
  * Dijkstra 알고리즘 기반
  * 경로 기준(SearchType)에 따라 거리 / 시간 가중치 분기
* 경로 탐색 결과 출력
  * 전체 경로(역 목록)
  * 총 거리 / 총 소요 시간

# 구현 예정
* 입력값 검증 로직 정리 (validator)
* 테스트 코드 추가

# 설계 및 구현 메모

## 경로 탐색 결과 모델
* 초기 단계에서 탐색 흐름 검증을 위해 OneStepResult, TwoStepResult, ThreeStepResult를 임시 도입

## 현재는 최종 결과 모델 PathResult로 통합 완료
* 경로(역 목록)
* 총 거리
* 총 소요 시간

(실험 단계)
OneStepResult / TwoStepResult / ThreeStepResult
↓
(최종)
PathResult

## 경로 탐색 방식
* SubwayMap 기반 인접 구간 탐색
* 방문 여부(visited)를 활용해 순환 경로 방지
* Dijkstra 특성상 도착 노드 최초 도달 시 탐색 종료

## 구간 방향성 처리
* 현재는 경로 탐색 흐름 검증을 위해
* 구간을 양방향으로 등록
* DataInitializer에 헬퍼 메서드를 추가하여 임시 처리
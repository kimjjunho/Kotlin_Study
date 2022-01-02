# MVI

## MVI란 무엇인가?

- Model - View - Intent의 약자
  - Model - 모델은 상태를 나타낸다. MVI의 모델은 아키텍쳐의 다른 레이어와의 단방향 데이터 흐름을 보장하기 위해 변경이 불가능 해야 한다.
  - View — View를 나타내며 하나 이상의 Activity나 Fragment로 구현됩니다.
  - Intent — 사용자 또는 앱내 발생하는 Action을 나타냅니다. 모든 Action에 대해 View는 Intent를 수신합니다. Presenter는 Intent를 관찰하고 Model은 새로운 상태로 변환한다.

- MVI는 Cycle.js프레임워크의 단방향성과 Cycle Nature에서 영감을 받은 안드로이드를 위한 최신 아키텍처 패턴 중 하나이다
- MVI는 먼 친척인 MVC, MVP, MVVM과 매우 다른 방식으로 작동한다



## Models

- 다른 아키텍처 패턴에서 모델은 데이터베이스나 API 같은 Backend 와의 연결고리 역할을 하는 계층으로 구현된다. 하지만 MVI에서 모델은 앱의 상태를 나타낸다.

- MVI로 해결할 수 있는 이슈

  - Multiple inputs: MVP와 MVVM에서, Presenter와 ViewModel은 많은 수의 입출력을 관리해야 하는 경우가 많다. 이건 많은 백그라운드 테스크가 있는 큰 앱에서는 문제를 일으킬 수 있다.

  - Multiple states: MVP와 MVVM에서, 비즈니스로직과 View는 언제든 다른 상태를 가질 수 있다. 개발자는 자주 Observable 과 Observer 콜백의 상태를 동기화시킨다. 하지만 이건 행위의 충돌을 야기할 수 있다.

    *이런 이슈를 해결하기 위해서 모델이 데이터가 아닌 상태를 나타내도록 한다.

## 출처

- https://jaehochoe.medium.com/번역-안드로이드를-위한-mvi-model-view-intent-아키텍쳐-튜토리얼-시작하기-165bda9dfbe7
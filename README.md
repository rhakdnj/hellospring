## Section 02.

### 관심사의 분리

**관심사**는 변경의 관점으로 바라보면 좋습니다. 코드는 언젠가 변경됩니다.

만약에 환율 가져오는 방식이 API에서 DB로, 혹은 계산하는 방식이 혹은 유효한 시간 계산하는 방식이 변경될 수 있습니다.
중요한 것은 하나의 메서드 내에서 변경되는 이유도 다르고 변경되는 시점이 다르다는 점 입니다.

### 인터페이스 도입

Interface를 도입하면 인터페이스를 의존하는 코드는 변경이 되지 않고 확장될 수 있다.

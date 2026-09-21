# TODO REST API SERVER

## 1. 실행 방법

```
# 빌드
./gradlew clean build

# 서버 실행
./gradlew bootRun
```

## 2. API 명세

| 기능 | Method | Endpoint | 설명 | 성공 Status |
| :--- | :--- | :--- | :--- | :--- |
| **할 일 생성** | `POST` | `/api/v1/todo` | 새로운 할 일을 생성합니다. (제목 필수, 1~30자) | `201 Created` |
| **목록 조회** | `GET` | `/api/v1/todo` | 페이징(`Pageable`)이 적용된 할 일 목록을 조회합니다. | `200 OK` |
| **단건 조회** | `GET` | `/api/v1/todo/{id}` | 특정 ID의 할 일을 단건 조회합니다. | `200 OK` |
| **제목 수정** | `PATCH` | `/api/v1/todo/{id}` | 특정 ID의 할 일 제목을 수정합니다. (1~30자) | `200 OK` |
| **완료 처리** | `PATCH` | `/api/v1/todo/{id}/complete` | 특정 ID의 할 일을 완료 상태로 변경합니다. | `200 OK` |
| **미완료 처리**| `PATCH` | `/api/v1/todo/{id}/incomplete` | 특정 ID의 할 일을 미완료 상태로 변경합니다. | `200 OK` |
| **할 일 삭제** | `DELETE` | `/api/v1/todo/{id}` | 특정 ID의 할 일을 삭제합니다. | `200 OK` |


### 공통 오류 응답 형식

400, 404 등 발생시 해당하는 형태의 JSON 응답을 반환합니다
```
# 예시

{
  "success": false,
  "data": null,
  "time": "2026-09-21T14:10:36.8922341",
  "error": {
    "code": 400,
    "message": "이름은 1자이상 20자 이내여야 합니다."
  }
}
```

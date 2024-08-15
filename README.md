# 주특기 입문 주차 개인과제

🏁 **Goal:  "나만의 일정 관리 앱 서버 만들기"**


## 🆘 API 명세서 - 나만의 일정 관리 앱 서버

| 기능 | Method | URL | Request | Response |
| --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedule | 요청 body | 등록 정보 |
| 특정 일정 조회 | GET | /api/schedule/{scheduleid} | 요청 Param | 단일 응답 정보 |
| 전체 일정 조회 | GET | /api/schedule | 요청 Param | 전체 응답 정보 |
| 특정 일정 변경 | PUT | /api/schedule/{scheduleid} | 요청 body | 변경 정보 |
| 특정 일정 삭제 | DELETE | /api/schedule/{scheduleid} | 요청 param |  |

## ERD
![erd](https://github.com/user-attachments/assets/d4f07981-56ea-4bae-bee1-21af76f4f3e5)

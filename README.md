# 정산 프로젝트

#### 📅 2024.06.19 ~ 2024.07.16

## 💡 프로젝트

<h2 align="center">대용량의 동영상 & 광고 데이터에 대한 통계 및 정산 시스템</h2>

## 📚 기술 스택
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<br>
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/Github Actions-2088FF?style=for-the-badge&logo=Github Actions&logoColor=white"><img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/AWS Route 53-8C4FFF?style=for-the-badge&logo=Spring Boot&logoColor=white">

## 🫧 주요 기능
| **기능**          | **세부 기능**                                          |
|-------------|------------------------------------------------|
| **유저 관리**       | 👤 **회원 가입**<br/> 🔐 **로그인**<br/> 🚪 **로그아웃**  |
| **동영상 & 광고 관리** | 🎥 **동영상** : 등록, 재생, 정지<br/> 📢 **광고** : 등록, 배치 |
| **통계 기능**       | 📊 **동영상 및 광고 통계 조회** : 일간/주간/월간 Top 5         |
| **정산 기능** | 💰 **동영상 및 광고 정산 조회** : 일간/주간/월간             |          


## 💥 프로젝트 주요 경험

<h2 align="center">1. 배치 작업 성능 개선 </h2>


<h2 align="center">2. 부하 분산 </h2>

## ⚙️ 아키텍처 & ERD
<details>
<summary>아키텍처</summary>
> 아키텍처 이미지 추가 예정
</details>

<details>
<summary>ERD</summary>
<img src="https://github.com/user-attachments/assets/445a5e4d-278a-4e29-82fd-afd74ae863c6" width="900"/>
</details>


## 🫧 주요 기능
1. Spring batch를 활용한 통계 및 정산 기능 [문서](https://happygimy97.tistory.com/224)
   - Chunk Oriented Processing으로 배치 작업 수행 [문서]()
2. 부하 분산
   - master/slave 구조로 가용성 DB 구축
   - CQRS 패턴 적용
3. Multi Thread 기반 Spring Batch 구현
   - 병렬로 batch 작업 처리
4. 관련 API [문서](https://documenter.getpostman.com/view/20895656/2sA3kUGMoF)


## ⛳️ 트러블 슈팅
1. DB Replication 실패 문제 [문서](https://available-snow-c33.notion.site/DB-Replication-87602359eb354f3da44566850334b608)
2. LazyInitializationException 문제 [문서](https://available-snow-c33.notion.site/LazyInitializationException-df2c6e0900ec4e5dba4d905c967d91c2)
3. Bean 객체 충돌 문제 [문서](https://available-snow-c33.notion.site/Bean-fc722a99867b48238fda8894664512e0?pvs=25)
4. 로컬 DB 인스턴스 우선 참조 문제 [문서](https://available-snow-c33.notion.site/DB-8e987b0c66764a1bb6f38756789edea8)
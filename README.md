# 정산 프로젝트

## 💡 프로젝트 소개

#### 🧑‍💻 2024.06.19 ~ 2024.07.16

- Spring Batch를 활용한 대용량의 영상 & 광고 시청기록 데이터 통계 및 정산 작업 [batch github](https://github.com/Kimjipang/Settlement-batch)
- DB의 부하 분산 및 가용성을 위한 CQRS 패턴 & Master/Slave DB 구조

## 📚 기술 스택
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<br>
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/Github Actions-2088FF?style=for-the-badge&logo=Github Actions&logoColor=white"><img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/AWS Route 53-8C4FFF?style=for-the-badge&logo=Spring Boot&logoColor=white">


#### 🧑‍💻 기술적 의사결정 [문서](https://available-snow-c33.notion.site/262cb2b4de4e4b269c862f2a5a17a347)

- Spring Batch
- MySQL
- Docker
- Github Actions

## ⚙️ 아키텍처 & ERD

> 아키텍처 이미지 추가 예정

> ERD 이미지 추가 예정

## 🫧 주요 기능
1. 통계 및 정산 기능
   - 5천만 건의 데이터 처리
   - Chunk Oriented Processing으로 배치 작업 수행 [문서]()
2. 부하 분산
   - master/slave 구조로 가용성 DB 구축
   - CQRS 패턴 적용
3. Multi Thread 기반 Spring Batch 구현
   - 병렬로 batch 작업 처리
4. 관련 API [문서](https://documenter.getpostman.com/view/20895656/2sA3kUGMoF)


## ⛳️ 트러블 슈팅
1. DB Replication 실패 에러 [문서](https://available-snow-c33.notion.site/DB-Replication-87602359eb354f3da44566850334b608)
2. LazyInitializationException 에러 [문서](https://available-snow-c33.notion.site/LazyInitializationException-df2c6e0900ec4e5dba4d905c967d91c2)
3. Bean 객체 충돌 에러 [문서](https://available-snow-c33.notion.site/Bean-fc722a99867b48238fda8894664512e0?pvs=25)
4. 로컬 DB 인스턴스 우선 참조 [문서](https://happygimy97.tistory.com/228)
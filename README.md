# 정산 프로젝트

## 💡 프로젝트 소개

#### 🧑‍💻 2024.06.19 ~ 2024.07.16

- Spring Batch를 활용한 대용량의 영상 & 광고 시청기록 데이터 통계 및 정산 작업
- DB의 부하 분산 및 가용성을 위한 CQRS 패턴 & Master/Slave DB 구조

## 📚 기술 스택
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/Github Actions-2088FF?style=for-the-badge&logo=Github Actions&logoColor=white">
<img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=Spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/AWS Route 53-8C4FFF?style=for-the-badge&logo=Spring Boot&logoColor=white">

<p/>

#### 🧑‍💻 기술적 의사결정 [문서](https://available-snow-c33.notion.site/262cb2b4de4e4b269c862f2a5a17a347)

- Spring Batch
- MySQL
- Docker
- Github Actions

## ⚙️ 아키텍처

> 이미지 추가 예정


#### 🧑‍💻 파일 구조도

```
📦settlement
 ┣ 📂advertisement
 ┃ ┣ 📂controller
 ┃ ┃ ┣ 📜AdAdjustmentController.java
 ┃ ┃ ┣ 📜AdController.java
 ┃ ┃ ┣ 📜AdViewController.java
 ┃ ┃ ┗ 📜VideoAdController.java
 ┃ ┣ 📂dto
 ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┣ 📜AdAdjustmentRequestDto.java
 ┃ ┃ ┃ ┣ 📜AdRequestDto.java
 ┃ ┃ ┃ ┣ 📜AdViewRequestDto.java
 ┃ ┃ ┃ ┗ 📜VideoAdRequestDto.java
 ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┣ 📜AdAdjustmentResponseDto.java
 ┃ ┃ ┃ ┣ 📜AdResponseDto.java
 ┃ ┃ ┃ ┣ 📜AdViewResponseDto.java
 ┃ ┃ ┃ ┗ 📜VideoAdResponseDto.java
 ┃ ┣ 📂entity
 ┃ ┃ ┣ 📜AdAdjustment.java
 ┃ ┃ ┣ 📜AdStatistics.java
 ┃ ┃ ┣ 📜AdView.java
 ┃ ┃ ┣ 📜Advertisement.java
 ┃ ┃ ┗ 📜VideoAd.java
 ┃ ┣ 📂repository
 ┃ ┃ ┣ 📂read
 ┃ ┃ ┃ ┣ 📜AdAdjustmentReadRepository.java
 ┃ ┃ ┃ ┣ 📜AdReadRepository.java
 ┃ ┃ ┃ ┣ 📜AdViewReadRepository.java
 ┃ ┃ ┃ ┗ 📜VideoAdReadRepository.java
 ┃ ┃ ┗ 📂write
 ┃ ┃ ┃ ┣ 📜AdAdjustmentWriteRepository.java
 ┃ ┃ ┃ ┣ 📜AdViewWriteRepository.java
 ┃ ┃ ┃ ┣ 📜AdWriteRepository.java
 ┃ ┃ ┃ ┗ 📜VideoAdWriteRepository.java
 ┃ ┗ 📂service
 ┃ ┃ ┣ 📜AdAdjustmentService.java
 ┃ ┃ ┣ 📜AdService.java
 ┃ ┃ ┣ 📜AdViewService.java
 ┃ ┃ ┗ 📜VideoAdService.java
 ┣ 📂common
 ┃ ┣ 📜BaseCreateTimeEntity.java
 ┃ ┣ 📜BaseEntity.java
 ┃ ┣ 📜BaseTimeEntity.java
 ┃ ┗ 📜UserAuth.java
 ┣ 📂config
 ┃ ┣ 📜DataSourceConfig.java
 ┃ ┣ 📜ReadEntityManagerConfig.java
 ┃ ┣ 📜SecurityConfig.java
 ┃ ┗ 📜WriteEntityManagerConfig.java
 ┣ 📂security
 ┃ ┗ 📜JwtRequestFilter.java
 ┣ 📂user
 ┃ ┣ 📂controller
 ┃ ┃ ┣ 📜AuthController.java
 ┃ ┃ ┗ 📜UserController.java
 ┃ ┣ 📂dto
 ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┣ 📜AuthenticationRequest.java
 ┃ ┃ ┃ ┗ 📜UserRequestDto.java
 ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┣ 📜AuthenticationResponse.java
 ┃ ┃ ┃ ┗ 📜UserResponseDto.java
 ┃ ┣ 📂entity
 ┃ ┃ ┣ 📜Role.java
 ┃ ┃ ┗ 📜User.java
 ┃ ┣ 📂repository
 ┃ ┃ ┣ 📂read
 ┃ ┃ ┃ ┗ 📜UserReadRepository.java
 ┃ ┃ ┗ 📂write
 ┃ ┃ ┃ ┗ 📜UserWriteRepository.java
 ┃ ┗ 📂service
 ┃ ┃ ┣ 📜CustomUserDetailsService.java
 ┃ ┃ ┗ 📜UserService.java
 ┣ 📂util
 ┃ ┗ 📜JwtUtil.java
 ┣ 📂video
 ┃ ┣ 📂controller
 ┃ ┃ ┣ 📜VideoAdjustmentController.java
 ┃ ┃ ┣ 📜VideoController.java
 ┃ ┃ ┗ 📜VideoViewController.java
 ┃ ┣ 📂dto
 ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┣ 📜VideoAdjustmentRequestDto.java
 ┃ ┃ ┃ ┣ 📜VideoRequestDto.java
 ┃ ┃ ┃ ┗ 📜VideoViewRequestDto.java
 ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┣ 📜VideoAdjustmentResponseDto.java
 ┃ ┃ ┃ ┣ 📜VideoResponseDto.java
 ┃ ┃ ┃ ┗ 📜VideoViewResponseDto.java
 ┃ ┣ 📂entity
 ┃ ┃ ┣ 📜Video.java
 ┃ ┃ ┣ 📜VideoAdjustment.java
 ┃ ┃ ┣ 📜VideoStatistics.java
 ┃ ┃ ┗ 📜VideoView.java
 ┃ ┣ 📂repository
 ┃ ┃ ┣ 📂read
 ┃ ┃ ┃ ┣ 📜VideoAdjustmentReadRepository.java
 ┃ ┃ ┃ ┣ 📜VideoReadRepository.java
 ┃ ┃ ┃ ┗ 📜VideoViewReadRepository.java
 ┃ ┃ ┗ 📂write
 ┃ ┃ ┃ ┣ 📜VideoAdjustmentWriteRepository.java
 ┃ ┃ ┃ ┣ 📜VideoViewWriteRepository.java
 ┃ ┃ ┃ ┗ 📜VideoWriteRepository.java
 ┃ ┗ 📂service
 ┃ ┃ ┣ 📜VideoAdjustmentService.java
 ┃ ┃ ┣ 📜VideoService.java
 ┃ ┃ ┗ 📜VideoViewService.java
 ┗ 📜SettlementApplication.java
```

## 🫧 주요 기능
1. 통계 및 정산 기능
    - Chunk Oriented Processing으로 배치 작업 수행
2. 부하 분산
    - master/slave 구조로 가용성 DB 구축
    - CQRS 패턴 적용
3. 관련 API [문서](https://available-snow-c33.notion.site/API-30c1782c32364730b292483c0f49de61?pvs=4)

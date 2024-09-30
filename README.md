# 반려동물 프로젝트
### [소프트웨어 마에스트로]
기간: 2023.05 - 2023.11  
인원: Spring 2명, iOS 1명

### 사용한 기술스택
- 구조: Layered Architecture
- 언어: Java 17
- 프레임워크 & 라이브러리: Spring Boot 3.1, Spring Security, Spring Data JPA, QueryDsl, OpenFeign, Validation, Lombok
- 인증 & API 관리: JWT, Apple Login, Kakao Login, Swagger
- 데이터베이스 & 캐싱: MySQL, H2, Redis, Elasticsearch
- 클라우드 & 배포 서비스: AWS ElasticBeanstalk, AWS EC2, AWS RDS, AWS Route53, AWS S3, AWS ELB(ALB), Docker, Docker-Compose
- CI/CD: Github Actions
- 모니터링 & 로깅: SLF4J(Logback), Sentry, ELK (Elasticsearch, Logstash, Kibana), Prometheus, Grafana
- 테스트 도구: JUnit5
- 프로젝트 관리 & 협업 커뮤니케이션 도구: Jira, Figma, Notion, Discord, KakaoTalk, Github
- 문자 서비스: CoolSMS
- 정적 분석 도구: Sonarqube


# 아키텍처
![펫밀리.drawio.png](%ED%8E%AB%EB%B0%80%EB%A6%AC.drawio.png)

# ERD
![데이터베이스.png](%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4.png)

### 동시성 문제 해결
1. 동시성 문제 발생
   - 상황: 회원 가입 요청이 0.001ms 차이로 두 번 오는 동시성 문제 발생
   - 최종 해결: Email 컬럼에 unique key 제약조건을 추가하여 공통 예외 처리로 해결.



### 동시성 이슈 해결하는 방법 정리
https://github.com/kwongio/race-condition



# AWS Elastic Beanstalk 배포 문제 해결
1. 로드밸런서 Health Check 실패:
   - 문제: 루트 경로(/)에 API가 없어 Health Check가 실패함.
   - 해결: 루트 경로에 Health Check API를 만들어 문제를 해결함.
2. Data.sql로 인한 중복 초기값 세팅:
   - 문제: 초기 데이터 설정 중복.
   - 해결: application.yml의 설정을 spring.sql.init.mode:never로 변경하여 해결.
3. GitHub Actions의 Timeout 문제:
   - 문제: wait_for_environment_recovery의 기본값인 30초 내에 배포가 완료되지 않음.
   - 해결: GitHub Actions의 wait_for_environment_recovery를 180초로 조정하고, 인스턴스 성능을 향상시켜 문제 해결.  
https://velog.io/@kjgi73k/Elastic-Beanstalk-Environment-still-has-health-Grey-%ED%95%B4%EA%B2%B0


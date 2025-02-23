### 로컬 데이터베이스 생성
1. mariadb 설치 확인, 없으면 설치
2. MySQL Client 열기
```
## 명령어 끝 세미콜론 필수
# DB 계정 생성
create user 'danggo'@'%' identified by 'danggo12!@';

# DB 생성
create database danggo;

# 확인
show databases;

# 생성한 계정에 대해 권한 부여 및 적용
grant all privileges on danggo.* to 'danggo'@'%';
flush privileges;

# 확인
show grants for 'danggo'@'%';

# 종료
exit
```
3. DBeaver로 확인
   * 새 데이터베이스 연결 - MariaDB 선택
   * Server Host : `localhost`
   * Port : 기본 3306
   * Database: `danggo`
   * Username: `danggo`
   * Password: `danggo12!@`
   * 왼쪽 하단 Test Connection으로 연결 확인 후 완료 
   4. DataSource 설정 : application.propertis
        ```
        spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
        spring.datasource.url=jdbc:mariadb://localhost:3306/danggo
        spring.datasource.username=danggo
        spring.datasource.password=danggo12!@
        ```
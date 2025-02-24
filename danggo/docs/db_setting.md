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
      
** 테스트용 임시 테이블 쿼리 **
```
CREATE TABLE `tb_test` (
  `t_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
  `note` varchar(255) DEFAULT NULL COMMENT '노트',
  PRIMARY KEY (`t_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### MyBatis 연동
1. 의존성 추가 : build.gradle의 dependencies -> Edit Starters...   
   -> MariaDB Driver, Spring Data JDBC, MyBatis Framework 선택 후 빌드
   ![Image](https://github.com/user-attachments/assets/7faee82b-9509-424c-8347-be4ac21a7842)
2. resources/mapper/config/mapper-config.xml 설정
   ```
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   
   <configuration>
       <settings>
           <!-- 컬럼명 형태를 CamelCase형태로 자동으로 매핑 -->
           <setting name="mapUnderscoreToCamelCase" value="true"></setting>
           <!--  파라미터에 Null 값이 있을 경우 에러 처리 -->
           <setting name="jdbcTypeForNull" value="VARCHAR"></setting>
       </settings>
   </configuration>
   ```
3. application.properties에 쿼리를 작성한 xml 파일을 인식할 수 있도록 경로 지정 (classpath는 danggo/src/main/resources)
   ```
   mybatis.mapper-locations=classpath:mapper/sql/**/*.xml
   ```
4. SQL.xml 작성
   ```
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="이름지정">
      <select id="queryId" resultType="com.example.danggo.settingtest.vo.Stest> <!-- 이 부분은 mapper.config.xml에 별칭 등록 고려 -->
         SELECT * FROM TB_TEST;
      </select>
   </mapper>
   ```
5. DAO 작성
   ```
   // Repository 역할 : 스프링 빈 등록, 데이터 접근 계층임을 나타냄, DAO 객체를 스프링 컨테이너에서 관리
   @Repository("StestDAO")
   public class StestDAOImpl extends SqlBaseDAO implements StestDAO {
       @Override
       public void insertTest(String note) {
           insert("지정한 namespace.queryId", note);
       }
   }
   ```
   * SqlBaseDAO 파일 참고 -> 일관된 데이터 접근 처리와 유지보수의 편리를 위해 추상화 클래스 생성
   * DAO를 생성하고 DAOImpl을 생성할 때 `extends SqlBaseDAO` 필수 -> insert, selectOne, selectList, update, delete 사용 가능
   * DAO를 생성하고 DAOImpl을 생성할 때 `@Repository("DAO명")` 작성 및 `implements DAO명` 필수
   * Override 작성하는 경우 해당 sql 쿼리가 작성된 xml에 지정한 namespace명과 쿼리 ID를 `namespace.queryId` 형태로 작성
package Spring.Test.jdk.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class MemberDao {
    @Autowired DataSource dataSource;
    //SimpleJdbcTemplate simpleJdbcTemplate; //Spring 4.x 부터 삭제 NamedParameterJdbcTemplate 혹은 JdbcTemplate 사용
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert;
    SimpleJdbcCall simpleJdbcCall;

    /*
    public void setDataSource(DataSource dataSource){
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
     */

    public void setDataSource(DataSource dataSource) {
        //this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        /* SimpleJdbcInsert 생성*/
        /* Thread Safe 하다 */
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("member");

        /* 저장 프로시저나 펑션을 이용할 때 사용한다 */
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("find_name");
    }

    /* 애노테이션을 이용한 주입 */
    @Autowired
    public void init(DataSource dataSource){
        //this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);

        /* SimpleJdbcInsert 생성*/
        /* Thread Safe 하다 */
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("member");

        /* 저장 프로시저나 펑션을 이용할 때 사용한다 */
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("find_name");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* 파라미터를 이용한 삽입 */
    /*
    public void addMember(int id, String name, double point){
        simpleJdbcTemplate.update("INSERT INTO MEMBER(ID, NAME, POINT) VALUES(?, ?, ?)",
                id,name,point);
    }
    */

    /* 이름 치환자를 이용한 삽입 */
    public void addMember(Map<String, Object> map){
        namedParameterJdbcTemplate.update("INSERT INTO MEMBER(ID, NAME, POINT) VALUES(:id, :name, :point)",
                map);
    }

    /* BeanPropertySqlParameterSource를 사용한 삽입 */
    /* BeanPropertySqlParameterSource를 사용한 바인딩 파라미터 사용 */
    public void addMember(Member member){
        namedParameterJdbcTemplate.update("INSERT INTO MEMBER(ID, NAME, POINT) VALUES(:id, :name, :point)",
                new BeanPropertySqlParameterSource(member));
    }

    /* MapSqlParameterSource를 사용한 삽입 */
    /* MqpSourceParameterSource를 사용한 바인딩 파라미터 사용 */
    public void addMember(){
        namedParameterJdbcTemplate.update("INSERT INTO MEMBER(ID, NAME, POINT) VALUES(:id, :name, :point)",
                new MapSqlParameterSource()
        .addValue("id",4).addValue("name", "Spring").addValue("point", 3.5));
    }

    /* 배치 메소드를 통한 삽입 */
    public void batchaddMember(){
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO MEMBER(ID, NAME, POINT) VALUES(:id, :name, :point)",
                new SqlParameterSource[] {
        new MapSqlParameterSource().addValue("id",5).addValue("name", "Spring").addValue("point",3.5),
        new BeanPropertySqlParameterSource(new Member(6, "Spring", 3.5))
    });
    }

    /* Insert를 이용한 삽입 기본적으로 테이블의 모든 컬럼을 다 사용하지만 변경 가능하다 (.usingcolunms) */
    public void addWithInsert(){
        Member member = new Member(7, "Spring", 3.5);
        simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(member));
    }

    public String getMemberNameByCall(int id){
        return simpleJdbcCall.executeFunction(String.class, id);
    }

    public void deleteAll(){
        jdbcTemplate.update("DELETE FROM MEMBER");
    }
}

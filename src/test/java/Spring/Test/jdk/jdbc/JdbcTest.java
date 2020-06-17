package Spring.Test.jdk.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "JdbcAppContext.xml")
public class JdbcTest {
    @Autowired MemberDao memberDao;
    Map<String, Object> map;

    @Before
    public void setUp(){
        /* Map을 사용한 바인딩 파라미터 사용 */
        map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("name", "Spring");
        map.put("point", 3.5);
    }

    @Test
    public void insert(){
        memberDao.deleteAll();
        memberDao.addMember();
        memberDao.addMember(map);
        memberDao.batchaddMember();
        assertThat(memberDao.getMemberNameByCall(1), is("Spring"));
    }

}

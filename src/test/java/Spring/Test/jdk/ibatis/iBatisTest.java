package Spring.Test.jdk.ibatis;

import Spring.Test.jdk.jdbc.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class iBatisTest {
    @Autowired IbatisDao dao;

    @Test
    public void ibatis() {
        dao.deleteAll();
        dao.insert(new Member(5, "iBatis", 1.2));
        dao.insert(new Member(6, "sqlMap", 3.3));

        Member m = dao.select(5);
        assertThat(m.getId(), is(5));
        assertThat(m.getName(), is("iBatis"));
        assertThat(m.getPoint(), is(1.2));

        List<Member> ms = dao.selectAll();
        assertThat(ms.size(), is(2));

    }
    public static class IbatisDao {
        /* iBatis도 사용되지 않는다 MyBatis를 공부하자.. */
        private SqlMapClientTemplate sqlMapClientTemplate;

        public void setSqlMapClient(SqlMapClient sqlMapClient) {
            sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
        }

        public void insert(Member m) { sqlMapClientTemplate.insert("insertMember", m); }
        public void deleteAll() { sqlMapClientTemplate.delete("deleteMemberAll"); }
        public Member select(int id) { return (Member)sqlMapClientTemplate.queryForObject("findMemberById", id); }
        public List<Member> selectAll() { return sqlMapClientTemplate.queryForList("findMembers"); }
    }
}

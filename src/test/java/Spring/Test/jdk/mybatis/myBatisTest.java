package Spring.Test.jdk.mybatis;

import Spring.Test.jdk.jdbc.Member;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "mybatisAppContext.xml")
public class myBatisTest {
    @Autowired MybatisDao mybatisdao;

    @Test
    public void mybatis() {
        mybatisdao.deleteAll();
        mybatisdao.insert(new Member(5, "myBatis", 1.2));
        mybatisdao.insert(new Member(6, "sqlMap", 3.3));

        Member m = mybatisdao.select(5);
        assertThat(m.getId(), is(5));
        assertThat(m.getName(), is("myBatis"));
        assertThat(m.getPoint(), is(1.2));

        List<Member> ms = mybatisdao.selectAll();
        assertThat(ms.size(), is(2));

    }
    public static class MybatisDao {
        @Autowired
        @Qualifier("sqlSession")
        private SqlSession sqlSession;

        public void setSqlSession(SqlSession sqlSession) { this.sqlSession = sqlSession; }

        public void insert(Member m) { sqlSession.insert("insertMember", m); }
        public void deleteAll() { sqlSession.delete("deleteMemberAll"); }
        public Member select(int id) { return (Member)sqlSession.selectOne("findMemberById", id); }
        public List<Member> selectAll() { return sqlSession.selectList("findMembers"); }
    }
}

package Spring.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import Spring.user.domain.Level;
import Spring.user.domain.User;
import Spring.user.sqlservice.SqlService;

@Repository
public class UserDaoJdbc implements UserDao {
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlService sqlService;

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	private RowMapper<User> userMapper = 
		new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setLevel(Level.valueOf(rs.getInt("level")));
				user.setLogin(rs.getInt("login"));
				user.setRecommend(rs.getInt("recommend"));
				return user;
			}
		};

	public void add(User user) {
		this.jdbcTemplate.update(
				this.sqlService.getSql("userAdd"), 
					user.getId(), user.getName(), user.getPassword(), user.getEmail(), 
					user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}

	public User get(String id) {
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGet"),
				new Object[] {id}, this.userMapper);
	} 

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
	}

	public int getCount() {
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCount"),int.class);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("userGetAll"), this.userMapper);
	}

	public void update(User user) {
		this.jdbcTemplate.update(
				this.sqlService.getSql("userUpdate"),
		user.getName(), user.getPassword(), user.getEmail(), 
		user.getLevel().intValue(), user.getLogin(), user.getRecommend(),
		user.getId());
		
	}
}

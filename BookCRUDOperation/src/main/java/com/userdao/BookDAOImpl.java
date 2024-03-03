package com.userdao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

import com.userdto.BookDTO;

public class BookDAOImpl implements BookDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean insertBookDetails(BookDTO bookDTO) {
		String sql = "insert into book values(?, ?, ?)";

		Object property[] = { bookDTO.getBookId(), bookDTO.getBookName(), bookDTO.getBookPrice() };

		jdbcTemplate.update(sql, property);
		return true;
	}

	@Override
	public boolean findBookDetailsById(int id) {
		String sql = "select * from book where book_id=?";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, id);
		System.out.println(queryForList);
		return true;
	}

	@Override
	public boolean updateBookDetailsById(int id, String newBookName) {
		String sql = "update book set book_name = ? where book_id = ?";
		int rowsAffected = jdbcTemplate.update(sql, newBookName, id);
		if (rowsAffected > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void printAllBookDetails() {

		String sql = "select * from book";

		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);

		int count = 1;
		for (Map<String, Object> book : queryForList) {

			Set<Entry<String, Object>> entrySet = book.entrySet();
			System.out.println(count + "->" + entrySet);
			System.out.println("============================");
			count++;
		}
	}

	@Override
	public boolean deleteBookDetailsById(int id) {
		String sql = "delete from book where book_id=?";
		int rowAffected = jdbcTemplate.update(sql, id);
		if (rowAffected > 0) {
			return true;
		} else {
			return false;
		}
	}
}

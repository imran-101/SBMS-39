package com.userdao;

import com.userdto.BookDTO;

public interface BookDAO {
	public boolean insertBookDetails(BookDTO bookDTO);
	public boolean findBookDetailsById(int id);
	public boolean updateBookDetailsById(int id, String bookName);
	public void printAllBookDetails();
	public boolean deleteBookDetailsById(int id);
}

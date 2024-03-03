package com.userservice;

import com.userdto.BookDTO;

public interface BookService {
	public void addBook(BookDTO bookDTO);
	public void findBookById(int id);
	public void updateBookById(int id, String bookName);
	public void printAll();
	public void deleteBookById(int id);
}

package com.userservice;

import com.userdao.BookDAO;
import com.userdto.BookDTO;

public class BookServiceImpl implements BookService {
	BookDAO bookDao;

	public void setBookDao(BookDAO bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public void addBook(BookDTO bookDTO) {
		boolean status = bookDao.insertBookDetails(bookDTO);
		if(status) {
			System.out.println("record inserted...");
		}
		else {
			System.out.println("insertion failed");
		}
	}

	@Override
	public void findBookById(int id) {
		boolean status = bookDao.findBookDetailsById(id);
		if(!status) {
			System.out.println("Record not Available...");
		}
	}
	
	@Override
	public void updateBookById(int id, String bookName) {
		boolean status = bookDao.updateBookDetailsById(id,bookName);
		if(status) {
			System.out.println("Record Updated...");
		}
		else {
			System.out.println("no record available with id : "+id);
		}
	}

	@Override
	public void printAll() {
		bookDao.printAllBookDetails();
	}


	@Override
	public void deleteBookById(int id) {
		boolean status = bookDao.deleteBookDetailsById(id);
		if(status) {
			System.out.println("Record Deleted...");
		}
		else {
			System.out.println("Record is not Present...");
		}
	}

}

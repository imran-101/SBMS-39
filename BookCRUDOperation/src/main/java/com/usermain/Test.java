package com.usermain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.userdto.BookDTO;
import com.userservice.BookServiceImpl;

public class Test {
	public static void main(String[] args) {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("config.xml");
		
		// object creation
		BookDTO bto=new BookDTO();
		
		BookServiceImpl bookService = context.getBean(BookServiceImpl.class);
		
		// value setting in object by BookDTO setter method
//		bto.setBookId(4);
//		bto.setBookName("rust");
//		bto.setBookPrice(1500);
		
		// performing the add book details operation.
//		bookService.addBook(bto);
		
		// performing the findBookById details operation.
//		bookService.findBookById(1);
		
		//performing the updateBookById details operation.
//		bookService.updateBookById(4, "c");
		
		// performing the printAllBooks details operation
		bookService.printAll();
		
		// performing the deleteBookById details operation
//		bookService.deleteBookById();
		
	}
}

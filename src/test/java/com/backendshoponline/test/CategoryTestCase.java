package com.backendshoponline.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.backendshoponline.dao.CategoryDAO;
import com.backendshoponline.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
    private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
		
	}
	
	
	@Test
	public void testAddCategory(){
		category =new Category();
		System.out.println("category");
		category.setName("Camfer");
		category.setDescription("Camfer are of good number");
		category.setImageURL("cat_2.png");
		assertEquals("Successfully added a category",true,categoryDAO.add(category));
		}
	/*@Test
	public void testUpdateCategory(){
		category =  categoryDAO.get(1)   ;
		category.setName("TV");
		assertEquals("Successfully added a category",true,categoryDAO.update(category));
		
	}
	
/*	@Test
	public void testDeleteCategory(){
		category =  categoryDAO.get(1)   ;
		assertEquals("Successfully added a category",true,categoryDAO.delete(category));
		
	}
	*/
	/*
	@Test
	public void testListCategory(){
		
		assertEquals("Successfully added a category",5,categoryDAO.list().size());
		
	}*/
	
	
	
}

package com.backendshoponline.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	private Product product;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	@Test
	public void testCRUDProduct(){
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phones!");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while inserting a new product!",true,productDAO.add(product));
	
	
	product = productDAO.get(2);
	product.setName("Samsung Galaxy S7");
	assertEquals("Something went wrong while updating the existing record!",true,productDAO.update(product));
	
	
	assertEquals("Something went wrong while deleting the existing record!",true,productDAO.delete(product));
	
	assertEquals("Something went wrong while fetching the list of products!",6,productDAO.list().size());
	
	
	}
	
	
}

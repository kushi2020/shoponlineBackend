package com.backendshoponline.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backendshoponline.dao.CartLineDAO;
import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dao.UserDAO;
import com.backendshoponline.dto.Cart;
import com.backendshoponline.dto.CartLine;
import com.backendshoponline.dto.Product;
import com.backendshoponline.dto.User;

public class CartLinetestCase {
	private static AnnotationConfigApplicationContext context;
    private static CartLineDAO cartLineDAO;
    private static User user =null;
    private static UserDAO userDAO;
    private static Cart cart; 
	private static Product product;
	private static ProductDAO productDAO;
	private static CartLine cartLine;
	
	@BeforeClass
	public static void init(){
		System.out.println("initializastion call------------------------------");
		context = new AnnotationConfigApplicationContext();
		context.scan("com");System.out.println("initializastion scan done------------------------------");
		context.refresh();
		cartLineDAO=(CartLineDAO) context.getBean("cartLineDAO");
		System.out.println("initializastion catline called------------------------------");
		userDAO=(UserDAO) context.getBean("userDAO");
		productDAO=(ProductDAO) context.getBean("productDAO");
		
		
	}
	
	@Test
	public void testAddNewCartLine(){
		
		user =userDAO.getByEmail("k@gmail.com");
		cart =user.getCart();
		product =productDAO.get(1);
		cartLine=new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine",true,cartLineDAO.add(cartLine));
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("Failed to update the cartLine",true,cartLineDAO.updateCart(cart));

		
		
		
	}
	
	
	

}

package com.backendshoponline.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backendshoponline.dao.UserDAO;
import com.backendshoponline.dto.Address;
import com.backendshoponline.dto.Cart;
import com.backendshoponline.dto.User;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@Before
	public  void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		
	}
	
	@Test
	public void testAdd(){
		user = new User();
		user.setFirstName("kushi");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("123");
		//add the user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//link the user with the address using user id
		address.setUser(user);
		
		//add the address
		assertEquals("Failed to add user!",true,userDAO.addAddress(address));
		
		if(user.getRole().equals("USER"))
		{
		//create a cart for this user
			cart = new Cart();
			cart.setUser(user);
			//add the cart
//			assertEquals("Failed to add cart!",true,userDAO.updateCart(cart));
			//add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat store");
            address.setState("Mharastra");
			address.setCity("Mumbai");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user
			address.setUser(user);
			
			//add the shipping address
			assertEquals("Failed to add shipping address!",true,userDAO.addAddress(address));
		}
		
	} 

/*	@Test
	public void testAdd(){
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("123");
		
		
		
		if(user.getRole().equals("USER"))
		{
		//create a cart for this user
			cart = new Cart();
			cart.setUser(user);
			//attach cart with the user
			user.setCart(cart);
			
		}
		//add the user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		
	} */
/*	@Test
	public void testUpdateCart()
	{
		//fetch the user by its email
		user = userDAO.getByEmail("hr@gmail.com");
		
		//get the cart of the user
		cart = user.getCart();
		cart.setGrandTotal(5555);
		cart.setCartLines(2);
		assertEquals("Failed to update the cart!",true,userDAO.updateCart(cart));
	}
	*/

/*	@Test
	public void testAddAddress()
	{
	//create user
		
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("123");
		//add the user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//linking address to user
		address.setUser(user);
    	//Add billing Address
		//add the address
				assertEquals("Failed to add user!",true,userDAO.addAddress(address));	
		
	//Add Shipping Address	
				address = new Address();
				address.setAddressLineOne("101/B Jadoo society, Krissh Nagar");
				address.setAddressLineTwo("Near Kaabil Store");
				address.setCity("Mumbai");
				address.setState("Maharashtra");
				address.setCountry("India");
				address.setPostalCode("400001");
				address.setUser(user);
				address.setShipping(true);
				assertEquals("Failed to add user!",true,userDAO.addAddress(address));	
		
	}
*/	
	
/*	@Test
	public void  testAddAddre()
	{
		user = userDAO.getByEmail("hr@gmail.com");
		System.out.println("Got The User From Data base");
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharastra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(false);
		address.setUser(user);
		address.setShipping(true);
		assertEquals("Failed to add user!",true,userDAO.addAddress(address));
		
		
		
		
		
	} 
	*/
	
	
/*	@Test
	public void listOfAddress()
	{
		user =userDAO.getByEmail("hr@gmail.com");
		assertEquals("Failed to add user!",1,userDAO.listBillingAddress(user).size());
	}
*/		

}

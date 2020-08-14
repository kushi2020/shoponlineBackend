package com.backendshoponline.dao;

import java.util.List;

import com.backendshoponline.dto.Address;
import com.backendshoponline.dto.Cart;
import com.backendshoponline.dto.User;

public interface UserDAO {
	
	//add an user
	boolean addUser(User user);
	User getByEmail(String email);
	
	//add an address
	boolean addAddress(Address address);
	
	
	//add a cart
		Address getBillingAddress(User user);
	List<Address> listShippingAddress(int userId);
	List<Address> listBillingAddress(User user);
	
	boolean addCart(Cart cart);
	
	
}

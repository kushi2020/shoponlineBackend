package com.backendshoponline.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backendshoponline.dao.UserDAO;
import com.backendshoponline.dto.Address;
import com.backendshoponline.dto.Cart;
import com.backendshoponline.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public boolean addUser(User user) {
	try{	sessionFactory.getCurrentSession().persist(user);
	  System.out.println("User is created----------------");
	
		return true;
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		return false;
	}
		
	}

	@Override
	public boolean addAddress(Address address) {
		try{
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	
	@Override
	public User getByEmail(String email) {
		String selectQuery= "FROM User WHERE email = :email";
		try{
			return sessionFactory.getCurrentSession().createQuery(selectQuery,User.class).setParameter("email", email).getSingleResult();
			
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public Address getBillingAddress(User user) {
		// TODO Auto-generated method stub
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing" ;
		try{
			
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class).setParameter("user", user).setParameter("billing", true).getSingleResult();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	
	}

	@Override
	public List<Address> listShippingAddress(int userId) {
		// TODO Auto-generated method stub
		String selectQuery = "FROM Address WHERE id = :userId AND shipping = :shipping" ;
		try{
			
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class).setParameter("id", userId).setParameter("shipping", true).getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Address> listBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing" ;
		try{
			
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class).setParameter("user", user).setParameter("billing", true).getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

}

	@Override
	public boolean addCart(Cart cart) {
		try{
			sessionFactory.getCurrentSession().persist(cart);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
}

package it.al.ma.test;

	import static org.junit.Assert.*;

	import java.util.ArrayList;
	import java.util.List;

	import org.junit.Assert;
	import org.junit.Test;

	import it.al.ma.dao.UserDao;
	import it.al.ma.dao.UserDaoImpl;
	import it.al.ma.model.User;




	public class UserDaoImplTest {
	 
	 UserDao userDao= new UserDaoImpl();
	  
	  @Test
	  public void findAllUserTest() {
	   List list = new ArrayList();
	   
	   list=userDao.findAllUser();
	   assertNotNull(list);
	   System.out.println(list);
	   
	 
	  
	   
	  }
	     
	  @Test
	  public void insertUserTest(){
	   User user= new User("aaa","aaa","aaa","aaa",2,"aaa",5,"aaa","aaa",3);
	   userDao.insertUser(user);
	   User  user6=userDao.findByIdUser(user);
	   Assert.assertEquals(user, user6);
	   System.out.println(user+" "+user6);
	   
	  }
	  

	  
	  
	  @Test
	  public void findByIdUserTest(){
	   User user= new User(1);
	   User user1= new User("aaa","aaa","aaa","aaa",2,"aaa",7,"aaa","aaa",3);
	   userDao.findByIdUser(user);
	   Assert.assertNotSame("mah", user, user1);
	   System.out.println(user+" "+user1);
	   
	  }
	  
	  @Test
	  public void findByEmailAndPasswordTest(){
	   User user2= new User("lello","lello");
	   User user3 = userDao.findByMailAndPassword(user2);
	   System.out.println(user3+"PAPPAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	  }
	  
	  @Test
	  public void updateUserTest(){
	   User user4 = new User(1);
	   User user5= userDao.findByIdUser(user4);
	   user5.setLastname("ooo");
	   user5.setPassword("ooo");
	   userDao.updateUser(user5);
	   System.out.println(user5+"oooooooooooooooooooooooooooooo");
	   
	  }
	  
	  @Test
	  public void deleteUserTest(){
	   User user4 = new User(1);
	   User user5= userDao.findByIdUser(user4);
	   userDao.deleteUser(user5);
	   System.out.println(user5+"zzzzzzzzzzzzzz");
	   
	  }
	  
	}
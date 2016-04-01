package catalyst.thymeleaf.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import catalyst.thymeleaf.entity.User;

@Repository
public class UserStorage {
	
	private List<User> pplList = new ArrayList<User>();
	
	public UserStorage() {
		
		pplList.add(new User("Kevin", 24));
		pplList.add(new User("Billy", 28));
	}
	
	public List<User> getList() {
		
		return pplList;
	}
	
	public User getUser(String name) {
		
		for (User user : pplList) {
			
			if (user.getName().toLowerCase().equals(name)) {
				
				return user;
			}
		}
		
		return null;
	}

}

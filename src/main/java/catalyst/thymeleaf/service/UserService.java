package catalyst.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catalyst.thymeleaf.entity.User;
import catalyst.thymeleaf.repository.UserStorage;

@Service
public class UserService {
	
	@Autowired
	private UserStorage storage;
	
	private void setStorage(UserStorage storage) {
		
		this.storage = storage;
	}
	
	public List<User> getPplList() {
		
		return storage.getList();
	}
	
	public User getUser(String name) {
		
		return storage.getUser(name);
	}

}

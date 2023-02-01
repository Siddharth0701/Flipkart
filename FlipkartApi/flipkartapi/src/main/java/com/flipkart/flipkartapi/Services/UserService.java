package com.flipkart.flipkartapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flipkart.flipkartapi.dao.UserRepository;
import com.flipkart.flipkartapi.dto.UsersData;
import com.flipkart.flipkartapi.exception.IdNotFoundException;
import com.flipkart.flipkartapi.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;

	private Users getUserEntity(UsersData usersData) {
		Users users = new Users();
		users.setId(usersData.getUserId());
		users.setName(usersData.getName());
		users.setEmail(usersData.getEmail());
		users.setPassword(usersData.getPassword());
		return users;

	}

	private UsersData getUserData(Users users) {
		UsersData usersData = new UsersData();
		usersData.setUserId(users.getId());
		usersData.setName(users.getName());
		usersData.setEmail(users.getEmail());
		usersData.setPassword(users.getPassword());
		return usersData;

	}

	@Override
	public List<UsersData> findAll() {
		List<UsersData> userDataList = new ArrayList<>();
		List<Users> users = userRepository.findAll();
		users.forEach(user -> {
			userDataList.add(getUserData(user));
		});

		return userDataList;
	}

	@Override
	public UsersData findById(Long id) throws IdNotFoundException {
		Optional<Users> userOptional = userRepository.findById(id);
		if (userOptional == null) {
			new IdNotFoundException("User not found");
		}

		return getUserData(userOptional.get());
	}

	@Override
	public UsersData create(UsersData usersData) {
		Users users = getUserEntity(usersData);
		return getUserData(userRepository.save(users));
	}

	@Override
	public boolean delete(Long id) {
		Users users = userRepository.findById(id).get();
		if (users != null) {
			userRepository.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(UsersData usersData, Long id) {
		Users users2 = userRepository.findById(id).get();
		if (users2 != null) {
			users2.setName(usersData.getName());
			users2.setEmail(usersData.getEmail());
			users2.setPassword(usersData.getPassword());
			userRepository.save(users2);
			return true;
		}
		return false;
	}

}

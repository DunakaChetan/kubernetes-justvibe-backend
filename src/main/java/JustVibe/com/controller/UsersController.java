package JustVibe.com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JustVibe.com.model.Users;
import JustVibe.com.model.UsersManager;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersManager UM;
	
	@PostMapping("/insert")
	public String insertUser(@RequestBody Users U)
	{
		return UM.addUser(U);
	}
	
	@PostMapping("/signin")
	public String signIn(@RequestBody Users U)
	{
		return UM.login(U.getEmail(), U.getPassword());
	}
	
	@PostMapping("/getusername")
	public String getUsername(@RequestBody Map<String,String> data)
	{
		return UM.getUsername(data.get("csrid"));
	}
	
}
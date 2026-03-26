package in.rohanIT.quotes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.rohanIT.quotes.service.UserService;
import in.rohanIT.quotes.userdto.UserDto;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getCountries")
	public List<String> getCountries() {
		List<String> listOfCountries=userService.getCountriesList();
		return listOfCountries;
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserDto userDto) {
		return userService.register(userDto);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserDto userDto, String pw) {
		return userService.login(userDto, pw);
	}
	
	@GetMapping("/getStates")
	public List<String> getStates(@RequestParam String country) {
	    return userService.getStates(country);
	}

	@GetMapping("/getCities")
	public List<String> getCities(@RequestParam String state) {
	    return userService.getCities(state);
	}
}

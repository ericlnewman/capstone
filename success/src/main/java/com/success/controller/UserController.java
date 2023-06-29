package com.success.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.success.dto.UserDTO;
import com.success.service.UserService;



@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/saveuser")
	public  String saverUser(UserDTO userDTO){
		userDTO.setConcernId(12);
		return "start";
	}
	
	
	/**
	 * Handle the /start end point with GET (Read, send data to client)
	 * @ResponseBody would make this into JSON
	 * */
	@RequestMapping(value="/readJSON", method=RequestMethod.GET)
	public UserDTO readJSON(Model model) {
		UserDTO userDTO = userService.getUserById(123456789L);
		model.addAttribute("userDTO", userDTO);
		return userDTO;
	}
	
	/**
	 * 
	 * @param model
	 * @param concerns
	 * @return a different concern when entered in the browser with http://127.0.0.1:8080/adduser?concerns=something
	 */
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String addUser(Model model, @RequestParam(value="concerns", required= false, defaultValue="adhd") String concerns) {
		UserDTO userDTO = userService.getUserById(123456789L);
		userDTO.setConcerns(concerns);
		model.addAttribute("userDTO", userDTO);
		return "start";
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "start";
	}
	
	/**params make an if test if certain things occur*/
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"read = academic"})
	public String readAcademic() {
		return "start";
	}
	
	
	/**params make an if test if certain things occur*/
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"read = behavior"})
	public String readBehavior() {
		return "start";
	}
	/**
	 * 
	 *headers add meta data that client does not see
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET, headers= {"content-type=text/json"})
	public String readJson() {
		return "start";
	}
	/**
	 * Handle the /start end point with POST (create, send data to server)
	 * */
	@PostMapping("/start")
	public String create() {
		return "start";
	}
	
	/**
	 * Handle the / end point
	 * */
	@GetMapping("/")
	public String start() {
		return "start";
	}
	
	@RequestMapping("/searchConcerns")
	public String searchConcerns(@RequestParam(value="searchTerm", required= false, defaultValue="") String searchTerm) { {
		String enhancedTerm = searchTerm + "";
		return "start";
	}
	
	@RequestMapping("/searchConcerns")
	public String searchAll(@RequestParam Map<String, String> requestParams) { 
		int params = requestParams.size();
		return "start";
	}
}

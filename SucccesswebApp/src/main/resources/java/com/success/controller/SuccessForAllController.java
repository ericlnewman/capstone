package com.success.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.model.UserAddress;
import com.success.model.UserInformation;
import com.success.model.UserPersonOfConcern;
import com.success.service.SuccessForAllService;
import com.success.service.UserService;

@Controller
/**
 * A Controllers are responsible for controlling the flow of the application execution.
 * In other words controller handles the user's clicks within the web page.
 * It calls down to the service layer (business logic) that aggregates DAOs.
 */
public class SuccessForAllController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SuccessForAllService successForAllService;
	@Autowired
	private List<UserService> userServiceList;
	@Autowired
	private UserService userService;
	
	
	/**
	 * Handle the /start end point with GET (Read, send data to client)
	 * @ResponseBody would make this into JSON
	 * */
	@RequestMapping(value="/readJSON", method=RequestMethod.GET)
	public UserDTO readJSON(Model model) {
		UserDTO userDTO = successForAllService.getSuccessForAllId(123456789L);
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
	public String addUser(Model model, @RequestParam(value="concerns", required= false, defaultValue="") String concerns) {
		UserDTO userDTO = successForAllService.getSuccessForAllId(123456789L);
		userDTO.setConcerns(concerns);
		model.addAttribute("userDTO", userDTO);
		return "adduser";
	}
	
	@RequestMapping(value="/tart", method=RequestMethod.GET)
	public String read(Model model) {
		log.info("User has entered the /start endpoint");
		model.addAttribute("userDTO", new UserDTO());
		return "start";
	}
	
	/**@param make an if test if certain things occur*/
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"read = academic"})
	public String readAcademic() {
		return "start";
	}
	
	
	/**@param make an if test if certain things occur*/
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
	@PostMapping("/api/v/start")
	public String create() {
		return "start";
	}
	
	/**
	 * Handle the / end point
	 * */
	@GetMapping("/api/")
	public String start() {
		return "start";
	}
	
	/*  *********************************************************************************************
	 *  This is used to search for concerns with the search bar in the nav bar of the app
	 *  The @Requestparam allows the user to enter a term in the search bar, and then to GET that info
	 *  from the database and display to the user.
	 *  *********************************************************************************************/
	@RequestMapping("/searchConcerns")
	public ModelAndView searchConcerns(@RequestParam(value="searchTerm", required=false, defaultValue="")
								String searchTerm, Model model) {
	    log.debug("Entering search concerns");
		ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("userDTO", new UserDTO());
	    List<ConcernsDTO> concerns = new ArrayList<ConcernsDTO>();	
	    try {		
	    	concerns = userService.fetchConcerns(searchTerm);
			modelAndView.setViewName("concernResults");
			if(concerns.size() == 0) {
				log.warn("Recieved 0 results for search string: " + searchTerm);
			}
	    } catch (Exception e) {
			log.error("Error happened in searchConcerns endpoint", e);
			e.printStackTrace();
			modelAndView.setViewName("error");
		}
                              // name association for thymeleaf to ensure these match from controller to html
	    modelAndView.addObject("concerns", concerns);
	    log.debug("Exiting search Concerns");
	    return modelAndView;
	}

	// this is an alternative way to search, not currently used.
	@RequestMapping("/searchConcernsAll")
	public String searchAll(@RequestParam Map<String, String> requestParams, @ModelAttribute("userDTO")
					UserDTO userDTO) { 
		int params = requestParams.size();
		requestParams.get("searchConcern");
		return "start";
	}
	
	/*  *********************************************************************************************
	 * These are links to the various web pages within the online application
	 * 
	 * **********************************************************************************************/
	@RequestMapping("academicConcerns")
	public String academicConcernsLink() {
		return "academicConcerns";
	}
	
	@RequestMapping("behaviorConcerns")
	public String behaviorConcernsLink() {
		return "behaviorConcerns";
	}
	
	@RequestMapping("laws")
	public String lawsLink() {
		return "laws";
	}
	
	@RequestMapping("localresources")
	public String localResourcesLink() {
		return "localresources";
	}
	
	@RequestMapping("/logout")
	public String logoutLink() {
		return "logout";
	}
	
	@RequestMapping("/login")
	public String loginLink() {
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signupLink() {
		return "signup";
	}
	
	@RequestMapping("/index")
	public String indexLink() {
		return "index";
	}
	
	@RequestMapping("/forgotLoginInfo")
	public String forgotLoginInfoLink() {
		return "forgotLoginInfo";
	}
	
	@GetMapping("/profile")
	public String profileLink() {
		return "profile";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
	    model.addAttribute("userDTO", new UserDTO());
	    return "signup";
	}
	
	
	 @PostMapping("/signup")
	    public String signUp(@RequestBody UserDTO userDTO) {
	        return "start";	
	    }
	
}


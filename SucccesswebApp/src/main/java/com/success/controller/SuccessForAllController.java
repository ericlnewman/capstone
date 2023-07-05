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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.service.SuccessForAllService;
import com.success.service.UserService;

import jakarta.validation.ConstraintViolationException;

@Controller
@RequestMapping("/")
public class SuccessForAllController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SuccessForAllService successForAllService;
    private final UserService userService;

    @Autowired
    public SuccessForAllController(SuccessForAllService successForAllService, UserService userService) {
        this.successForAllService = successForAllService;
        this.userService = userService;
    }

   

    @RequestMapping(value = "/readJSON", method = RequestMethod.GET)
    public UserDTO readJSON(Model model) {
        UserDTO userDTO = successForAllService.getSuccessForAllId(123456789L);
        model.addAttribute("userDTO", userDTO);
        return userDTO;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(Model model, @RequestParam(value = "concerns", required = false, defaultValue = "") String concerns) {
        UserDTO userDTO = successForAllService.getSuccessForAllId(123456789L);
        userDTO.setConcerns(concerns);
        model.addAttribute("userDTO", userDTO);
        return "adduser";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String read(Model model) {
        log.info("User has entered the /start endpoint");
        model.addAttribute("userDTO", new UserDTO());
        return "start";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET, params = "read=academic")
    public String readAcademic() {
        return "start";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET, params = "read=behavior")
    public String readBehavior() {
        return "start";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET, headers = "content-type=text/json")
    public String readJson() {
        return "start";
    }

    @PostMapping("/start")
    public String create() {
        return "start";
    }

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @RequestMapping("/searchConcerns")
    public ModelAndView searchConcerns(@RequestParam(value = "searchTerm", required = false, defaultValue = "") String searchTerm, Model model) {
        log.debug("Entering search concerns");
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("userDTO", new UserDTO());
        List<ConcernsDTO> concerns = new ArrayList<>();

        try {
            concerns = userService.fetchConcerns(searchTerm);
            modelAndView.setViewName("concernResults");
            if (concerns.size() == 0) {
                log.warn("Received 0 results for search string: " + searchTerm);
            }
        } catch (Exception e) {
            log.error("Error happened in searchConcerns endpoint", e);
            modelAndView.setViewName("error");
        }

        modelAndView.addObject("concerns", concerns);
        log.debug("Exiting search Concerns");
        return modelAndView;
    }

    @RequestMapping("/searchConcernsAll")
    public String searchAll(@RequestParam Map<String, String> requestParams, @ModelAttribute("userDTO") UserDTO userDTO) {
        int params = requestParams.size();
        requestParams.get("searchConcern");
        return "start";
    }

    @RequestMapping("/academicConcerns")
    public String academicConcernsLink() {
        return "academicConcerns";
    }

    @RequestMapping("/behaviorConcerns")
    public String behaviorConcernsLink() {
        return "behaviorConcerns";
    }

    @RequestMapping("/laws")
    public String lawsLink() {
        return "laws";
    }

    @RequestMapping("/localresources")
    public String localResourcesLink() {
        return "localresources";
    }

    @RequestMapping("/logout")
    public String logoutLink() {
        return "logout";
    }
    
    @PostMapping(value = "/signup")
    public String saveUser(UserDTO userDTO, Model model) {
        try {
            userService.newSuccessForAllUser(userDTO);
        } catch (ConstraintViolationException e) {
            log.error("Validation failed for user.", e);
            model.addAttribute("error", "Invalid password, must be at least 8 characters, a special character, number, upper and lower case. Please try again.");
            model.addAttribute("userDTO", userDTO);
            return "signup";
        } catch (Exception e) {
            log.error("Unable to save user.", e);
            return "error";
        }

        return "start";
    }

    @PostMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @GetMapping("/login")
    public String loginLink(Model model) {
    	model.addAttribute("userDTO", new UserDTO());
        return "login";
    }
   
    @GetMapping("/signup")
    public String  signupLink(Model model) {
    	model.addAttribute("userDTO", new UserDTO());
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
    public String profileLink(Model model) {
    	model.addAttribute("userDTO", new UserDTO());
        return "profile";
    }
    
    @PostMapping(value = "/profile")
    public String updateUser(UserDTO userDTO, Model model) {
        try {
            userService.save(userDTO);
        } catch (ConstraintViolationException e) {
            log.error("Validation failed for user.", e);
            model.addAttribute("error", "Invalid password, must be at least 8 characters, a special character, number, upper and lower case. Please try again.");
            model.addAttribute("userDTO", userDTO);
            return "profile";
        } catch (Exception e) {
            log.error("Unable to save changes.", e);
            return "error";
        }

        return "saveuser";
    }

    
}

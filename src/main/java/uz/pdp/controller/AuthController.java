package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    UserService userService;


    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerUserForm(Model model){

        model.addAttribute("message", "Registration Form");
        return "/auth/registrationForm";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User registeringUser, HttpServletRequest request,
                               Model model){
        User registeredUser = userService.registerUser(registeringUser);

        if (registeredUser.getId() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", registeredUser.getId());
            model.addAttribute("message", "Successfully Registered");
            return "redirect:/person/home";
        }

        model.addAttribute("message", "Registration Failed, please try again!");
        return "/auth/registrationForm";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginUserForm(Model model){

        model.addAttribute("message", "Login Form");
        return "/auth/loginForm";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginUserMethod(@ModelAttribute User loginUser, HttpServletRequest request,
                                  Model model){

        User loginedUser = userService.loginUser(loginUser);

        if (loginedUser.getId() == null) {
            model.addAttribute("message", "Login Failed, please try again!");
            return "/auth/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", loginedUser.getId());
        session.setAttribute("roleId", loginedUser.getRoleId());
        model.addAttribute("message", "Welcome To Home Page");

        switch (loginedUser.getRoleId()){
            case 1: return "redirect:/person/home";
            case 2: return "redirect:/author/home";
            case 3: return "redirect:/admin/home";
            case 4: return "redirect:/superAdmin/home";
        }

        return "redirect:/person/home";
    }
}

package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(path = "/home")
    public String getBooks() {

        return "views/user/home";
    }

    @GetMapping("/settings")
    public String editUserPrifile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        model.addAttribute("user", userService.getUserById(String.valueOf(userId)));
        return "views/user/editUsersProfile";
    }


    @PostMapping("/edit")
    public String saveUsersInfo(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPasswords = request.getParameter("confirmPassword");

        List<User> allusers = userService.getAllusers();
        for (User alluser : allusers) {
            if (alluser.getId() == Integer.parseInt(id)){
                alluser.setFullName(fullname);
                alluser.setEmail(email);
                alluser.setPassword(newPassword);
                userService.editUser(alluser);
            }
        }

        HttpSession session = request.getSession();
        int roleId = (int)session.getAttribute("roleId");

        switch (roleId){
            case 1: return "redirect:/person/home";
            case 2: return "redirect:/author/home";
            case 3: return "redirect:/admin/home";
            case 4: return "redirect:/superAdmin/home";
        }
        return "redirect:/user/home";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        return "redirect:/user/home";
    }
}

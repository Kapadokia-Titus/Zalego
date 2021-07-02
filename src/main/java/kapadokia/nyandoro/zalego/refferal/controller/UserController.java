package kapadokia.nyandoro.zalego.refferal.controller;

import kapadokia.nyandoro.zalego.refferal.model.AdminUser;
import kapadokia.nyandoro.zalego.refferal.model.Refer;
import kapadokia.nyandoro.zalego.refferal.model.User;
import kapadokia.nyandoro.zalego.refferal.service.FirebaseService;
import kapadokia.nyandoro.zalego.refferal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping()
public class UserController {
    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UserService userService;


    @RequestMapping("/users")
    public String getAll(Model model) throws ExecutionException, InterruptedException {
        List<User> users = firebaseService.getUsers();

        model.addAttribute("users", users);
        for (User user:users){
            System.out.println(user.hasImage);
        }

        AdminUser adminUser = userService.getAll();
        model.addAttribute("admin", adminUser);
        return "users";
    }

    @RequestMapping("/test")
    public String getTestClass(Model model) throws ExecutionException, InterruptedException {
        List<Refer> refers = firebaseService.getRefers();
        model.addAttribute("refers", refers);
        return "test";
    }



}

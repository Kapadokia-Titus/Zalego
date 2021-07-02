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

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/refers")
public class ReferredController {


    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private UserService userService;

    @RequestMapping()
    public String getAll(Model model) throws ExecutionException, InterruptedException {
        List<Refer> refers = firebaseService.getRefers();
        model.addAttribute("refers", refers);

        AdminUser adminUser = userService.getAll();
        model.addAttribute("admin", adminUser);
        return "refers";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Optional<Refer> getOne(String Id) {
        return firebaseService.getOne(Id);
    }
    @GetMapping("/approve/{id}")
    public String approveRef(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        firebaseService.approve(id);
        return "redirect:/refers";
    }
}

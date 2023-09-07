package com.spring_simpleform.controller;


import com.spring_simpleform.model.UsersModel;
import com.spring_simpleform.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

//    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage( Model model) {
        model.addAttribute( "registerRequest", new UsersModel() );  // This creates a null Object on html page to hold vales
        return "register_page";         // By default, spring boot  configure to serve all html pages under template
    }

    @GetMapping("/login")
    public String getLoginPage( Model model) {
        model.addAttribute( "loginRequest", new UsersModel() );   // This creates Object to hold values when the UI loads Html page
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel ) {
        // Log in (logs) are preferred but to simplify we use Syst.out.print();
        System.out.println( "register request: " + usersModel );
        UsersModel registeredUser = usersService.registerUser( usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail() );

        return registeredUser == null ?  "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model )
    {
        System.out.println("Login request : " + usersModel );
        UsersModel authenticated = usersService.authenticate( usersModel.getLogin(), usersModel.getPassword() );
        if( authenticated != null ){
            model.addAttribute( "userLogin", authenticated.getLogin() );
            return "personal_page";
        }else{
            return "error_page";
        }
    }

}

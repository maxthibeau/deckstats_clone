package com.example.deckstats_clone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/create")
    public @ResponseBody
    RedirectView addNewDeck (@RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String cards
                             )
    {
        Deck d = new Deck();
        d.setTitle(title);
        d.setAuthor(author);
        d.setCards(cards);
        deckRepository.save(d);
        return new RedirectView("/create_success");
    }

    @GetMapping("create_success")
    public String create_success(){
        return "create_success";
    }

    @GetMapping("/decks")
    public String decks(Model model){
        model.addAttribute("decks", deckRepository.findAll());
        return "decks";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public @ResponseBody RedirectView submitRegistration(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String psw
            ) {
        User u = new User();
        u.setName(username);
        u.setEmail(email);
        u.setPassword(psw);
        userRepository.save(u);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public @ResponseBody String attempt_login(@RequestParam String username,
                                @RequestParam String psw){
        Iterable<User> allUsers = userRepository.findAll();
        for (User user: allUsers){
            if (user.getName().equals(username) && user.getPassword().equals(psw)){
                return "successful login";
            }
        }
        return "attempt login";
    }
}

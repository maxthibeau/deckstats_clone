package com.example.deckstats_clone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    @Autowired
    private DeckRepository deckRepository;

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
    RedirectView addNewDeck (@RequestParam String title
            , @RequestParam String author,
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

    @GetMapping("/view")
    public @ResponseBody Iterable<Deck> getAllDecks(){
        return deckRepository.findAll();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

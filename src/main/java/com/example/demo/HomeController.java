package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired//Tells the compiler to instantiate the repository object when the application run, so you dont have to type out that line so many times!
    MessageRepository messageRepository;

    //Default Route
    //The user will see a list of all the course entires that have been made
    @RequestMapping
    public String listMessage(Model model){
        model.addAttribute("message", messageRepository.findAll());//FindAll- pulls all the data for a selected model from the database
        return "list";
    }


    // A new instance of the Message class will be created and passed to the view
    @GetMapping("/add")
    public String messgeFrom(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result){
        if (result.hasErrors())
            return "messageform";
        messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "detail";
    }

}

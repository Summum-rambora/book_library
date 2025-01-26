package com.book_library.book_library.controllers;


import com.book_library.book_library.dto.RegistrationDto;
import com.book_library.book_library.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "registration";
    }


    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute("registrationDto") RegistrationDto registrationDto) {
        registrationService.registerUser(registrationDto);
        return "redirect:/login";
    }


}

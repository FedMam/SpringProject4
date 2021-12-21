package com.fedormamaevv.SpringProjectTemp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParticipantController1 {
    @GetMapping("/participants")
    public String participants(Model model)
    {
        return "participants";
    }
}

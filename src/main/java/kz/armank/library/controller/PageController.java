package kz.armank.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("main")
    public String getMainPage() {
        return "main";
    }

    @GetMapping("index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/createDiv")
    public ResponseEntity<String> getCreateDiv () {
        return new ResponseEntity<>("Div created", HttpStatus.OK);
    }

}

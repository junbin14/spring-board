package com.project.board.author.controller;

import com.project.board.author.domain.Author;
import com.project.board.author.domain.AuthorDto;
import com.project.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/list")
    public String authorList(Model model) throws SQLException {
        model.addAttribute("authors", this.authorService.findAll());
        return "/author/authorList";
    }

    @GetMapping("/author/createform")
    public String createForm(){
        return "/author/authorCreate";
    }

    @PostMapping("/author/create")
    public String create(AuthorDto authorDto){
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setPassword(authorDto.getPassword());
        author.setRole(authorDto.getRole());
        author.setCreateDate(LocalDateTime.now());
        authorService.save(author);
        return "redirect:/";
    }
}

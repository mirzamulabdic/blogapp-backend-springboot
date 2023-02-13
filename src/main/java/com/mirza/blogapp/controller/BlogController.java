package com.mirza.blogapp.controller;

import com.mirza.blogapp.model.Blog;
import com.mirza.blogapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;


    @PostMapping("/add")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) throws Exception {
        Blog newBlog= blogService.addNewBlog(blog);
        return new  ResponseEntity<>(newBlog, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getBlogs() throws Exception {
        List<Blog> blogs= blogService.getAllBlogs();
        return new  ResponseEntity<>(blogs, HttpStatus.ACCEPTED);
    }
}

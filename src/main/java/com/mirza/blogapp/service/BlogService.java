package com.mirza.blogapp.service;

import com.mirza.blogapp.model.Blog;
import com.mirza.blogapp.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BlogService {

    private final BlogRepository blogRepository;
    public Blog addNewBlog(Blog blog) throws Exception{
        if (blog.getTitle()==null || blog.getCategory()== null||blog.getContent() == null){
            throw new RuntimeException("Please enter all fields");
        }
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}

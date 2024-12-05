package org.example.socialmediaapi.controller;

import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.manager.PostManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController implements Controller<PostRequest, PostResponse>{

    private final PostManager postManager;

    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    @Override
    @Validated
    @PostMapping("/save")
    public PostResponse save(@Valid @RequestBody PostRequest postRequest) {
        return postManager.save(postRequest);
    }

    @Override
    @Validated
    @PostMapping("/update/{id}")
    public PostResponse update(@PathVariable Long id, @Valid @RequestBody PostRequest newInfo) {
        return postManager.update(id, newInfo);
    }

    @Override
    @GetMapping("/delete/{id}")
    public PostResponse delete(@PathVariable Long id) {
        return postManager.delete(id);
    }

    @GetMapping("/get-by-id/{id}")
    public PostResponse getById(@PathVariable Long id){
        return postManager.getById(id);
    }

    @GetMapping("/get-all")
    public List<PostResponse> getAll(){
        return postManager.getAll();
    }

    @GetMapping("/get-all-comments-of/{id}")
    public PostResponse getAllCommentsOf(@PathVariable Long id){
        return postManager.getAllCommentsOfPost(id);
    }

}

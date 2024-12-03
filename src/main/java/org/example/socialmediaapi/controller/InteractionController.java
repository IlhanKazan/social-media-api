package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.manager.InteractionManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/interaction")
public class InteractionController implements Controller<InteractionRequest, InteractionResponse> {

    private final InteractionManager interactionManager;

    public InteractionController(InteractionManager interactionManager) {
        this.interactionManager = interactionManager;
    }

    @Override
    @Validated
    @PostMapping("/save")
    public InteractionResponse save(@Valid @RequestBody InteractionRequest request) {
        return interactionManager.save(request);
    }

    @Override
    @Validated
    @PostMapping("/update/{id}")
    public InteractionResponse update(@PathVariable Long id, @Valid @RequestBody InteractionRequest newInfo) {
        return interactionManager.update(id, newInfo);
    }

    @Override
    @GetMapping("/delete/{id}")
    public InteractionResponse delete(@PathVariable Long id) {
        return interactionManager.delete(id);
    }

    @GetMapping("/get-by-id/{id}")
    public InteractionResponse getById(@PathVariable Long id) {
        return interactionManager.getById(id);
    }

    @GetMapping("/get-all")
    public List<InteractionResponse> getAll() {
        return interactionManager.getAll();
    }

}

package com.nhnacademy.minidooray.task.backend.controller;

import com.nhnacademy.minidooray.task.backend.domain.CommentDto;
import com.nhnacademy.minidooray.task.backend.domain.CommentRequest;
import com.nhnacademy.minidooray.task.backend.domain.TaskIdOnlyRequest;
import com.nhnacademy.minidooray.task.backend.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final TaskService taskService;

    public CommentController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public List<CommentDto> getCommentList(@RequestBody TaskIdOnlyRequest request) {
        return taskService.findCommentListByTask(request.getId());
    }

    @PostMapping("/create")
    public void createComment(@Valid @RequestBody CommentRequest commentRequest) {
        taskService.createComment(commentRequest, commentRequest.getTaskId());
    }

    @PutMapping("/{commentId}/modify")
    public void modifyComment(@PathVariable("commentId")Long commentId,  @Valid @RequestBody CommentRequest commentRequest){
        //TODO
    }

    @DeleteMapping("/{commentId}/delete")
    public void deleteComment(){
        //TODO
    }
}
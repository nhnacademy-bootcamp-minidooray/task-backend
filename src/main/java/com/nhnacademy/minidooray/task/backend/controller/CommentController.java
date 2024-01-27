package com.nhnacademy.minidooray.task.backend.controller;

import com.nhnacademy.minidooray.task.backend.domain.CommentDto;
import com.nhnacademy.minidooray.task.backend.domain.CommentRequest;
import com.nhnacademy.minidooray.task.backend.domain.TaskIdOnlyRequest;
import com.nhnacademy.minidooray.task.backend.domain.requestbody.CommentModifyRequest;
import com.nhnacademy.minidooray.task.backend.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final TaskService taskService;

    public CommentController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommentDto>> getCommentList(@RequestBody TaskIdOnlyRequest request) {

        return ResponseEntity.ok().body(taskService.findCommentListByTask(request.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        boolean isProcessed = taskService.createComment(commentRequest, commentRequest.getTaskId());

        return isProcessed
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modifyComment(@RequestParam("commentId") Long commentId,
                                              @Valid @RequestBody CommentModifyRequest commentModifyRequest) {
        boolean isProcessed = taskService.modifyComment(commentId, commentModifyRequest);
        return isProcessed
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteComment(@RequestParam("commentId") Long commentId) {
        boolean isProcessed = taskService.deleteComment(commentId);

        return isProcessed
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();

    }
}
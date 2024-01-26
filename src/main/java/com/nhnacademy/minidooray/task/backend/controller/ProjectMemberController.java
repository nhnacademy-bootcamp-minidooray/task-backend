package com.nhnacademy.minidooray.task.backend.controller;

import com.nhnacademy.minidooray.task.backend.domain.dto.MemberIdOnlyDTO;
import com.nhnacademy.minidooray.task.backend.domain.requestbody.ProjectIdOnlyRequest;
import com.nhnacademy.minidooray.task.backend.domain.requestbody.ProjectMemberListRegisterRequest;
import com.nhnacademy.minidooray.task.backend.domain.requestbody.ProjectMemberRegisterRequest;
import com.nhnacademy.minidooray.task.backend.service.ProjectMemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MemberIdOnlyDTO>> getProjectMembers(@RequestBody ProjectIdOnlyRequest request){
        List<MemberIdOnlyDTO> memberList = projectMemberService.getProjectMembers(request);

        return memberList.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(memberList);

    }

    @PostMapping("/list/register")
    public ResponseEntity<Void> registerProjectMembers(@RequestBody ProjectMemberListRegisterRequest request){
        boolean isProcessed = projectMemberService.registerProjectMembers(request);

        return isProcessed
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> registerProjectMember(@RequestBody ProjectMemberRegisterRequest request){
        boolean isProcessed = projectMemberService.registerProjectMember(request);

        return isProcessed
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProjectMember(@RequestBody ProjectMemberRegisterRequest request){
        boolean isProcessed = projectMemberService.deleteProjectMember(request);

        return isProcessed
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}

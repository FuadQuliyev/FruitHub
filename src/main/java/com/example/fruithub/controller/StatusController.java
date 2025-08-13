package com.example.fruithub.controller;


import com.example.fruithub.dto.SystemStatus;
import com.example.fruithub.model.Status;
import com.example.fruithub.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@RequiredArgsConstructor
public class StatusController {

   private final StatusService statusService;

    @GetMapping
    public List<Status> getStatus(){
        return statusService.getStatus();
    }

    @PostMapping("/add")
    public void addStatus(@RequestBody  SystemStatus statusDto){
        statusService.addStatus(statusDto);
    }

}

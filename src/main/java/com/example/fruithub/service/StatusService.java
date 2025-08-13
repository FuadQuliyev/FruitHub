package com.example.fruithub.service;

import com.example.fruithub.dto.SystemStatus;
import com.example.fruithub.model.Status;

import java.util.ArrayList;
import java.util.List;

public interface StatusService {
    List<Status> getStatus();
    void addStatus(SystemStatus statusDto);
}

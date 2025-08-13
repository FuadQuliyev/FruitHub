package com.example.fruithub.service;

import com.example.fruithub.dto.SystemStatus;
import com.example.fruithub.exception.StatusHandlerException;
import com.example.fruithub.model.Status;
import com.example.fruithub.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService{

    private final StatusRepository statusRepository;

    @Override
    public List<Status> getStatus() {
        return new ArrayList<>(statusRepository.findAll());
    }

    @Override
    public void addStatus(SystemStatus statusDto) {
        Optional<Status> status = statusRepository.findByName(statusDto.getName());
        if (status.isPresent()){
            throw new StatusHandlerException("Status already exists");
        }else {
            Status newStatus = new Status();
            newStatus.setName(statusDto.getName());
            newStatus.setText(statusDto.getDescription());
            statusRepository.save(newStatus);
        }
    }
}

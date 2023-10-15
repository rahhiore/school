package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@Service
public class InfoService {
    @Value("${server.port}")
    private Integer port;
    @GetMapping("/getPort")
    public Integer getPort() {
        return port;
    }
}

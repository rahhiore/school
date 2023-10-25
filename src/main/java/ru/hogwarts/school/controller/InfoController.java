package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;


@RequestMapping("/info")
@RestController
public class InfoController {
    private final InfoService infoService;
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }
    @GetMapping("/getPort")
    public Integer getPort() {
        return infoService.getPort();
    }
}

package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.service.BootcampService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final BootcampService bootcampService;

    @GetMapping("/")
    public String indexView(Model model) {

        List<List<BootcampDto>> list = bootcampService.getBootcampList();

        List<BootcampDto> campList = list.get(0);

        List<BootcampDto> devField = list.get(1);

        model.addAttribute("campList", campList);
        model.addAttribute("fieldList", devField);

        return "index";
    }
}

package com.qbang.stockpedia.controller;

import com.qbang.stockpedia.domain.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @GetMapping("/vue")
    public TestVO vue() {
        TestVO result = new TestVO();
        result.setData("ㅎㅎㅎㅎㅎ");
        result.setSuccess(true);
        return result;
    }
}

package com.yonatankarp.petclinic.contorllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final String INDEX_PAGE = "index";
    private static final String NOT_IMPLEMENTED_PAGE = "not_implemented";

    @RequestMapping({ "/", "index", "index.html"})
    public String index() {
        return INDEX_PAGE;
    }

    @RequestMapping("/oups")
    public String oupsHandler() {
        return NOT_IMPLEMENTED_PAGE;
    }
}

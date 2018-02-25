package springwiki.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springwiki.demo.entities.Page;
import springwiki.demo.services.PageService;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/page/create", method = RequestMethod.GET)
    public String createPage(){
        return "pages/create";
    }

    @ResponseBody
    @RequestMapping(value = "/page/create", method = RequestMethod.POST)
    public String createPages(@RequestBody Page page){

        pageService.create(page);
        return "done";
    }

    @ResponseBody
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public List<Page> pages(){
        return pageService.findAll();
    }







    @ResponseBody
    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String rrrrr(){

        return "Hello, I am private";
    }



}

package springwiki.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springwiki.demo.entities.Page;
import springwiki.demo.services.PageService;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    PageService pageService;

//    @Autowired
//    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public List<Page> pages(){
        return pageService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/page/create", method = RequestMethod.POST)
    public String createPage(@RequestBody Page page){


//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        page.setCreator(userService.getUser(userDetails.getUsername()));

        pageService.create(page);
        return "done";
    }



}

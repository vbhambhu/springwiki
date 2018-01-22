package springwiki.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import springwiki.demo.entities.AjaxResponse;
import springwiki.demo.entities.Page;
import springwiki.demo.repositories.CategoryRepository;
import springwiki.demo.repositories.PageRepository;

import javax.validation.Valid;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@RestController
public class APIController {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @ResponseBody
    @RequestMapping(value = "api/page/create", method = RequestMethod.POST)
    public AjaxResponse createNewPage(@Valid Page page, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {

            for(ObjectError error: bindingResult.getAllErrors()){

                System.out.println(error);
            }



            //System.out.println("there is error in form");
        }


        System.out.println(page.getTitle());

        List<String> errors = new ArrayList<String>();

//        String slug = toSlug(title);
//
//        Category category = categoryRepository.findOne(Long.valueOf(1));
//
//        Page page = new Page();
//        page.setTitle(title);
//        page.setSlug(slug);
//        page.setCategory(category);
//        page.setContent("");
//        pageRepository.save(page);

        return new AjaxResponse(true, "ddd");

    }

    public static String toSlug(String input) {

        Pattern WHITESPACE = Pattern.compile("[\\s]");
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}

package springwiki.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springwiki.demo.SiteHelper;
import springwiki.demo.entities.Category;
import springwiki.demo.entities.Post;
import springwiki.demo.services.CategoryService;
import springwiki.demo.services.PostService;

import javax.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }


    @RequestMapping(value = "/admin/category/create", method = RequestMethod.GET)
    public String createPost(Model model, Category category){

        model.addAttribute("cats", categoryService.findAll());
//        String jsFiles[] = {"select2.min.js","tinymce/tinymce.min.js"};
//        model.addAttribute("jsFiles", jsFiles);

        return "categories/create";
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public String validateAndSavePost(Model model, @Valid Category category, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("cats", categoryService.findAll());
//            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
//            model.addAttribute("jsFiles", jsFiles);
            return "categories/create";
        }


        categoryService.create(category);
        return "redirect:/admin/category/list";

    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model){

//        String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
//        model.addAttribute("jsFiles", jsFiles);

        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("cats", categoryService.findAll());
        return "categories/edit";
    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Category category, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){


        if(bindingResult.hasErrors()){
//            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
//            model.addAttribute("jsFiles", jsFiles);
            model.addAttribute("cats", categoryService.findAll());
            return "categories/edit";
        }




//        Post articleDb = postService.getById(id);
//        post.setId(articleDb.getId());
//        post.setCreatedAt(articleDb.getCreatedAt());
//        post.setAuthor(articleDb.getAuthor());
//        postService.update(post);
        return "redirect:/admin/category/list";


    }


}

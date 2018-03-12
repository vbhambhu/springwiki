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
import springwiki.demo.entities.Post;
import springwiki.demo.services.PostService;

import javax.validation.Valid;

@Controller
public class PostController {

    @Autowired
    PostService postService;


    @RequestMapping(value = "/admin/post/list", method = RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    @RequestMapping(value = "/admin/post/create", method = RequestMethod.GET)
    public String createPost(Model model, Post post){


        model.addAttribute("cats", postService.getAllCategories());
        String jsFiles[] = {"select2.min.js","tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        return "posts/create";
    }

    @RequestMapping(value = "/admin/post/create", method = RequestMethod.POST)
    public String validateAndSavePost(Model model, @Valid Post post, BindingResult bindingResult){

        if(bindingResult.hasErrors()){


            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("cats", postService.getAllCategories());
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            return "posts/create";
        }


        System.out.println("Create poist");

        postService.create(post);
        return "redirect:/admin/post/list";

    }

    @RequestMapping(value = "/admin/post/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model){

        String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        Post article = postService.getById(id);
        model.addAttribute("post", article);
        model.addAttribute("cats", postService.getAllCategories());
        return "posts/edit";
    }

    @RequestMapping(value = "/admin/post/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Post post, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){


        post.setSlug(SiteHelper.encodeForUrl(post.getSlug()));

        if(postService.isDuplicateSlug(post)){
            bindingResult.rejectValue("slug", "article.slug","Slug already exists.");
        }


        if(bindingResult.hasErrors()){
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            model.addAttribute("cats", postService.getAllCategories());
            return "posts/edit";
        }




        Post articleDb = postService.getById(id);
        post.setId(articleDb.getId());
        post.setCreatedAt(articleDb.getCreatedAt());
        post.setAuthor(articleDb.getAuthor());
        postService.update(post);
        return "redirect:/admin/post/list";


    }


    @RequestMapping(value = "/admin/post/delete", method = RequestMethod.POST)
    public String saveEditedPost(Post post,RedirectAttributes redirAttrs){

       postService.delete(post);

        redirAttrs.addFlashAttribute("message", "Post deleted successfully!");
        return "redirect:/admin/post/list";

    }
}

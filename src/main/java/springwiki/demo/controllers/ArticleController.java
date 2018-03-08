package springwiki.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springwiki.demo.SiteHelper;
import springwiki.demo.entities.Article;
import springwiki.demo.services.ArticleService;

import javax.validation.Valid;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("articles", articleService.getAllPosts());
        return "articles/list";
    }

    @RequestMapping(value = "/article/create", method = RequestMethod.GET)
    public String createPost(Model model, Article article){


        model.addAttribute("cats", articleService.getAllCategories());
        String jsFiles[] = {"select2.min.js","tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        return "articles/create";
    }

    @RequestMapping(value = "/article/create", method = RequestMethod.POST)
    public String saveCreatedPost(Model model, @Valid Article article, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("cats", articleService.getAllCategories());
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            return "articles/create";
        }

        articleService.create(article);
        return "redirect:/articles";

    }

    @RequestMapping(value = "/article/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam Long id, Model model){

        String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
        model.addAttribute("jsFiles", jsFiles);

        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        model.addAttribute("cats", articleService.getAllCategories());
        return "articles/edit";
    }

    @RequestMapping(value = "/article/edit", method = RequestMethod.POST)
    public String saveEditedPost(@RequestParam Long id,
                                 Model model,
                                 @Valid Article article, BindingResult bindingResult,
                                 RedirectAttributes redirAttrs){


        article.setSlug(SiteHelper.encodeForUrl(article.getSlug()));

        if(articleService.isDuplicateSlug(article)){
            bindingResult.rejectValue("slug", "article.slug","Slug already exists.");
        }


        if(bindingResult.hasErrors()){
            String jsFiles[] = {"select2.min.js", "tinymce/tinymce.min.js"};
            model.addAttribute("jsFiles", jsFiles);
            model.addAttribute("cats", articleService.getAllCategories());
            return "articles/edit";
        }


        redirAttrs.addFlashAttribute("message", "This is message from flash");

        Article articleDb = articleService.getById(id);
        article.setId(articleDb.getId());
        article.setCreatedAt(articleDb.getCreatedAt());
        article.setAuthor(articleDb.getAuthor());
        articleService.update(article);
        return "redirect:/articles";


    }
}

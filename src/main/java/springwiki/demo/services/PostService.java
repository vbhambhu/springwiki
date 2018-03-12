package springwiki.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springwiki.demo.SiteHelper;
import springwiki.demo.entities.Post;
import springwiki.demo.entities.Category;
import springwiki.demo.entities.User;
import springwiki.demo.repositories.PostRepository;
import springwiki.demo.repositories.CategoryRepository;
import springwiki.demo.repositories.UserRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;


    public List<Post> getAllPosts() {
        return articleRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void create(Post article) {

        User user = userRepository.findByUsername("admin");

        article.setCreatedAt(new Date());
        article.setAuthor(user);
        articleRepository.save(article);
    }

    public Post getById(Long id) {

        return articleRepository.findOne(id);
    }

    public void update(Post article) {

        article.setUpdatedAt(new Date());
        articleRepository.save(article);
    }

    public String uploadFile(MultipartFile file){

        List<String> allowedFileTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

        String filename = file.getOriginalFilename();

        System.out.println(filename);

        if(filename.isEmpty()){
            return null;
        }

        String extension = filename.substring(filename.lastIndexOf("."), filename.length());
        String filepath = Paths.get(uploadPath, filename).toString();
        String fileContentType = file.getContentType();


        if(!allowedFileTypes.contains(fileContentType)){
            return null;
        }

        File file1 = new File(filepath);
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file1));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String newFileName = SiteHelper.encodeForUrl(filename)+"."+extension;
        String newfilepath = Paths.get(uploadPath, newFileName).toString();
        file1.renameTo(new File(newfilepath));

        return newFileName;

    }

    public boolean isDuplicateSlug(Post article) {

        return (articleRepository.findBySlugAndIdNot(article.getSlug(),article.getId()) == null) ? false : true;

    }

    public List<Post> getAllByHome() {
        return articleRepository.findAll();
    }

    public List<Post> findbyKeyword(String query) {

        if(query == null || query.length() == 0) return null;

        return articleRepository.findByTitleContaining(query);
    }

    public Post getBySlug(String slug) {

        return articleRepository.findBySlug(slug);
    }

    public void delete(Post post) {
        articleRepository.delete(post);
    }
}

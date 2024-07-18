package service;

import model.Blog;
import model.BlogComment;
import model.User;
import model.enums.BlogStatus;
import repository.BlogRepository;
import repository.UserRepository;

import java.util.List;

public class BlogService {
    private BlogRepository blogRepository ;
    private UserService userService;

    public BlogService(BlogRepository blogRepository,UserService userService) {
        this.blogRepository = blogRepository;
        this.userService = userService;
    }

    public Blog createBlog(String title, String text, String email) {

        User foundUser=userService.getUserByEmail(email);

        Blog blog = new Blog(title, text, foundUser);

        blogRepository.save(blog);

        return blog;

    }

    public Blog getBlogByTitle(String title) {


        return blogRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Blog Bulunamadı.."));
    }

    public void addCommets(String title, String commet, User user) {
        Blog foundBlog = getBlogByTitle(title);
        foundBlog.getBlogCommentList().add(new BlogComment(user, commet));

        //Log Eklenecek
    }

    public List<Blog> getBlogsFilterByStatus(BlogStatus blogStatus,String email){
        UserService userService=new UserService(new UserRepository());

        User foundUser=userService.getUserByEmail(email);

        return foundUser.getBlogList().stream()
                .filter(blog->blogStatus.equals(blog.getBlogStatus()))
                .toList();
    }


    public void changeBlogStatus(BlogStatus blogStatus, String title) {
       Blog foundBlog= getBlogByTitle(title);

       if(foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)){
           throw new RuntimeException("Statusu Publish Edilen Bir Blog Silinemez.");
       }


        //Log Eklenecek
       foundBlog.setBlogStatus(blogStatus);

    }
}

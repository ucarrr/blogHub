package model;

import model.enums.BlogStatus;
import model.enums.StatusType;
import repository.BlogRepository;
import repository.UserRepository;
import service.BlogService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService(new UserRepository());

        userService.saveUser("ucar@gmail.com", "123456");
        userService.saveUser("mehmet@mail.com", "123456");
        userService.saveUser("test@mail.com", "123456");

        BlogService blogService = new BlogService(new BlogRepository(), userService);


        userService.changeStatus("ucar@gmail.com", StatusType.APPROVED);
        userService.changeStatus("mehmet@mail.com", StatusType.APPROVED);
        userService.changeStatus("test@mail.com", StatusType.APPROVED);

        userService.changeStatus(List.of("test@mail.com","mehmet@mail.com","test@mail.com"),StatusType.APPROVED);

        userService.getAllUsers().forEach(System.out::println);

        userService.getAllUsers()
                .stream()
                .peek(System.out::println)
                .filter(user->user.getEmail().equals("mehmet@mail.com"))
                .toList();

        //Örnek Stream ve Map Kullanımı
        List<String> emailList = userService.getAllUsers()
                .stream()
                .map(User::getEmail)
                .toList();

        List<Blog> allUsersBlogList = userService.getAllUsers()
                .stream()
                .flatMap(user -> user.getBlogList().stream())
                .toList();

        Map<String, User> emailUserMap = userService.getAllUsersMap();

        User testUser= emailUserMap.get("test@mail.com");

        User foundUser = userService.getUserByEmail("ucar@gmail.com");



        blogService.createBlog("Başlık", "İçerik", "ucar@gmail.com");

        Blog foundBlog = blogService.getBlogByTitle("Başlık");

        blogService.addCommets("Başlık", "eline sağlık", foundUser);


//        List<Blog> blogList = new ArrayList<>();
//        List<Blog> drawBlogList = new ArrayList<>();
//        List<Blog> publisBlogList = new ArrayList<>();
//
//        Blog blog1 = new Blog("test title", "test text", user1);
//
//        blog1.getBlogCommentList().add(new BlogComment(user1, "Deneme Yorum"));
//
//        blogList.add(blog1);
//
//        user1.setBlogList(List.of(blog1));
//
//        List<Blog> userBlogList = user1.getBlogList();
//
//        for (Blog userBlog : userBlogList) {
//            if (BlogStatus.DRAFT.equals(userBlog.getBlogStatus())) {
//                drawBlogList.add(userBlog);
//            } else if (BlogStatus.PUBLISHED.equals(userBlog.getBlogStatus())) {
//                publisBlogList.add(userBlog);
//            }
//        }
//
//
//        // --
//
//        List<Blog> userDraftList = foundUser.getBlogList().stream().filter(it -> BlogStatus.DRAFT.equals(it.getBlogStatus())).toList();
//        List<Blog> userPublishList = foundUser.getBlogList().stream().filter(it -> BlogStatus.PUBLISHED.equals(it.getBlogStatus())).toList();
//
//        user1.getFollowedTagList().add(new BlogTag("Space"));
//        user1.getFollowedTagList().add(new BlogTag("Programming"));


//        Optional<Blog> instacioBlog = blogList.stream()
//                .filter(blogs1 -> blogs1.getTitle().equals("Instancio: A New Way to Create Test Data"))
//                .findFirst();

        //ödev kullanıcının takip ettiği taglere göre bloglar gelmeli

//        Blog blog2 = instacioBlog.orElseThrow(() -> new RuntimeException("bu isimde bir blog bulunamadı"));
//
//        if (instacioBlog.isEmpty()) {
//            System.out.println("bu isimde bir blog bulunamadı");
//            throw new RuntimeException("bu isimde bir blog bulunamadı");
//        }
//
//        Optional<Blog> willBeDraftBlog = user1.getBlogList()
//                .stream()
//                .filter(blog3 -> blog3.getTitle().equals("Instancio: A New Way to Create Test Data"))
//                .findFirst();
//
//
//        if (willBeDraftBlog.isEmpty()) {
//            System.out.println("bu isimde bir blog bulunamadı");
//            throw new RuntimeException("bu isimde bir blog bulunamadı");
//        }
//
//        if (BlogStatus.PUBLISHED.equals(willBeDraftBlog.get().getBlogStatus())) {
//            System.out.println("published olan blog delete edilemez. önce draft yapın.");
//            throw new RuntimeException("published olan blog delete edilemez. önce draft yapın.");
//        }
//
//        willBeDraftBlog.get().setBlogStatus(BlogStatus.DRAFT);

        blogService.changeBlogStatus(BlogStatus.DELETED, "Başlık");


    }
}

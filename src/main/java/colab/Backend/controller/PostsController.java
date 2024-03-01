package colab.Backend.controller;

import colab.Backend.dto.CommentDto;
import colab.Backend.dto.PostDto;
import colab.Backend.service.PostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8080") }, info = @Info(title = "Sample Spring Boot API", version = "v1", description = "A demo project using Spring Boot with Swagger-UI enabled", license = @License(name = "MIT License", url = "https://github.com/bchen04/springboot-swagger-rest-api/blob/master/LICENSE"), contact = @Contact(url = "https://www.linkedin.com/in/bchen04/", name = "Ben Chen")))
@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    PostService postService;



    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> postList = postService.getAllPosts();

        postList.forEach(System.out::println);
        return  ResponseEntity.ok(postList);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") int postId,
                                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "per-page", defaultValue = "1") int perPage) {

        List<CommentDto> commentsList = postService.getCommentsForPost(postId,page,perPage);


        return ResponseEntity.ok(commentsList);
    }



}

package blog.blog_api.DTO;

import blog.blog_api.model.Comment;
import blog.blog_api.model.Post;
import blog.blog_api.model.Tag;
import blog.blog_api.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BlogDTO {


    // ─── USER ───────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class UserRequest {
        @NotBlank(message = "Le username est obligatoire")
        private String username;

        @Email(message = "L'email n'est pas valide")
        @NotBlank(message = "L'email est obligatoire")
        private String email;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class UserResponse {
        private Long userId;
        private String username;
        private String email;
    }

    // ─── POST ───────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class PostRequest {
        @NotBlank(message = "Le titre est obligatoire")
        private String title;

        @NotBlank(message = "Le contenu est obligatoire")
        private String content;

        @NotNull(message = "L'userId est obligatoire")
        private Long userId;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class PostResponse {
        private Long postId;
        private String title;
        private String content;
        private Long userId;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<TagResponse> tags;
    }

    // ─── COMMENT ────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CommentRequest {
        @NotBlank(message = "Le contenu est obligatoire")
        private String commentContent;

        @NotNull(message = "Le postId est obligatoire")
        private Long postId;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CommentResponse {
        private Long commentId;
        private String commentContent;
        private Long postId;
        private String title;
    }

    // ─── TAG ────────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TagRequest {
        @NotBlank(message = "Le nom du tag est obligatoire")
        private String name;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TagResponse {
        private Long tagId;
        private String name;
    }



    // ─── MAPPING STATIC ─────────────────────
    public static User toUserModel(UserRequest dto) {
        return new User(
                null,
                dto.getUsername(),
                dto.getEmail());
    }

    public static UserResponse toUserResponse(User model) {
        return new UserResponse(
                model.getUserId(),
                model.getUsername(),
                model.getEmail());
    }

    public static Post toPostModel(PostRequest dto) {
        return new Post(
                null,
                dto.getTitle(),
                dto.getContent(),
                null,null,null,null,
                new ArrayList<>());
    }

    public static PostResponse toPostResponse(Post model) {
        return new PostResponse(
                model.getPostId(),
                model.getTitle(),
                model.getContent(),
                model.getUser().getUserId(),
                model.getUser().getUsername(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getTags().stream().map(BlogDTO::toTagResponse).collect(Collectors.toList()));
    }

    public static Comment toCommentModel(CommentRequest dto) {
        return new Comment(
                null,
                dto.getCommentContent(),
                null);
    }
    public static CommentResponse toCommentResponse(Comment model) {
        return new CommentResponse(
                model.getCommentId(),
                model.getCommentContent(),
                model.getPost().getPostId(),
                model.getPost().getTitle()
        );
    }

    // Mapping static
    public static Tag toTagModel(TagRequest dto) {
        return new Tag(null, dto.getName());
    }

    public static TagResponse toTagResponse(Tag model) {
        return new TagResponse(model.getTagId(), model.getName());
    }

}

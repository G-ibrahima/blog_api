package blog.blog_api.repository;

import blog.blog_api.model.Post;
import blog.blog_api.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    @BeforeEach
    void setUp() {
        // Crée un User avant chaque test
        user = new User(null, "ibrahima", "ibrahima@gmail.com");
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    void save_devraitSauvegarderUnPost() {
        // Arrange
        Post post = new Post(
                null,
                "Mon titre",
                "Mon contenu",
                user,
                null, null, null,
                new ArrayList<>());

        // Act
        Post saved = postRepository.save(post);

        // Assert
        assertNotNull(saved.getPostId());
        assertEquals("Mon titre", saved.getTitle());
        assertEquals(user.getUserId(), saved.getUser().getUserId());
    }
    @Test
    void findById_devraitRetournerLePost() {
        // Arrange
        Post post = new Post(
                null,
                "Mon titre",
                "Mon contenu", user,
                null, null, null,
                new ArrayList<>());
        entityManager.persist(post);
        entityManager.flush();

        // Act
        Post found = postRepository.findById(post.getPostId()).orElse(null);

        // Assert
        assertNotNull(found);
        assertEquals("Mon titre", found.getTitle());
    }

    @Test
    void delete_devraitSoftDeleterLePost() {
        // Arrange
        Post post = new Post(
                null,
                "Mon titre",
                "Mon contenu",
                user, null, null, null,
                new ArrayList<>());
        entityManager.persist(post);
        entityManager.flush();

        // Act
        postRepository.deleteById(post.getPostId());

        // Assert — le post ne doit plus apparaître
        List<Post> posts = postRepository.findAll();
        assertEquals(0, posts.size()); // ✅ invisible grâce à @Where
    }
}
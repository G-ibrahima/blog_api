package blog.blog_api.service;


import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public List<BlogDTO.UserResponse> getAllUsers() {
        return users.stream()
                .map(BlogDTO::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.UserResponse getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .map(BlogDTO::toUserResponse)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User non trouvé !"));
    }

    @Override
    public BlogDTO.UserResponse createUser(BlogDTO.UserRequest request) {
        User user = BlogDTO.toUserModel(request);
        user.setUserId((long) users.size() + 1);
        users.add(user);
        return BlogDTO.toUserResponse(user);
    }

    @Override
    public BlogDTO.UserResponse updateUser(Long id, BlogDTO.UserRequest request) {
        for (User u : users) {
            if (u.getUserId().equals(id)) {
                u.setUsername(request.getUsername());
                u.setEmail(request.getEmail());
                return BlogDTO.toUserResponse(u);
            }
        }
        throw new RuntimeException("User non trouvé !");
    }

    @Override
    public void deleteUser(Long id) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(id));
        if (!removed) throw new RuntimeException("User non trouvé !");
    }
}

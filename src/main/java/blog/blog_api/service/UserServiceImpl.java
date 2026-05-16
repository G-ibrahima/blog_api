package blog.blog_api.service;


import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.User;
import blog.blog_api.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // base de données

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<BlogDTO.UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(BlogDTO::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User non trouvé !"));
        return BlogDTO.toUserResponse(user);
    }

    @Override
    @Transactional
    public BlogDTO.UserResponse createUser(BlogDTO.UserRequest request) {
        User user = BlogDTO.toUserModel(request);
        return BlogDTO.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public BlogDTO.UserResponse updateUser(Long id, BlogDTO.UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User non trouvé !"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return BlogDTO.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User non trouvé !");
        }
        userRepository.deleteById(id);
    }
}

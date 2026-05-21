package blog.blog_api.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

     UserDetails loadUserByUsername(String email);
}

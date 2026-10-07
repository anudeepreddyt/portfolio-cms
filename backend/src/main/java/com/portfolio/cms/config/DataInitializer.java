package com.portfolio.cms.config;
import com.portfolio.cms.entity.User; import com.portfolio.cms.repository.UserRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seedAdmin(UserRepository repo,PasswordEncoder encoder,@Value("${app.admin.email}") String email,@Value("${app.admin.password}") String password){return args->{if(repo.findByEmail(email.toLowerCase()).isEmpty()){User u=new User();u.setEmail(email.toLowerCase());u.setPasswordHash(encoder.encode(password));u.setRole("ADMIN");repo.save(u);}};}
}

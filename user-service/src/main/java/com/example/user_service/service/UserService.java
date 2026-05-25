/*
package com.example.user_service.service;

import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}

 */

package com.example.user_service.service;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.dto.LoginResponse;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.entity.User;
import com.example.user_service.exception.EmailAlreadyExistsException;
import com.example.user_service.exception.InvalidPasswordException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.security.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository repository,BCryptPasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    public UserResponse saveUser(UserRequest request) {

        Optional<User> existingUser =
                repository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {

            throw new EmailAlreadyExistsException(
                    "Email already registered"
            );
        }









        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        User savedUser = repository.save(user);

        return new UserResponse(
                savedUser.getId(),
               savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public List<UserResponse> getAllUsers() {

        List<User> users = repository.findAll();

        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
/*
    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        return "Login Successful";
    }

 */
//public String login(LoginRequest request) {

    public LoginResponse login(LoginRequest request){
    User user = repository.findByEmail(request.getEmail())


            .orElseThrow(() ->
                    new UserNotFoundException("User not found")
            );

        //    .orElseThrow(() ->
        //            new RuntimeException("User not found")
        //    );

    boolean passwordMatches =
            passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
            );

    if (!passwordMatches) {
       // throw new RuntimeException("Invalid password");

        throw new InvalidPasswordException(
                "Invalid password"
        );


    }

   // return jwtUtil.generateToken(user.getEmail());  ... raw form token generate..

        String token =
                jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                "Bearer",
                user.getEmail()
        );

    }

    public UserResponse getCurrentUser(
            Authentication authentication) {

        String email = authentication.getName();

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


}
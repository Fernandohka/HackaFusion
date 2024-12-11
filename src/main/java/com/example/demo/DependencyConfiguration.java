package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.Token;
import com.example.demo.services.AbilityService;
import com.example.demo.services.AnswerService;
import com.example.demo.services.AuthService;
import com.example.demo.services.CareerService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ChatService;
import com.example.demo.services.ForumService;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.JWTService;
import com.example.demo.services.NotificationService;
import com.example.demo.services.ProjectFeedbackService;
import com.example.demo.services.ProjectMessageService;
import com.example.demo.services.ProjectService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.TopicMessageService;
import com.example.demo.services.TopicService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.AbilityImpl;
import com.example.demo.services.impl.AnswerImpl;
import com.example.demo.services.impl.AuthImpl;
import com.example.demo.services.impl.BcryptImpl;
import com.example.demo.services.impl.CarrerImpl;
import com.example.demo.services.impl.CategoryImpl;
import com.example.demo.services.impl.ChatImpl;
import com.example.demo.services.impl.ForumImpl;
import com.example.demo.services.impl.ImageStorageImplementation;
import com.example.demo.services.impl.JwtImplementation;
import com.example.demo.services.impl.NotificationImpl;
import com.example.demo.services.impl.ProjectFeedbackImpl;
import com.example.demo.services.impl.ProjectImpl;
import com.example.demo.services.impl.ProjectMessageImpl;
import com.example.demo.services.impl.QuestionImpl;
import com.example.demo.services.impl.TopicImpl;
import com.example.demo.services.impl.TopicMessageImpl;
import com.example.demo.services.impl.UserImpl;

@Configuration
public class DependencyConfiguration {
    // @Bean
    // public service service(){
    //     return new implementation();
    // }

    @Bean
    public UserService userService(){
        return new UserImpl();
    }

    @Bean
    public AuthService authService(){
        return new AuthImpl();
    }

    @Bean
    public ImageStorageService imageStorageService(){
        return new ImageStorageImplementation();
    }

    @Bean
    public JWTService<Token> jwtService(){
        return new JwtImplementation();
    }

    @Bean
    public BcryptImpl bcryptImpl(){
        return new BcryptImpl();
    }

    @Bean
    public AbilityService abilityService(){
        return new AbilityImpl();
    }

    @Bean
    public CareerService careerService(){
        return new CarrerImpl();
    }

    @Bean
    public ChatService chatService(){
        return new ChatImpl();
    }

    @Bean
    public ProjectService projectService(){
        return new ProjectImpl();
    }

    @Bean 
    public ProjectMessageService projectMessageService(){
        return new ProjectMessageImpl();
    }

    @Bean 
    public ProjectFeedbackService projectFeedbackService(){
        return new ProjectFeedbackImpl();
    }

    @Bean 
    public AnswerService answerService(){
        return new AnswerImpl();
    }

    @Bean 
    public ForumService forumService(){
        return new ForumImpl();
    }

    @Bean 
    public QuestionService questionService(){
        return new QuestionImpl();
    }

    @Bean
    public TopicService topicService(){
        return new TopicImpl();
    }

    @Bean
    public TopicMessageService topicMessageService(){
        return new TopicMessageImpl();
    }

    @Bean
    public NotificationService notificationService(){
        return new NotificationImpl();
    }

    @Bean
    public CategoryService categoryService(){
        return new CategoryImpl();
    }

}

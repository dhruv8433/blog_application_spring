package com.blog.blog_application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.blog_application.model.User;
import com.blog.blog_application.payload.UserDto;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Prevent ModelMapper from mapping 'id' when converting DTO -> Entity
		modelMapper.typeMap(UserDto.class, User.class)
				.addMappings(mapper -> mapper.skip(User::setId));

		return modelMapper;
	}

}

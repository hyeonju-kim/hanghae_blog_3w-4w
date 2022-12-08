package com.example.hanghae_blog.entity;///

import com.example.hanghae_blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String username;

	@OneToMany(mappedBy = "posts")
	private List<Comment> commentList = new ArrayList<>();

	@ManyToOne
	private User users;

	// @Entity 어노테이션 붙은 클래스는 기본생성자가 필수!!
	public Post(PostRequestDto requestDto, String username) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.username = username;
	}

	public void update(PostRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.username = this.users.getUsername();
	}
}

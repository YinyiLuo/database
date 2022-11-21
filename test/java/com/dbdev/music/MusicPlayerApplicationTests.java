package com.dbdev.music;

import com.dbdev.music.controller.AlbumController;
import com.dbdev.music.controller.CheckController;
import com.dbdev.music.controller.CommentController;
import com.dbdev.music.controller.TrackController;
import com.dbdev.music.domain.Comment;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.repository.*;
import com.dbdev.music.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@SpringBootTest
class MusicPlayerApplicationTests {
	@Autowired
	HttpServletResponse response;

	@Autowired
	SysUserRepository sysUserRepository;

	@Autowired
	TrackController trackController;

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	AlbumRepository albumRepository;

	@Autowired
	TrackRepository trackRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentController commentController;

	@Autowired
	AlbumController albumController;

	@Autowired
	CommentService commentService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CheckController checkController;

	@Autowired
	ListenRepository listenRepository;

	@Autowired
	OpenRepository openRepository;

	@SuppressWarnings("all")
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	String from;

	@Value("2135246743@qq.com")
	String to;

	@Test
	public void generatePassword() {
		System.out.println(passwordEncoder.encode("password1"));
	}

	@Test
	void testComment() throws Exception {
		System.out.println(objectMapper.writeValueAsString(commentController.getComments(2L, 0, 8)));
	}

	@Test
	void TestEx()
	{
		String uploadPath = "src/main/resources/static/music";
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists())
		{
			System.out.println("不存在");
			System.out.println(uploadDir.mkdir());
		}
		System.out.println("hello");
	}

	/*@Test
	void TestPage()
	{
		Page<Album> yiranfantexi = albumRepository.findByNameLike("%iranfantexi", PageRequest.of(0, 1));
		for (Album album : yiranfantexi) {
			System.out.println(album);
		}
		//System.out.println("yiranfantexi");
	}*/

	@Test
	void testCS()
	{
		System.out.println(commentService.getComments(2L, PageRequest.of(0, 5)));
	}
	@Test
	void contextLoads() {
		String password = passwordEncoder.encode("password1");
//		sysUserRepository.save(
//				SysUser.builder()
//						.name("zhangsan")
//						.password(password)
//						.role("ROLE_STUDENT")
//						.build()
//		);
	}

//	@Test
//	void testSimpleSend() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(from);
//		message.setTo(to);
//		message.setSubject("简单邮件");
//		message.setText("您的验证码为123456");
//		System.out.println(message);
//		mailSender.send(message);
//	}

	@Test
	void testNew() throws Exception {
//		System.out.println(objectMapper.writeValueAsString(albumController.removeAlbum(1579857861113155584L)));
//		System.out.println(objectMapper.writeValueAsString(listenRepository
//				.findByUserId(1571829035812655104L, PageRequest.of(0, 10))));
//		System.out.println(objectMapper.writeValueAsString(openRepository
//				.findByUserId(1571829035812655104L, PageRequest.of(0, 10))));
//		System.out.println(trackController.getSongFile(UUID.fromString("98064504-ae9b-42f0-8392-6fe9cef9ba34"), response));
//		System.out.println(objectMapper.writeValueAsString(checkController.getAllUnchecked()));
//		System.out.println(objectMapper.writeValueAsString(
//				commentService.getComments(Long.parseLong("2"), PageRequest.of(0, 5))
//		));
		System.out.println(objectMapper.writeValueAsString(
				albumRepository.findAllWithExtraInfoAndCheckedIsTrue(PageRequest.of(0, 10))
		));
//		System.out.println(objectMapper.writeValueAsString(
//				artistRepository.findWithExtraInfoByNameLike("杰", PageRequest.of(0, 5))
//		));
//		System.out.println(objectMapper.writeValueAsString(
//				artistRepository.findAllWithExtraInfo(PageRequest.of(0, 5))
//		));
//		System.out.println(objectMapper.writeValueAsString(
//				trackRepository.findWithExtraInfoByNameLike("里", PageRequest.of(0, 20))
//		));
//		System.out.println(objectMapper.writeValueAsString(
//				trackRepository.findAllWithExtraInfo(PageRequest.of(0, 5))
//		));
//		System.out.println(objectMapper.writeValueAsString(
//				(albumRepository.findAlbumsByArtistId(1L, PageRequest.of(0, 1)))));
//		System.out.println(objectMapper.writeValueAsString(
//				trackRepository.findTracksByAlbumId(1L, PageRequest.of(0,1))));
//		System.out.println(objectMapper.writeValueAsString(
//				trackRepository.findTracksByArtistNameLike("%杰伦%", PageRequest.of(0,3))));
	}
}

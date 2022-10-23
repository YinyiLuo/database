package com.dbdev.music;

import com.dbdev.music.domain.Artist;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.repository.AlbumRepository;
import com.dbdev.music.repository.ArtistRepository;
import com.dbdev.music.repository.SysUserRepository;
import com.dbdev.music.repository.TrackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootTest
class MusicPlayerApplicationTests {

	@Autowired
	SysUserRepository sysUserRepository;

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	AlbumRepository albumRepository;

	@Autowired
	TrackRepository trackRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ObjectMapper objectMapper;

	@SuppressWarnings("all")
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	String from;

	@Value("2135246743@qq.com")
	String to;

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
	void contextLoads() {
		String password = passwordEncoder.encode("password1");
		sysUserRepository.save(
				SysUser.builder()
						.name("zhangsan")
						.password(password)
						.role("ROLE_STUDENT")
						.build()
		);
	}

	@Test
	void testSimpleSend() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("简单邮件");
		message.setText("您的验证码为123456");
		System.out.println(message);
		mailSender.send(message);
	}

	@Test
	void testNew() throws Exception {
//		System.out.println(objectMapper.writeValueAsString(
//				albumRepository.findWithExtraInfoByNameLike("你", PageRequest.of(0, 5))
//		));
//		System.out.println(objectMapper.writeValueAsString(
//				albumRepository.findAllWithExtraInfo(PageRequest.of(0, 5))
//		));
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

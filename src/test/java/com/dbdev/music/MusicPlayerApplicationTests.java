package com.dbdev.music;

import com.dbdev.music.domain.SysUser;
import com.dbdev.music.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class MusicPlayerApplicationTests {

	@Autowired
	SysUserRepository sysUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	String from;

	@Value("2135246743@qq.com")
	String to;

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
}

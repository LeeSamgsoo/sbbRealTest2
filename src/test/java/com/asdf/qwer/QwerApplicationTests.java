package com.asdf.qwer;

import com.asdf.qwer.article.ArticleService;
import com.asdf.qwer.user.SiteUser;
import com.asdf.qwer.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QwerApplicationTests {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		SiteUser user = this.userService.get("test");
		for (int i = 1; i <= 300; i++) {
			this.articleService.create(String.format("Title[%s]", i), "Dummy Article", user);
		}
	}

}

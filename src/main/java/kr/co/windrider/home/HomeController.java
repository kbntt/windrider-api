package kr.co.windrider.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.windrider.vo.BlogVo;
import kr.co.windrider.vo.MenuVo;

@RestController
public class HomeController {
	
	@Autowired
	public HomeService homeService;

	@GetMapping("/home/getTest")
	public List<BlogVo> getTest() {
		System.out.println("getTest");
		List<BlogVo> listBlogVo = homeService.getTest();
		return listBlogVo;
		
	}

	@GetMapping("/home/getTopMenu")
	public List<MenuVo> getTopMenu() {
		System.out.println("getTopMenu");
		List<MenuVo> list = homeService.getTopMenu();
		return list;
	}

	
}

package kr.co.windrider.myLife;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.windrider.vo.BlogVo;
import kr.co.windrider.vo.MenuVo;
import kr.co.windrider.vo.MyLifeVo;

@RestController
public class MyLifeController {
	
	@Autowired
	public MyLifeService myLifeService;
	
	@GetMapping("/myLife/getMyLife")
	public List<MyLifeVo> getMyLife() {
		System.out.println("getMyLife");
		List<MyLifeVo> list = myLifeService.getMyLife();
		return list;
	}
}

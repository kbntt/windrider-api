package kr.co.windrider.myLife;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

	@PostMapping("/myLife/saveMyLife")
	public HashMap<String, Object> saveMyLife(@RequestBody HashMap<String, Object> data) {
		System.out.println("saveMyLife");
		String title = (String) data.get("title");
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo.setTitle(title);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		
		int result = myLifeService.saveMyLife(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if( 0 < result  ) { resultMap.put("msg", "SUCCESS");}
		else { resultMap.put("msg", "FAIL");}
		
		return resultMap;
	}
}

package kr.co.windrider.myLife;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.windrider.comm.file.FilePath;
import kr.co.windrider.comm.file.FileStorageService;
import kr.co.windrider.vo.MyLifeVo;

//@CrossOrigin(origins = "http://domain1.com, http://domain2.com")
@RestController
public class MyLifeController {
	
	@Autowired
	public MyLifeService myLifeService;

	@Autowired
	public FileStorageService fileStorageService;
	
	
	@GetMapping("/myLife/getMyLife")
	public List<MyLifeVo> getMyLife() {
		
		System.out.println("getMyLife");
		MyLifeVo myLifeVo = new MyLifeVo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		
		List<MyLifeVo> list = myLifeService.getMyLife(map);
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

	@PostMapping("/myLife/fileUploadTest")
	public void fileUploadTest(MultipartFile file) throws Exception{
		String filePath = FilePath.MYLIFE_PATH;
		System.out.println(filePath);
		fileStorageService.upload(file, filePath);
		
	}
}

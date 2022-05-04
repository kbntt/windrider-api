package kr.co.windrider.myLife;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.windrider.comm.file.FilePath;
import kr.co.windrider.comm.file.FileStorageService;
import kr.co.windrider.vo.AttachFileVo;
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
		
		for(int i =0; i < list.size(); i++) {
			HashMap<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("boardUuid", list.get(i).getUuid());
			List<AttachFileVo> fileList = fileStorageService.getAttachFile(fileMap);
			list.get(i).setFilelist(fileList);
		}
		return list;
	}
	
	@GetMapping("/myLife/getMyLifeByUuid")
	public MyLifeVo getMyLifeByUuid(HttpServletRequest request) {
		
		System.out.println("getMyLife");
		String uuid = request.getParameter("uuid");
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo.setUuid(uuid);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		
		MyLifeVo vo = myLifeService.getMyLifeOne(map);

		if( vo != null) {
			HashMap<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("boardUuid", vo.getUuid());
			List<AttachFileVo> fileList = fileStorageService.getAttachFile(fileMap);
			vo.setFilelist(fileList);
		}
		
		return vo;
	}

	@PostMapping("/myLife/saveMyLife")
	public HashMap<String, Object> saveMyLife(HttpServletRequest request, MultipartFile file) {
		System.out.println("saveMyLife");
		String uuid = UUID.randomUUID().toString();
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		Date date = new Date();
		
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo.setUuid(uuid);
		myLifeVo.setTitle(title);
		myLifeVo.setContents(contents);
		myLifeVo.setDeleteYn("N");
		myLifeVo.setCreateUser("windRider");
		myLifeVo.setCreateDate(date);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		
		int result = myLifeService.saveMyLife(map,file);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if( 0 < result  ) { resultMap.put("msg", "SUCCESS");}
		else { resultMap.put("msg", "FAIL");}
		
		return resultMap;
	}
	 
	@DeleteMapping("/myLife/delMyLife/{uuid}")
	public HashMap<String, Object> delMyLife(@PathVariable String uuid) throws Exception{
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo.setUuid(uuid);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		int result = myLifeService.delMyLife(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(0<result) { resultMap.put("msg", "SUCCESS");}
		else { resultMap.put("msg", "FAIL");}
		return resultMap;
	}
	
	@PutMapping("/myLife/modifyMyLife")
	public HashMap<String, Object> modifyMyLife(HttpServletRequest request, @RequestPart(value="file", required=false)
	MultipartFile file
			){
		String uuid = (String) request.getParameter("uuid");
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		String imageModState = (String) request.getParameter("imageModState");
		String[] orgFileUuid = request.getParameterValues("orgFileUuid");
		
		Date date = new Date();
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo.setUuid(uuid);
		myLifeVo.setTitle(title);
		myLifeVo.setContents(contents);
		myLifeVo.setUpdateDate(date);
		myLifeVo.setImageModState(imageModState);
		List<AttachFileVo> attachFileVoList = new ArrayList<>();
		for(int i = 0; i<orgFileUuid.length; i++) {
			AttachFileVo attachFileVo = new AttachFileVo();
			attachFileVo.setOrgFileUuid(orgFileUuid[i]);
			attachFileVo.setUuid(orgFileUuid[i]);
			attachFileVoList.add(attachFileVo);
			myLifeVo.setFilelist(attachFileVoList);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myLifeVo", myLifeVo);
		
		int result = myLifeService.modifyMyLife(map,file);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(0<result) { resultMap.put("msg", "SUCCESS");}
		else { resultMap.put("msg", "FAIL");}
		return resultMap;
	}
	
}

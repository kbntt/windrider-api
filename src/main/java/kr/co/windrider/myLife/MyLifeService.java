package kr.co.windrider.myLife;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.windrider.vo.MyLifeVo;

public interface MyLifeService {
	public List<MyLifeVo> getMyLife(HashMap<String, Object> map); 
	public int saveMyLife(HashMap<String, Object> map);
	
}

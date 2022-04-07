package kr.co.windrider.myLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.windrider.comm.file.FileStorageService;
import kr.co.windrider.vo.MyLifeVo;

@Service
public class MyLifeServiceImple implements MyLifeService{

	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "kr.co.windrider.myLife";
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Override
	public List<MyLifeVo> getMyLife(HashMap<String, Object> map) {
		String sqlId = ".getMyLife";
		List<MyLifeVo> list = sqlSession.selectList(namespace+sqlId, map);
		return list;
	}
	@Override
	public int saveMyLife(HashMap<String, Object> map,MultipartFile file) {
		
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo = (MyLifeVo)map.get("myLifeVo");
		String boardUiid = myLifeVo.getUuid(); 
		int result = fileStorageService.saveAttachFile(file, boardUiid);
		
		if(0 < result) {
			String sqlId = ".saveMyLife";
			result = sqlSession.insert(namespace+sqlId, map);	
		}
		return result;
	}
	
}

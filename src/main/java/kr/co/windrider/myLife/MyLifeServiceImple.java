package kr.co.windrider.myLife;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kr.co.windrider.comm.file.FileStorageService;
import kr.co.windrider.vo.AttachFileVo;
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

	@Override
	public int modifyMyLife(HashMap<String, Object> map,MultipartFile file) {
		
		MyLifeVo myLifeVo = new MyLifeVo();
		
		myLifeVo = (MyLifeVo)map.get("myLifeVo");
		String boardUuid = myLifeVo.getUuid(); 
		List<AttachFileVo> fileList = myLifeVo.getFilelist(); 
		
		int result = 0;
		// 파일이 수정됨
		if( "true".equals(myLifeVo.getImageModState()) ) {
			for(int i =0; i < fileList.size(); i++) {
			    result = fileStorageService.modifyAttachFile(file, boardUuid, fileList.get(i).getOrgFileUuid());
				if(0 < result) {
					String sqlId = ".updateMyLife";
					result = sqlSession.update(namespace+sqlId, map);
				}
			}
		}else {
			// 파일이 수정안됨
			String sqlId = ".updateMyLife";
			result = sqlSession.update(namespace+sqlId, map);
		}
		
		return result;
	}
	@Override
	public int delMyLife(HashMap<String, Object> map) {
		String sqlId = ".delMyLife";
		int result = sqlSession.delete(namespace+sqlId, map);
		
		MyLifeVo myLifeVo = new MyLifeVo();
		myLifeVo = (MyLifeVo)map.get("myLifeVo");
		String boardUuid = myLifeVo.getUuid();
		fileStorageService.deleteAttachFile(boardUuid);
		return result;
	}
	
	@Override
	public MyLifeVo getMyLifeOne(HashMap<String, Object> map) {
		String sqlId = ".getMyLife";
		MyLifeVo vo = sqlSession.selectOne(namespace+sqlId, map);
		return vo;
	}

}

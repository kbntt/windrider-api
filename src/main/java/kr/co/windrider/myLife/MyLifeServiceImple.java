package kr.co.windrider.myLife;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.windrider.vo.MyLifeVo;

@Service
public class MyLifeServiceImple implements MyLifeService{

	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "kr.co.windrider.myLife";
	
	@Override
	public List<MyLifeVo> getMyLife() {
		String sqlId = ".getMyLife";
		List<MyLifeVo> list = sqlSession.selectList(namespace+sqlId, null);
		return list;
	}
	@Override
	public int saveMyLife(HashMap<String, Object> map) {
		String sqlId = ".saveMyLife";
		int result = sqlSession.insert(namespace+sqlId, map);
		return result;
	}
	
	
}

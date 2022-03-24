package kr.co.windrider.home;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.windrider.vo.BlogVo;
import kr.co.windrider.vo.MenuVo;

@Service
public class HomeServiceImple implements HomeService{

	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "kr.co.windrider.home";
	
	@Override
	public List<BlogVo> getTest() {
		String sqlId = ".getTest";
		List<BlogVo> listBlog = sqlSession.selectList(namespace+sqlId, null);
		return listBlog;
	}
	
	@Override
	public List<MenuVo> getTopMenu() {
		String sqlId = ".getTopMenu";
		List<MenuVo> listMenu = sqlSession.selectList(namespace+sqlId, null);
		return listMenu;
	}
	

}

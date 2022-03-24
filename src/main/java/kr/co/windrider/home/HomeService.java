package kr.co.windrider.home;

import java.util.List;

import kr.co.windrider.vo.BlogVo;
import kr.co.windrider.vo.MenuVo;

public interface HomeService {
	public List<BlogVo> getTest();
	public List<MenuVo> getTopMenu();
}

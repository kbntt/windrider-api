package kr.co.windrider.myLife;

import java.util.HashMap;
import java.util.List;

import kr.co.windrider.vo.MyLifeVo;

public interface MyLifeService {
	public List<MyLifeVo> getMyLife(); 
	public int saveMyLife(HashMap<String, Object> map);
}

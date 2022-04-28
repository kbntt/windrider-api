package kr.co.windrider.comm.file;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.windrider.vo.AttachFileVo;

public interface FileStorageService {
	public void init(String uploadPath);
	public void upload(MultipartFile file, String uploadPath,String fileName);
	public int  saveAttachFile(MultipartFile file, String boardUiid);
	public void delete(String filename);
	public int modifyAttachFile(MultipartFile file, String boardUiid, String uuid);
	public int deleteAttachFile(String boardUuid);
	public List<AttachFileVo> getAttachFile(HashMap<String, Object> map);
}

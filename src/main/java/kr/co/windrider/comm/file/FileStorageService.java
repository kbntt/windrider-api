package kr.co.windrider.comm.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	public void init(String uploadPath);
	public void upload(MultipartFile file, String uploadPath,String fileName);
	public int  saveAttachFile(MultipartFile file, String boardUiid);
}

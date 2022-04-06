package kr.co.windrider.comm.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	void init(String uploadPath);
	void upload(MultipartFile file, String uploadPath);
}

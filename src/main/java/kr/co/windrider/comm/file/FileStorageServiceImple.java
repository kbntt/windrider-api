package kr.co.windrider.comm.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImple implements FileStorageService {
	
	@Value("${spring.servlet.multipart.location}")
	private String springUploadPath;
	
	@Override
	public void init(String uploadPath) {
		try {
			Files.createDirectories(Paths.get(uploadPath));
		}catch(IOException e) {
			throw new RuntimeException("Could not create upload folder!");
		}
	}

	@Override
	public void upload(MultipartFile file, String uploadPath) {
		try {
			String path = springUploadPath+uploadPath;
			if(file.isEmpty()) {
				throw new Exception("ERROR : File is empty.");
			}
			Path root = Paths.get(path);
			if(!Files.exists(root)) {
				init(path);
			}
			try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, root.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			}
		}catch(Exception e) {
			throw new RuntimeException("Could not store the file. Error: "+e.getMessage());
		}
		
	}

}

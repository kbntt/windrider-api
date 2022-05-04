package kr.co.windrider.comm.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kr.co.windrider.vo.AttachFileVo;


@Service
public class FileStorageServiceImple implements FileStorageService {
	
	@Value("${spring.servlet.multipart.location}")
	private String springUploadPath;
	
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "kr.co.windrider.comm";
	
	@Override
	public void init(String uploadPath) {
		try {
			Files.createDirectories(Paths.get(uploadPath));
		}catch(IOException e) {
			throw new RuntimeException("Could not create upload folder!");
		}
	}

    @Override
    public void upload(MultipartFile file,String uploadPath,String fileName) {
        try {
        	String path = springUploadPath+uploadPath;
            Path root = Paths.get(path);
            if (!Files.exists(root)) {
                init(path);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, root.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

	@Override
	public int saveAttachFile(MultipartFile file, String boardUuid) {
		
		if( file.isEmpty()) {
			return 0;
		}
		String originalFilename = file.getOriginalFilename();
		long fileSize = file.getSize();
		String fileType = file.getContentType();
		String uuid = UUID.randomUUID().toString();
		Date date = new Date();
		String uploadPath = FilePath.MYLIFE_PATH;
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String storedFileName = uuid+extension;
		
		AttachFileVo attachFileVo = new AttachFileVo();
		attachFileVo.setUuid(uuid);
		attachFileVo.setBoardUuid(boardUuid);
		attachFileVo.setOrgFileName(originalFilename);
		attachFileVo.setFileDirectory(uploadPath);
		attachFileVo.setFileSize(fileSize);
		attachFileVo.setFileType(fileType);
		attachFileVo.setFileExtension(extension);
		attachFileVo.setStoredFileName(storedFileName);
		attachFileVo.setCreateDate(date);
		attachFileVo.setCreateUser("windRider");
		
		String sqlId = ".saveAttachFile";
		
		int result = sqlSession.insert(namespace+sqlId,attachFileVo);
		if(0<result) {
			upload(file,uploadPath,storedFileName);
		}
		return result;
	}
	
    @Override
	public void delete(String filename) {
		try {
			String uploadPath = springUploadPath + FilePath.MYLIFE_PATH;
			
			String delFile = uploadPath + filename;
			Path filePath = Paths.get(delFile);
			Files.deleteIfExists(filePath);
			
		}catch( Exception e) {
			throw new RuntimeException("Could not delete the file. Error: " + e.getMessage());
		}
	}


	@Override
	public int deleteAttachFile(String boardUuid) {
		HashMap<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("boardUuid", boardUuid);
		List<AttachFileVo> list = getAttachFile(fileMap);
		
		for(int i=0; i<list.size(); i++) {
			delete(list.get(i).getStoredFileName()); 
		}
		String sqlId = ".deleteAttachFile";
		AttachFileVo attachFileVo = new AttachFileVo();
		attachFileVo.setBoardUuid(boardUuid);
		
		int result = sqlSession.delete(namespace+sqlId, attachFileVo); 
		return result;
	}
	
	@Override
	public List<AttachFileVo> getAttachFile(HashMap<String, Object> map){
		String sqlId = ".getAttachFile";
		List<AttachFileVo> list = sqlSession.selectList(namespace+sqlId, map);
		return list;
	}
	
	@Override
	public int modifyAttachFile(MultipartFile file,String boardUuid, String uuid ) {

		if( file.isEmpty()) {
			return 0;
		}
		String originalFilename = file.getOriginalFilename();
		long fileSize = file.getSize();
		String fileType = file.getContentType();
		Date date = new Date();
		String uploadPath = FilePath.MYLIFE_PATH;
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String storedFileName = uuid+extension;
		
		AttachFileVo attachFileVo = new AttachFileVo();
		attachFileVo.setBoardUuid(boardUuid);
		attachFileVo.setUuid(uuid);
		attachFileVo.setOrgFileName(originalFilename);
		attachFileVo.setFileDirectory(uploadPath);
		attachFileVo.setFileSize(fileSize);
		attachFileVo.setFileType(fileType);
		attachFileVo.setFileExtension(extension);
		attachFileVo.setStoredFileName(storedFileName);
		attachFileVo.setUpdateDate(date);
		attachFileVo.setCreateUser("windRider");
		
		String sqlId = ".updateAttachFile";
		
		int result = sqlSession.update(namespace+sqlId,attachFileVo);
		delete(storedFileName);
		upload(file,uploadPath,storedFileName);
		return result;
	}
}








package kr.co.windrider.vo;


import java.util.Date;

public class AttachFileVo {

    private String uuid;
    private String boardUuid;
    private String orgFileName;
    private String fileDirectory;
    private long   fileSize;
    private String fileType;
    private String storedFileName;
    private String fileExtension;
    private String updateUser;
    private Date   updateDate;
    private String createUser;
    private Date   createDate;
    private String imgUrl;
    private String orgFileUuid;
    
    public String getOrgFileUuid() {
		return orgFileUuid;
	}

	public void setOrgFileUuid(String orgFileUuid) {
		this.orgFileUuid = orgFileUuid;
	}

	public String getImgUrl() {
		return imgUrl;
	}
    
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBoardUuid() {
        return boardUuid;
    }

    public void setBoardUuid(String boardUuid) {
        this.boardUuid = boardUuid;
    }

    public String getOrgFileName() {
        return orgFileName;
    }

    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}    
          
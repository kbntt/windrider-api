package kr.co.windrider.vo;

import java.util.Date;
import java.util.List;

public class MyLifeVo {
	
	private String  uuid        ;
	private String  title       ;
	private String  contents    ;
	private String  deleteYn    ;
	private String  updateUser  ;
	private Date    updateDate  ;
	private String  createUser  ;
	private Date    createDate  ;
	private List<AttachFileVo> filelist;
	private String  imageModState    ;
	
	public String getImageModState() {
		return imageModState;
	}
	public void setImageModState(String imageModState) {
		this.imageModState = imageModState;
	}
	public List<AttachFileVo> getFilelist() {
		return filelist;
	}
	public void setFilelist(List<AttachFileVo> filelist) {
		this.filelist = filelist;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
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

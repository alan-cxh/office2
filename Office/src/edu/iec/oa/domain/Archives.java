package edu.iec.oa.domain;

/**
 * @author TaoJG
 * 人事管理--档案管理
 * 档案
 */

public class Archives{
	private Long id;				//ID
	private String title;       	//标题
	private String description; 	//描述
	private String author;			//上传者，记录是谁上传的文件
	private String ipAddress;		//上传者的电脑IP：用于记录
	
	//附件信息：档案附件的一般属性信息
	private String archivesFileName;		//附件:上传文件名
	private String archivesContentType;		//文件类型
	private String archivesSavePath;		//文件保存的路径:包含了路径和文件名，文件名采用UUID
	
	private User user;						//谁的档案 档案与用户多对一关系
	
	//get set
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArchivesFileName() {
		return archivesFileName;
	}
	public void setArchivesFileName(String archivesFileName) {
		this.archivesFileName = archivesFileName;
	}
	public String getArchivesContentType() {
		return archivesContentType;
	}
	public void setArchivesContentType(String archivesContentType) {
		this.archivesContentType = archivesContentType;
	}
	public String getArchivesSavePath() {
		return archivesSavePath;
	}
	public void setArchivesSavePath(String archivesSavePath) {
		this.archivesSavePath = archivesSavePath;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}

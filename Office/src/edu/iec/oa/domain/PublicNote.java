package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 工作日记：公共日记
 */
public class PublicNote {
	
	private Long id;			//编号
	private String title;		//标题
	private String content;		//内容
	private Long authorId;		//作者的id：作者日志被转载的时候；是根据Id来获得，不要根据姓名
	private String author;		//作者：从session中获取，不用与User对象关联了
	
	private Long oldAuthorId;	//原来的作者的Id
	private String oldAuthor;	//原来的作者的姓名
	private Date time;			//创建时间
	
	private int praiseCount;	//点赞次数
	private int demoteCount;	//踩的次数
	private int reprintCount;	//转载次数
	
	private int type;			//类型：  原创 0：原创；1：转载
	
	public final static String TYPE_ORIGINAL = "原创";
	public final static String TYPE_REPRINT = "转载";
	
	//get and set
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public int getDemoteCount() {
		return demoteCount;
	}

	public void setDemoteCount(int demoteCount) {
		this.demoteCount = demoteCount;
	}

	public int getReprintCount() {
		return reprintCount;
	}

	public void setReprintCount(int reprintCount) {
		this.reprintCount = reprintCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getOldAuthorId() {
		return oldAuthorId;
	}

	public void setOldAuthorId(Long oldAuthorId) {
		this.oldAuthorId = oldAuthorId;
	}

	public String getOldAuthor() {
		return oldAuthor;
	}

	public void setOldAuthor(String oldAuthor) {
		this.oldAuthor = oldAuthor;
	}
}

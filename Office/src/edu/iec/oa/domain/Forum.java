package edu.iec.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author TaoJG
 * 论坛版块
 */
public class Forum implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;			//版块ID
	private String name;		//版块名称
	private String description;	//版块描述
	private int position;		//版块位置
	
	private int topicCount;		//主题（主贴）数量
	private int articleCount;	//回复数量
	
	private Set<Topic> topics = new HashSet<Topic>();		//每个模块里面对应的主题（主贴）：一对多
	//private Topic lastTopic;	//最新的发表主题:一对一
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

}

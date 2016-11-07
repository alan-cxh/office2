package edu.iec.oa.domain;

/**
 * @author TaoJG
 * 回复，每个主贴里面的回复
 */
public class Reply extends Article{
	
	private Topic topic;	//所属主题（主贴）：多对一

	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}

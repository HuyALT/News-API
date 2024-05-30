package com.ptithcm.commentsservice.dto.request;

public class CommentRequestUpdate {
	private Long commentsid;
	private String content;
	public CommentRequestUpdate() {
		super();
	}
	public CommentRequestUpdate(Long commentsid, String content) {
		super();
		this.commentsid = commentsid;
		this.content = content;
	}
	public Long getCommentsid() {
		return commentsid;
	}
	public void setCommentsid(Long commentsid) {
		this.commentsid = commentsid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

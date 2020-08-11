package com.reply;

public class ReplyDTO {
	private int rno;
	private int qno;
	private String userId;
	private String content;
	private String writeDate;

	public int getRno() {
		return this.rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getQno() {
		return this.qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
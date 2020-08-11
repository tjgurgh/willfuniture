package com.qna;


import java.util.List;

public class QNADTO {
	private int level;
	private int qno;
	private String qnaCategory;
	private String userId;
	private String subject;
	private String writeDate;
	private String qnaContent;
	private int hitCount;
	private int parentNo;
	private int groupNo;

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getQno() {
		return this.qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQnaCategory() {
		return this.qnaCategory;
	}

	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getQnaContent() {
		return this.qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public int getHitCount() {
		return this.hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
}
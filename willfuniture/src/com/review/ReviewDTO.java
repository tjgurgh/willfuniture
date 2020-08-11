package com.review;

import java.util.List;

import com.reply.ReplyDTO;

public class ReviewDTO {

	private int reviewNo;
	private String orderNo;
	private String userId;
	private String writeDate;
	private String subject;
	private String content;
	private int hitCount;
	private int status;
	private int recnt;


	public int getRecnt() {
		return recnt;
	}

	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}

	private List<ReplyDTO> replyList;
	
	
	
	public List<ReplyDTO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ReplyDTO> replyList) {
		this.replyList = replyList;
	}

	public int getReviewNo() {
		return this.reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHitCount() {
		return this.hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
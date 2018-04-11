package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyrepo;
	

	public List<Reply> replyBySender(int senderId,int role){
		List<Reply> replys=new ArrayList<>();
		replyrepo.findBySenderAndSenderRole(senderId,role)
		.forEach(replys::add);
		
		return replys;
		
	}
	
	public List<Reply> replyByReceiver(int receiverId){
		List<Reply> replys=new ArrayList<>();
		replyrepo.findByReceiver(receiverId)
		.forEach(replys::add);
		
		return replys;
		
	}
	
	public void addReply(Reply reply){
		replyrepo.save(reply);
		
	}
	
	public Reply getOneReply(int replyId){
		return replyrepo.findOne(replyId);
		
	}
	
	public void deleteOneReply(int replyid) {
		
		replyrepo.delete(replyid);
	}
}

package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface ReplyRepository extends CrudRepository<Reply,Integer> {

	public List<Reply> findBySenderAndSenderRole(int senderId,int role);
	
	public List<Reply> findByReceiver(int receiverId);
	
}

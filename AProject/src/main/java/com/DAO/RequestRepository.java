package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {

	public List<Request> findBySender(int senderId);
	
	public List<Request> findByReceiver(int receiverId);
	
	public List<Request> findByReceiverAndReceiverRole(int receiverId,int role);
	
}

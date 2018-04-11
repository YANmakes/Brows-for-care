package com.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{

	

}

package com.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.User;

public interface UserDao extends CrudRepository<User,Long>{

}

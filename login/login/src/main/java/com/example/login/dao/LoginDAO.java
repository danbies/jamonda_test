package com.example.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.login.dto.LoginDTO;
import com.example.member.dto.MemberDTO;

@Mapper
@Repository("loginDAO")
public interface LoginDAO {
	public MemberDTO loginCheck(MemberDTO member) throws DataAccessException;
}

package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.login.dao.LoginDAO;
import com.example.member.dto.MemberDTO;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDAO loginDAO;
	
	@Override
	public MemberDTO login(MemberDTO member) throws DataAccessException {
		return loginDAO.loginCheck(member);
	}
}

package com.example.login.service;

import org.springframework.dao.DataAccessException;

import com.example.member.dto.MemberDTO;

public interface LoginService {
	public MemberDTO login(MemberDTO member) throws DataAccessException;
}

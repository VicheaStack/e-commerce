package com.example.E_commerceApplication.service;

import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.Register;

@Service
public interface RegisterService {
	Register register(Register register);
}

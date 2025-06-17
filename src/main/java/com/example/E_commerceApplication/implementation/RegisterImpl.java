package com.example.E_commerceApplication.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.Register;
import com.example.E_commerceApplication.repository.RegisterRepository;
import com.example.E_commerceApplication.service.RegisterService;

@Service
public class RegisterImpl implements RegisterService {

	private RegisterRepository repository;

	@Autowired
	public RegisterImpl(RegisterRepository repository) {
		this.repository = repository;
	}

	@Override
	public Register register(Register register) {
		Optional<Register> existing = repository.findByEmail(register.getEmail());

		if (existing.isPresent()) {
			throw new RuntimeException("Email already registered");
		}

		Register data = new Register();
		data.setFullName(register.getFullName());
		data.setEmail(register.getEmail()); // 🔧 Missing line added
		data.setPassword(register.getPassword()); // 🔐 Encode password
		data.setRole("USER"); // or use register.getRole()

		return repository.save(data);
	}

}

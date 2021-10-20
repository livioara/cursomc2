package com.livio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livio.cursomc.domain.Cliente;
import com.livio.cursomc.repositories.ClienteRepository;
import com.livio.cursomc.services.exceptions.ObjectNotFoundException;

import net.bytebuddy.asm.Advice.Return;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
		
	}

	
}


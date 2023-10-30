package com.pe.HeoComisiones;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Sucursal;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HeoComisionesApplication implements CommandLineRunner {
	@Autowired
	PerfilesRepository perfilesRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(HeoComisionesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Sucursal sucursal = new Sucursal(0,"Sucursal 1","Av. Los Alamos","123456",true);
		Perfiles perfiles = new Perfiles(0,"Administrador",true);
		perfilesRepository.save(perfiles);
		Set<Perfiles>  perfiless = new HashSet<>();
		perfiless.add(perfiles);
		Usuarios usuario = new Usuarios(0,"juan","barre@","123",perfiless);
		usuarioRepository.save(usuario);
	}
}

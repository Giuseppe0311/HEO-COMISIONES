package com.pe.HeoComisiones;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HeoComisionesApplication implements CommandLineRunner {
	@Autowired
	PerfilesRepository perfilesRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	SucursalRepository sucursalRepository;

	public static void main(String[] args) {
		SpringApplication.run(HeoComisionesApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET","POST","DELETE","PUT","PATCH");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {
		Sucursales sucursales = new Sucursales(0,"Sucursal 1","Av. Los Alamos","123456",true);
		sucursalRepository.save(sucursales);
		Perfiles perfiles = new Perfiles(0,"Administrador",true);
		perfilesRepository.save(perfiles);
		Set<Perfiles>  perfiless = new HashSet<>();
		perfiless.add(perfiles);
		Usuarios usuario = new Usuarios(0,"juan","barre@","123",perfiless);
		usuarioRepository.save(usuario);
	}
}

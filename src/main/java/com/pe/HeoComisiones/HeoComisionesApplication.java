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
public class HeoComisionesApplication  {
	public static void main(String[] args) {
		SpringApplication.run(HeoComisionesApplication.class, args);
	}

}

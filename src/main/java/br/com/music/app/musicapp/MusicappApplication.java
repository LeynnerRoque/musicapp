package br.com.music.app.musicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MusicappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicappApplication.class, args);
	}

}

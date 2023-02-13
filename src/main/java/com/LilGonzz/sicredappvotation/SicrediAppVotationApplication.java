package com.LilGonzz.sicredappvotation;

import com.LilGonzz.sicredappvotation.repositories.PautaRepository;
import com.LilGonzz.sicredappvotation.repositories.SessionVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class SicrediAppVotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicrediAppVotationApplication.class, args);
	}
}

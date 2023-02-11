package com.LilGonzz.sicredappvotation;

import com.LilGonzz.sicredappvotation.model.Pauta;
import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.repositories.PautaRepository;
import com.LilGonzz.sicredappvotation.repositories.SessionVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SicrediAppVotationApplication implements CommandLineRunner {

	@Autowired
	PautaRepository pautaRepository;
	@Autowired
	SessionVoteRepository voteRepository;
	public static void main(String[] args) {
		SpringApplication.run(SicrediAppVotationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}

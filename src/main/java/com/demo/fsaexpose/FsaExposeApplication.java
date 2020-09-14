package com.demo.fsaexpose;

import com.demo.fsaexpose.mutator.Mutation;
import com.demo.fsaexpose.repositories.EstablishmentDetailRepository;
import com.demo.fsaexpose.repositories.LocalAuthorityRepository;
import com.demo.fsaexpose.resolver.EstablishmentDetailResolver;
import com.demo.fsaexpose.resolver.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class FsaExposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsaExposeApplication.class, args);
    }


    @Bean
    public EstablishmentDetailResolver localAuthorityResolver(LocalAuthorityRepository localAuthorityRepository) {
        return new EstablishmentDetailResolver(localAuthorityRepository);
    }

    @Bean
    public Query query(LocalAuthorityRepository localAuthorityRepository, EstablishmentDetailRepository establishmentDetailRepository) {
        return new Query(establishmentDetailRepository, localAuthorityRepository);
    }

    @Bean
    public Mutation mutation(LocalAuthorityRepository localAuthorityRepository, EstablishmentDetailRepository establishmentDetailRepository) {
        return new Mutation(establishmentDetailRepository, localAuthorityRepository);
    }

}

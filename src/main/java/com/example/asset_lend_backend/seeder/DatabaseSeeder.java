package com.example.asset_lend_backend.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserSeeder userSeeder;

    @Override
    public void run(String... args) throws Exception {
        userSeeder.seedUsers();
    }
}
package com.javaws.busroute.setup;

import com.javaws.busroute.model.Route;
import com.javaws.busroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "setupData".equals(args[0])) {
//          Insert some static route data into the database
            List<Route> routes = Arrays.asList(
                    new Route("A", "L"),
                    new Route("B", "L")
            );
            routeRepository.saveAll(routes);

            System.out.println("Data seeding completed successfully!");
        } else {
            System.out.println("No action specified. Use 'setupData' command to seed data.");
        }
    }
}

package com.dsl.simulator.config;

import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.net.URL;

@Configuration
public class OrekitConfig {

    @PostConstruct
    public void initOrekit() {
        try {
            URL resource = getClass().getClassLoader().getResource("orekit-data-main");
            if (resource == null) {
                throw new IllegalStateException("Orekit data directory not found in resources");
            }
            File orekitData = new File(resource.toURI());
            DataProvidersManager manager = DataContext.getDefault().getDataProvidersManager();
            manager.addProvider(new DirectoryCrawler(orekitData));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Orekit data", e);
        }
    }
}

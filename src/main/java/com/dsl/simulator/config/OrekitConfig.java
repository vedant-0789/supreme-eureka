package com.dsl.simulator.config;

import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.ZipJarCrawler;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.net.URL;

@Configuration
public class OrekitConfig {

    @PostConstruct
    public void initOrekit() {
        try {
            // This approach is robust for both file system and JAR execution
            final URL orekitDataUrl = getClass().getClassLoader().getResource("orekit-data-main.zip");
            if (orekitDataUrl == null) {
                 throw new IllegalStateException("Orekit data zip not found in resources. Please ensure 'orekit-data-main.zip' is present.");
            }
            
            DataProvidersManager manager = DataContext.getDefault().getDataProvidersManager();
            manager.addProvider(new ZipJarCrawler(orekitDataUrl));

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Orekit data from zip", e);
        }
    }
}

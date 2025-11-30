package com.dsl.simulator;

import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;

@SpringBootApplication
public class SatopsDslApplication {

	public static void main(String[] args) {
		System.out.println("Initializing Orekit data...");

		try {
			File orekitDir = extractResourceDirectory("orekit-data-main");

			DataProvidersManager manager = DataContext.getDefault().getDataProvidersManager();
			manager.addProvider(new DirectoryCrawler(orekitDir));

			System.out.println("Orekit data loaded successfully from: " + orekitDir.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize Orekit data", e);
		}

		SpringApplication.run(SatopsDslApplication.class, args);
	}

	/**
	 * Extracts a folder from resources. Works both:
	 *  - When running from IDE (folder exists on filesystem)
	 *  - When packaged in JAR (folder must be extracted)
	 */
	public static File extractResourceDirectory(String resourceDirName) throws IOException, URISyntaxException {

		ClassLoader cl = SatopsDslApplication.class.getClassLoader();
		URL resourceUrl = cl.getResource(resourceDirName);

		if (resourceUrl == null) {
			throw new FileNotFoundException("Resource folder not found: " + resourceDirName);
		}

		// CASE 1: Running from IDE => folder exists as normal directory
		if (resourceUrl.getProtocol().equals("file")) {
			System.out.println("Loading Orekit data from file system (IDE mode)");
			return new File(resourceUrl.toURI());
		}

		// CASE 2: Running from JAR => extract directory
		System.out.println("Extracting Orekit data from JAR...");

		File tempDir = Files.createTempDirectory("orekit-data").toFile();
		tempDir.deleteOnExit();

		try (FileSystem fs = FileSystems.newFileSystem(resourceUrl.toURI(), Collections.emptyMap())) {

			Path jarPath = fs.getPath("/" + resourceDirName);

			Files.walk(jarPath).forEach(source -> {
				try {
					Path dest = tempDir.toPath().resolve(jarPath.relativize(source).toString());

					if (Files.isDirectory(source)) {
						Files.createDirectories(dest);
					} else {
						Files.createDirectories(dest.getParent());
						Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
					}
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});

		} catch (Exception e) {
			throw new IOException("Failed to extract resource directory: " + resourceDirName, e);
		}

		return tempDir;
	}
}

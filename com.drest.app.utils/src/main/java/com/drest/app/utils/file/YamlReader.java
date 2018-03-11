package com.drest.app.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Yaml file parser
 * 
 * @author arvind
 *
 */
public class YamlReader {

	private static Yaml yaml = new Yaml();

	/**
	 * Read yaml file through specified full qualified path
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, Object> parseYamlFile(String filePath) throws Exception {

		File file = new File(filePath);

		if (!file.isFile()) {
			throw new Exception("No Configration file found for:" + filePath);
		}
		Map<String, Object> config = null;
		try (InputStream stream = new FileInputStream(file)) {
			config = yaml.load(stream);
		} catch (Exception ex) {
			throw new Exception("Configuration loading ERROR: " + filePath, ex);
		}
		return config;
	}

	/**
	 * Read yaml resource file through specified classloader resource.
	 * 
	 * @param clazz
	 * @param resourceName
	 * @return
	 */
	public static <T> T parseYamlResourceAsClass(Class<T> clazz, String resourceName) {
		T yamlConfig = null;
		try (InputStream in = clazz.getClassLoader().getResourceAsStream(resourceName)) {
			yamlConfig = yaml.loadAs(in, clazz);
		} catch (Exception ex) {
			throw new RuntimeException("Couldnot find yaml configuration: " + resourceName);
		}
		return yamlConfig;
	}

	/**
	 * Read yaml resource file through specified classloader resource.
	 * 
	 * @param clazz
	 * @param resourceName
	 * @return
	 */
	public static Map<String, Object> parseYamlResource(Class<?> clazz, String resourceName) {
		Map<String, Object> yamlConfig = null;
		try (InputStream in = clazz.getClassLoader().getResourceAsStream(resourceName)) {
			yamlConfig = yaml.load(in);
		} catch (Exception ex) {
			throw new RuntimeException("Couldnot find application yaml configuration: " + resourceName);
		}
		return yamlConfig;
	}
}

package com.example.app.repositories;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;

import com.example.app.util.exceptions.FileNotSavedException;

/**
 * @author Vitaly Chekushkin
 */
public class FileSaverImpl implements FileSaver {
	private final String path;

	public FileSaverImpl(String path) {
		this.path = path;
	}

	@Override
	public void save(Part part, String imageName) {
		try {
			part.write(path + File.pathSeparator + imageName);
		}
		catch (IOException e) {
			throw new FileNotSavedException(e);
		}
	}
}

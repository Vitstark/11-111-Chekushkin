package com.example.app.repositories;

import javax.servlet.http.Part;

/**
 * @author Vitaly Chekushkin
 */
public interface FileSaver {
	void save(Part part, String imageName);
}

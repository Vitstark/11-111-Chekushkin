package com.example.app.models;

import java.nio.file.Path;
import java.util.List;
import javax.persistence.Transient;
import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaly Chekushkin
 */

@Builder
@AllArgsConstructor
@Data
public class Concert {
	private Long id;
	private String title;
	private String author;
	private String description;
	private Path imagePath;

	@Transient
	private List<Presentation> presentations;
	@Transient
	private Part part;
}

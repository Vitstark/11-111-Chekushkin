package com.example.app.models;

import java.util.List;
import javax.persistence.Transient;

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
	private String description;

	@Transient
	private List<Presentation> presentations;
}

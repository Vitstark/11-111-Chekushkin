package com.example.app.repositories;

import java.util.List;

import com.example.app.models.Concert;
import com.example.app.models.Presentation;

/**
 * @author Vitaly Chekushkin
 */
public interface PresentationRepository extends CRUD<Presentation, Long> {
	List<Presentation> findByConcert(Long id);
}

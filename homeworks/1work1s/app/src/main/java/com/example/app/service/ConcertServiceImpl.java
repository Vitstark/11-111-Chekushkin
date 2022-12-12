package com.example.app.service;

import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.Part;

import com.example.app.models.Concert;
import com.example.app.repositories.ConcertRepository;
import com.example.app.repositories.FileSaver;
import com.example.app.repositories.FileSaverImpl;
import com.example.app.repositories.PresentationRepository;
import com.example.app.util.NameGeneratorFromImageName;
import com.example.app.util.exceptions.NotFoundException;

/**
 * @author Vitaly Chekushkin
 */
public class ConcertServiceImpl implements ConcertService {
	private final ConcertRepository concertRepository;
	private final PresentationRepository presentationRepository;
	private final FileSaver fileSaver;
	private final NameGeneratorFromImageName imageNameGenerator;

	public ConcertServiceImpl(ConcertRepository concertRepository,
		PresentationRepository presentationRepository,
		String path) {
		this.concertRepository = concertRepository;
		this.presentationRepository = presentationRepository;
		this.fileSaver = new FileSaverImpl(path);
		this.imageNameGenerator = new NameGeneratorFromImageName();
	}

	@Override
	public void save(Concert concert) {
		String imageName = imageNameGenerator.generateImageName(concert.getPart().getName());
		fileSaver.save(concert.getPart(), imageName);
		concert.setImagePath(Path.of("image", imageName));

		concertRepository.save(concert);
	}

	@Override
	public List<Concert> findAll() {
		List<Concert> concerts = concertRepository.findAll();

		concerts.forEach(concert -> concert.setPresentations(
			presentationRepository.findByConcert(concert.getId())));

		return concerts;
	}

	@Override
	public Concert findById(Long id) {
		Concert concert = concertRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Concert not found!"));

		concert.setPresentations(presentationRepository.findByConcert(concert.getId()));

		return concert;
	}

	@Override
	public void update(Concert concert) {
		concertRepository.update(concert);
		concert.setPresentations(presentationRepository.findByConcert(concert.getId()));
	}
}

package com.example.app.models;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaly Chekushkin
 */

@Builder
@Data
@AllArgsConstructor
public class Ticket {
	private Integer row;
	private Integer place;

	private Long presentationId;
	@Transient
	private Presentation presentation;

	private Long ownerId;
	@Transient
	private Person owner;

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
		this.presentationId = presentation.getId();
	}

	public void setOwner(Person owner) {
		this.owner = owner;
		this.ownerId = owner.getId();
	}
}

package fr.unantes.repositories;

import java.util.Collection;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;


import fr.unantes.beans.Amenagement;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class AmenagementRepository {/*
	private static AmenagementRepository amenagementRepository = null;
	 
	 static {
	  ObjectifyService.register(Amenagement.class);
	 }
	 
	 private AmenagementRepository() {
	 }
	 
	 public static synchronized AmenagementRepository getInstance() {
	  if (null == amenagementRepository) {
		  amenagementRepository = new AmenagementRepository();
	  }
	  return amenagementRepository;
	 }
	 
	 public Collection<Amenagement> findAmenagements() {
	  List<Amenagement> amenagements = ofy().load().type(Amenagement.class).list();
	  return amenagements;
	 }
	 
	 public Amenagement create(Amenagement amenagement) {
	  ofy().save().entity(amenagement).now();
	  return amenagement;
	 }
	 
	 public Amenagement update(Amenagement editedAmenagement) {
	  if (editedAmenagement.getId() == null) {
	   return null;
	  }
	 
	  Amenagement amenagement = ofy().load().key(Key.create(Amenagement.class, editedAmenagement.getId())).now();
	  amenagement.setNom(editedAmenagement.getNom());
	  amenagement.setDescription(editedAmenagement.getDescription());
	  ofy().save().entity(amenagement).now();
	 
	  return amenagement;
	 }
	 
	 public void remove(Long id) {
	  if (id == null) {
	   return;
	  }
	  ofy().delete().type(Amenagement.class).id(id).now();
	 }*/
}

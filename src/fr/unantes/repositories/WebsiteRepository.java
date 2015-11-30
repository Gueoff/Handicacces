package fr.unantes.repositories;
import com.google.appengine.api.datastore.ReadPolicy;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import fr.unantes.beans.Amenagement;
import fr.unantes.beans.Website;

import java.util.Collection;
 
import static com.googlecode.objectify.ObjectifyService.ofy;

public class WebsiteRepository {

	private static WebsiteRepository websiteRepository = null;
	 
	 static {
	  ObjectifyService.register(Website.class);
	  ObjectifyService.register(Amenagement.class);
	 }
	 
	 private WebsiteRepository() {
	 }
	 
	 public static synchronized WebsiteRepository getInstance() {
	  if (null == websiteRepository) {
		  websiteRepository = new WebsiteRepository();
	  }
	  return websiteRepository;
	 }
	 
	 //Retourne tous les sites web
	 public Collection<Website> findWebsites() {
		 Collection<Website> websites = ofy().load().type(Website.class).list();
		 return websites;
	 }
	 
	 //Retourne tous les sites web qui possède un aménagement particulier
	 public Collection<Website> findWebsitesByAmenagements(String nomAmenagement) {
		 Collection<Website> websites = ofy().load().type(Website.class).filter("amenagement", nomAmenagement).list();
	     return websites;
	    }
	 
	 //Retourne le site selon son URL
	 public Website findWebsiteByURL(String url){
		 Website website = ofy().load().type(Website.class).id(url).now();
		 return website;
	 }

	 //Crée un site web
	 public Website create(Website website) {
		 ofy().save().entity(website).now();
		 return website;
	 }
	 
	 
	 //Update le site web et lui ajoute un amenagement
	 public Website renseigner(Website editedWebsite, String nom_amenagement, String description_amenagement){
		 if (editedWebsite.getUrl() == null) {
			   return null;
		}
		 Amenagement amenagement = new Amenagement(nom_amenagement, description_amenagement);
		 editedWebsite.renseigner(amenagement);
			 
		Website website = ofy().load().key(Key.create(Website.class, editedWebsite.getUrl())).now();
		ofy().save().entity(website).now();
			 
		 return website;
	 }
	 
	 public void remove(String url) {
	  if (url == null) {
	   return;
	  }
	  ofy().delete().type(Website.class).id(url).now();
	 }
	 
}

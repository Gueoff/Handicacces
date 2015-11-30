package fr.unantes.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;
import com.googlecode.objectify.NotFoundException;

import fr.unantes.beans.Amenagement;
import fr.unantes.beans.User;
import fr.unantes.beans.Website;
import fr.unantes.repositories.AmenagementRepository;
import fr.unantes.repositories.WebsiteRepository;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(
	name = "handicacces", 
	version = "v1",
	description = "Little API which list some websites with layouts for disabled people"
	)
public class WebsiteEndpoint {
	
	//Tout e qui concerne les Aménagements
	@ApiMethod(name = "ListAllLayouts", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Amenagement> listAmenagements() {
	  return AmenagementRepository.getInstance().findAmenagements();
	 }
	
	 @ApiMethod(name = "CreateLayout", httpMethod = ApiMethod.HttpMethod.POST)
	 public Amenagement createAmenagement(Amenagement amenagement) {
	  return AmenagementRepository.getInstance().create(amenagement);
	 }
	 
	 @ApiMethod(name = "UpdateLayout", httpMethod = ApiMethod.HttpMethod.PUT)
	 public Amenagement updateAmenagement(Amenagement editedAmenagement) {
	  return AmenagementRepository.getInstance().update(editedAmenagement);
	 }
	 
	 @ApiMethod(name = "RemoveLayout", httpMethod = ApiMethod.HttpMethod.DELETE)
	 public void removeAmenagement(@Named("id") Long id) {
		 AmenagementRepository.getInstance().remove(id);
	 }
	
	
	 //Tout ce qui concerne les Websites
	 @ApiMethod(name = "ListAllWebsites", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Website> listWebsites() {
	  return WebsiteRepository.getInstance().findWebsites();
	 }
	 
	 @ApiMethod(name = "ListWebsitesByLayout", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Website> getWebsites(@Named("nom_amenagement") String nom_amenagement)  throws UnauthorizedException{
		 if(nom_amenagement.equals(null)){
			 throw new UnauthorizedException("Layout not found");
		 }
	 return WebsiteRepository.getInstance().findWebsitesByAmenagements(nom_amenagement);
	 }
	 
	 @ApiMethod(name = "GetWebsite", httpMethod = ApiMethod.HttpMethod.GET)
	 public Website getWebsite(@Named("url")String url) {
	  return WebsiteRepository.getInstance().findWebsiteByURL(url);	 
	 }
	 
	 
	 @ApiMethod(name = "CreateWebsite", httpMethod = ApiMethod.HttpMethod.POST)
	 public Website create(@Named("url")String url, Amenagement amenagement) {
		 Website website = new Website();
		 website.setUrl(url);
		 website.renseigner(amenagement);
		 return WebsiteRepository.getInstance().create(website);
	 }
	 
	 
	 @ApiMethod(name = "UpdateWebsite", httpMethod = ApiMethod.HttpMethod.PUT)
	 public Website update(Website website, @Named("nom")String nom, @Named("description")String description){
		 return WebsiteRepository.getInstance().renseigner(website, nom, description);
	 }
	 
	 @ApiMethod(name = "RemoveWebsite", httpMethod = ApiMethod.HttpMethod.DELETE)
	 public void remove(@Named("url") String url) {
		 WebsiteRepository.getInstance().remove(url);
	 }

}

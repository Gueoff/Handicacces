package fr.unantes.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Cursor;
import com.googlecode.objectify.NotFoundException;

import fr.unantes.beans.Amenagement;
import fr.unantes.beans.Layout;
import fr.unantes.beans.User;
import fr.unantes.beans.Website;
import fr.unantes.repositories.AmenagementRepository;
import fr.unantes.repositories.LayoutRepository;
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
	version = "v3",
	description = "Little API which list some websites with layouts for disabled people"
	)
public class WebsiteEndpoint {
	
	//Tout ce qui concerne les Aménagements
	@ApiMethod(name = "ListAllLayouts", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Layout> listLayouts() {
	  return LayoutRepository.getInstance().findLayouts();
	 }
	
	 @ApiMethod(name = "CreateLayout", httpMethod = ApiMethod.HttpMethod.POST)
	 public Layout createLayout(Layout layout) {
	  return LayoutRepository.getInstance().create(layout);
	 }
	 
	 @ApiMethod(name = "UpdateLayout", httpMethod = ApiMethod.HttpMethod.PUT)
	 public Layout updateLayout(Layout editedLayout) {
	  return LayoutRepository.getInstance().update(editedLayout);
	 }
	 
	 @ApiMethod(name = "RemoveLayout", httpMethod = ApiMethod.HttpMethod.DELETE)
	 public void removeLayout(@Named("id") Long id) {
		 LayoutRepository.getInstance().remove(id);
	 }
	
	
	 //Tout ce qui concerne les Websites
	 @ApiMethod(name = "ListAllWebsites", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Website> listWebsites() {
	  return WebsiteRepository.getInstance().findWebsites();
	 }
	 
	 @ApiMethod(name = "ListWebsitesByLayout", httpMethod = ApiMethod.HttpMethod.GET)
	 public Collection<Website> getWebsites(@Named("id_layout") int id_layout)  throws UnauthorizedException{
		/* if(id_layout == null){
			 throw new UnauthorizedException("Layout not found");
		 }*/
	 return WebsiteRepository.getInstance().findWebsitesByAmenagements(id_layout);
	 }
	 
	 @ApiMethod(name = "GetWebsite", httpMethod = ApiMethod.HttpMethod.GET)
	 public Website getWebsite(@Named("url")String url) {
	  return WebsiteRepository.getInstance().findWebsiteByURL(url);	 
	 }
	 
	 
	 @ApiMethod(name = "CreateWebsite", httpMethod = ApiMethod.HttpMethod.POST)
	 public Website create(Website website) {
		 return WebsiteRepository.getInstance().create(website);
	 }
	 
	 
	 @ApiMethod(name = "UpdateWebsite", httpMethod = ApiMethod.HttpMethod.PUT)
	 public Website update(@Named("url") String url, @Named("id_layout") int id_layout){ 
		 return WebsiteRepository.getInstance().update(url, id_layout);
	 }
	 
	 @ApiMethod(name = "RemoveWebsite", httpMethod = ApiMethod.HttpMethod.DELETE)
	 public void remove(@Named("url") String url) {
		 WebsiteRepository.getInstance().remove(url);
	 }

}

package fr.unantes.repositories;
import com.google.appengine.api.datastore.ReadPolicy;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import fr.unantes.beans.Layout;
import fr.unantes.beans.Website;
 
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

public class WebsiteRepository {

	private static WebsiteRepository websiteRepository = null;
	 
	 static {
	  ObjectifyService.register(Website.class);
	  ObjectifyService.register(Layout.class);
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
	 public List<Website> findWebsites() {
		 List<Website> websites = ofy().load().type(Website.class).list();
		 return websites;
	 }
	 
	 //Retourne tous les sites web qui possède un aménagement particulier
	 public List<Website> findWebsitesByAmenagements(int id_layout) {
		 List<Website> websites = ofy().load().type(Website.class).filter("layout", id_layout).list();
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
	 public Website update(String url, int id_layout){
		 Website editedWebsite = new Website();

		 if (editedWebsite.getUrl() == null) {
			   return null;
		}
		 /*
		 Layout layout = new Layout();
		 layout.setDescription(layoutDescription);
		 layout.setName(layoutName);
		*/	 
		Website website = ofy().load().key(Key.create(Website.class, editedWebsite.getUrl())).now();
		website.getLayouts().add(id_layout);
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

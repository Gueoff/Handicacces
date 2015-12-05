package fr.unantes.repositories;
import com.google.appengine.api.datastore.ReadPolicy;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import fr.unantes.beans.Amenagement;
import fr.unantes.beans.Website;
 
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

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
	 public List<Website> findWebsites() {
		 List<Website> websites = ofy().load().type(Website.class).list();
		 return websites;
	 }
	 
	 //Retourne tous les sites web qui possède un aménagement particulier
	 public List<Website> findWebsitesByAmenagements(String nomAmenagement) {
		 List<Website> websites = ofy().load().type(Website.class).filter("amenagement", nomAmenagement).list();
	     return websites;
	    }
	 
	 //Retourne le site selon son URL
	 public Website findWebsiteByURL(String url){
		 Website website = ofy().load().type(Website.class).id(url).now();
		 return website;
	 }

	 //Crée un site web
	 public Website create(Website website) {
		/*	JSONObject json = null;
			StringBuilder builder = new StringBuilder();
			try {
				json = new JSONObject(builder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 
		 JSONArray amenagements = new JSONArray();
		 try{
			 JSONObject val1 = new JSONObject();
				val1.put("nom", "assensseur3");
				val1.put("descr", "ce batiment est equip� d'un assensseur3");
				amenagements.put(val1);
				
				
			 JSONObject amenagement = (JSONObject) json.optJSONArray("items").get(3);
				o44.put("equipement", o4);
				four.put("resp", o44);
		 }
		 catch(JSONException e){
			 e.getMessage();
		 }
		 
		 */
		// ofy().save().entity(website).now();
		 return website;
	 }
	 
	 
	 //Update le site web et lui ajoute un amenagement
	 public Website renseigner(String url, String nom_amenagement, String description_amenagement){
		 Website editedWebsite = new Website(url);
		 if (editedWebsite.getUrl() == null) {
			   return null;
		}
		 Amenagement amenagement = new Amenagement(nom_amenagement, description_amenagement);
		// editedWebsite.renseigner(amenagement);
			 
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

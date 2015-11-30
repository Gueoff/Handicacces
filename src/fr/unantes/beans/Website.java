package fr.unantes.beans;


import java.util.Collection;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.ElementCollection;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Website {
	@Id
	private String url;
	@ElementCollection
	private Collection<Amenagement> amenagements;



	public Website() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Website(String url, Collection amenagements) {
		super();
		this.url = url;
		this.amenagements = amenagements;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Collection getAmenagements() {
		return amenagements;
	}
	public void setAmenagements(Collection amenagements) {
		this.amenagements = amenagements;
	}
	
	//Renseigne quels sont les amenagements pour le site
	public void renseigner(Amenagement amenagement){
		this.amenagements.add(amenagement);
	}
	
	


}

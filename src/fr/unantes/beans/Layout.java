package fr.unantes.beans;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.jdo.annotations.*;

/**
 * 
 * @author Geof
 *
 */
@Entity
@Cache
public class Layout {
	
	@Id
    private Long id;
    private String name;
    private String description;
    private boolean checked;

    public Layout() {
    }
    
    public Layout(String name, String description) {
    	this.name = name;
    	this.description = description;
    	this.checked = false;
    }
    
    public Layout(Long id, String name, String description) {
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.checked = false;
    }


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}

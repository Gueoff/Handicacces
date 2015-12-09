package fr.unantes.beans;


import javax.jdo.annotations.*;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Geof
 *
 */
@Entity
public class Website {

	@Id
    String url;
    //private List<Layout> layouts;
	List layouts = new ArrayList();
	

    public Website() {}
    
    public Website(String url){
    	this.url = url;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public List getLayouts() {
		return layouts;
	}

	public void setLayouts(List layouts) {
		this.layouts = layouts;
	}

    
    /*
    public List<Layout> getLayouts () {
        if (layouts == null) {
            return new ArrayList<Layout>();
        }
        return layouts;
    }

    public void setLayouts (List<Layout> layouts) {
        this.layouts = layouts;
    }
*/
}

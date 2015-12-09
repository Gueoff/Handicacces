package fr.unantes.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Collection;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import fr.unantes.beans.Layout;

public class LayoutRepository {

	private static LayoutRepository layoutRepository = null;
	 
	 static {
	  ObjectifyService.register(Layout.class);
	 }
	 
	 private LayoutRepository() {
	 }
	 
	 public static synchronized LayoutRepository getInstance() {
	  if (null == layoutRepository) {
		  layoutRepository = new LayoutRepository();
	  }
	  return layoutRepository;
	 }
	 
	 public Collection<Layout> findLayouts() {
	  List<Layout> layouts = ofy().load().type(Layout.class).list();
	  return layouts;
	 }
	 
	 public Layout create(Layout layout) {
	  ofy().save().entity(layout).now();
	  return layout;
	 }
	 
	 public Layout update(Layout editedLayout) {
	  if (editedLayout.getId() == null) {
	   return null;
	  }
	 
	  Layout layout = ofy().load().key(Key.create(Layout.class, editedLayout.getId())).now();
	  layout.setName(editedLayout.getName());
	  layout.setDescription(editedLayout.getDescription());
	  ofy().save().entity(layout).now();
	 
	  return layout;
	 }
	 
	 public void remove(Long id) {
	  if (id == null) {
	   return;
	  }
	  ofy().delete().type(Layout.class).id(id).now();
	 }
}

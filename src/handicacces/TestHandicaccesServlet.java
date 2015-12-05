package handicacces;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;



@SuppressWarnings("serial")
public class TestHandicaccesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		//resp.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = resp.getWriter();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("Url").setAncestor(KeyFactory.createKey("Browser", "moteur")).addSort("date", SortDirection.DESCENDING);
        List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));
	
        req.setAttribute("urls", results);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// Stocke url posté
        Entity url = new Entity("Url", KeyFactory.createKey("Browser", "moteur"));
        
        url.setProperty("name", req.getParameter("name"));
        url.setProperty("url", req.getParameter("url"));
        url.setProperty("date", new Date());

        datastore.put(url);

        resp.sendRedirect("/");
	}
}

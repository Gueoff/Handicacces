package handicacces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class requestServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		URL url;
//		JSONObject json2 = new JSONObject();
//		JSONArray arr = new JSONArray();
		try {
			
			String p = req.getParameter("p1");
			int nbtonextpage = Integer.parseInt(req.getParameter("start"));
			
//			url = new URL(
//				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
			
			url = new URL("https://www.googleapis.com/customsearch/v1?key=AIzaSyBU2lW6SS9BAIu_1rqLSmkXztNfcG-7v9Q&cx=007840350058366591309:jlizzfc_tam&q="+ p + "&start=" + nbtonextpage);
            
			URLConnection connection = url.openConnection();
			//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}

			JSONObject json = null;
			try {
				json = new JSONObject(builder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//out.print(json);
			
//			JSONObject jsonresp = null;
//			JSONArray arr = null;
//			
//		    for (int i = 0; i < json.optJSONObject("responseData").optJSONArray("results").length(); i++) {
//		        try {
//		        	
//					arr.put(json.optJSONObject("responseData").optJSONArray("results").get(i));
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//		    
//		    try {
//				jsonresp.accumulate("results", arr);
//			} catch (JSONException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		    
			//--------------------------------------------------------------
		    //on envoie les liens vers le servlet enrichisseur
		    //retour d'un jsonObject "retour" contenant un jsonarray "enrichie" ac les 4 elements (0-3), ac chaque element etant un jsonObject "URLenv" et un JSONArray "amenagement" contenant la liste des ammenagements
		    
		    //------------
		    //pour tester, on suppose que l'enrichissement a été effectué, on a donc ce json en retour:
		    JSONArray retour = null;
		    JSONObject o11 = new JSONObject();
		    JSONObject o22 = new JSONObject();
		    JSONObject o33 = new JSONObject();
		    JSONObject o44 = new JSONObject();
		    JSONArray o1 = new JSONArray();
		    JSONArray o2 = new JSONArray();
		    JSONArray o3 = new JSONArray();
		    JSONArray o4 = new JSONArray();
		    try {
		    	//1er elem

			    JSONObject val1 = new JSONObject();
				val1.put("nom", "assensseur3");
				val1.put("descr", "ce batiment est equipé d'un assensseur3");
			    JSONObject val2 = new JSONObject();
				val2.put("nom", "porte");
				val2.put("descr", "ce batiment a des portes larges3");
				o3.put(0, val1);
				o3.put(1, val2);

				//voila la forme du json recu pour le 1er lien, il faut donc maintenant inserer l'info:
//				JSONObject three = (JSONObject) json.optJSONObject("responseData").optJSONArray("results").get(2);
//				o33.put("equipement", o3);
//				
//				three.put("resp", o33);
				//-----retour.put(2, three);
				//2eme elem
				

			    val1 = new JSONObject();
				val1.put("nom", "assensseur2");
				val1.put("descr", "ce batiment est equipé d'un assensseur1");
			    val2 = new JSONObject();
				val2.put("nom", "porte");
				val2.put("descr", "ce batiment a des portes larges1");
				o1.put(0, val1);
				o1.put(1, val2);
				//voila la forme du json recu pour le 1er lien, il faut donc maintenant inserer l'info:
//				JSONObject one = (JSONObject) json.optJSONObject("responseData").optJSONArray("results").get(0);
//				o11.put("equipement", o2);
//				
//				one.put("resp", o22);
//				retour.put(0, one);
				//3eme elem
				

			    val1 = new JSONObject();
				val1.put("nom", "assensseur2");
				val1.put("descr", "ce batiment est equipé d'un assensseur2");
			    val2 = new JSONObject();
				val2.put("nom", "porte");
				val2.put("descr", "ce batiment a des portes larges2");
				o2.put(0, val1);
				o2.put(1, val2);
				//voila la forme du json recu pour le 1er lien, il faut donc maintenant inserer l'info:
//				JSONObject two = (JSONObject) json.optJSONObject("responseData").optJSONArray("results").get(1);
//				o22.put("equipement", o2);
//				
//				two.put("resp", o22);
//				retour.put(1, two);
				//retour.put(2, three);
				//4eme elem
				

			    val1 = new JSONObject();
				val1.put("nom", "assensseur4");
				val1.put("descr", "ce batiment est equipé d'un assensseur4");
			    val2 = new JSONObject();
				val2.put("nom", "porte");
				val2.put("descr", "ce batiment a des portes larges4");
				o4.put(0, val1);
				o4.put(1, val2);
				//voila la forme du json recu pour le 1er lien, il faut donc maintenant inserer l'info:
//				JSONObject four = (JSONObject) json.optJSONObject("responseData").optJSONArray("results").get(3);
//				o44.put("equipement", o4);
//				
//				four.put("resp", o44);
//				retour.put(3, four);
				
				
				
				JSONObject one = (JSONObject) json.optJSONArray("items").get(0);
				o11.put("equipement", o2);
				one.put("resp", o22);
				//retour.put(0, one);
				
				JSONObject two = (JSONObject) json.optJSONArray("items").get(1);
				o22.put("equipement", o2);
				two.put("resp", o22);
				//retour.put(1, two);
				
				JSONObject three = (JSONObject) json.optJSONArray("items").get(2);
				o33.put("equipement", o3);
				three.put("resp", o33);
				//retour.put(2, three);
				
				JSONObject four = (JSONObject) json.optJSONArray("items").get(3);
				o44.put("equipement", o4);
				four.put("resp", o44);
				//retour.put(3, four);
				
				JSONArray newitem = new JSONArray();
				newitem.put(one);
				newitem.put(two);
				newitem.put(three);
				newitem.put(four);
				newitem.put(json.optJSONArray("items").get(4));
				newitem.put(json.optJSONArray("items").get(5));
				newitem.put(json.optJSONArray("items").get(6));
				newitem.put(json.optJSONArray("items").get(7));
				newitem.put(json.optJSONArray("items").get(8));
				newitem.put(json.optJSONArray("items").get(9));
				
				
				json.getJSONObject("responseData").remove("items");
				json.getJSONObject("responseData").append("items",newitem);
				
				//out.print(json);
				
				
				
				//----------------------------------------------------------------------------
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		    
		    
		    
		    
		    
//		    
//		    url = new URL(
//				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//				    + "start="+ (nbtonextpage+4) + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			connection = url.openConnection();
//			//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
//
//			//line;
//			builder = new StringBuilder();
//			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			while((line = reader.readLine()) != null) {
//			 builder.append(line);
//			}
//
//			json = null;
//			try {
//				json = new JSONObject(builder.toString());
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			}
//			
//			for (int i = 0; i < json.optJSONObject("responseData").optJSONArray("results").length(); i++) {
//		        try {
//		        	arr.put(json.optJSONObject("responseData").optJSONArray("results").get(i));
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
			
			out.print(json);
			//out.flush();
				
			
		} finally {
			out.close();
		}
}

	
	
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		URL url;
//		int nbtonextpage = 0;
//		try {
//			
//			String p = req.getParameter("p1");
//			out.println("<html> <head> <title>Result</title> </head> <body> <h1>Result!</h1>");
//			out.println("<table>");
//			
//			url = new URL(
//				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			for(int j = 0; j<2; j++ )
//			{
//				
//				URLConnection connection = url.openConnection();
//				//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
//	
//				String line;
//				StringBuilder builder = new StringBuilder();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				while((line = reader.readLine()) != null) {
//				 builder.append(line);
//				}
//	
//				JSONObject json = null;
//				try {
//					json = new JSONObject(builder.toString());
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				JSONObject json2 = json.optJSONObject("responseData");
//				
//				JSONArray json3 = null;
//				try {
//					json3 = json2.getJSONArray("results");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//				for (int i = 0; i < json3.length(); ++i) {
//				    JSONObject rec=null;
//					try {
//						rec = json3.getJSONObject(i);
//						out.println("<tr>");
//						out.println("<td> LIEN NO " + ((j*4)+i+1));
//						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println(" <td>DESCR: " + rec.getString("content"));
//						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println("<td>TITRE NON FORMATE: " + rec.getString("titleNoFormatting"));
//						out.println("</td></tr>");
////						out.println("<tr>");
////						out.println("</td>TITRE: " +rec.getString("title"));
////						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println("<td>LIEN: " + rec.getString("url"));
//						out.println("</td></tr>");
//						out.println("<td>-----------------------------");
//						out.println("</td>");
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				try {
//					out.println(json2.getJSONObject("cursor").getString("currentPageIndex").toString());
//					nbtonextpage = Integer.parseInt(json2.getJSONObject("cursor").getString("currentPageIndex"));
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				nbtonextpage = (nbtonextpage+1) *4;
//				out.println(nbtonextpage);
//				url = new URL(
//					    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//							    + "start=" + nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			
//			}
//			
//			out.println("<form action=\"request\" method=\"GET\">");
//			out.println("les params: <input type=\"text\" name=\"p1\" value=\"\" size=20 />");
//			out.println("<input type=\"submit\" value=\"submit\" />");
//			out.println("</form>");
//			out.println("</table> </body> </html>");
//			
//			
//			
//			
//			
//		} finally {
//			out.close();
//		}
//	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		URL url;
		int nbtonextpage = 0;
		try {
			
			String p = req.getParameter("p1");
			
			url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
			for(int j = 0; j<1; j++ )
			{
				
				URLConnection connection = url.openConnection();
				//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
	
				String line;
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
				 builder.append(line);
				}
	
				JSONObject json = null;
				try {
					json = new JSONObject(builder.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				out.print(json);
				//out.flush();
				
			}
			
		} finally {
			out.close();
		}
	}

}

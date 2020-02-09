/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;
import model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author User
 */
public class Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Persinf p1= new Persinf();
        p1.setName("matina");
        Persinf p2= new Persinf();
        p2.setName("george");
        EntityManager em;  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
         em = emf.createEntityManager();        
         em.getTransaction().begin();
         em.persist(p1);
         em.persist(p2);
         em.getTransaction().commit();

 
	// Αν θέλετε δοκιμάστε σε αυτό το σημείο να ανακτήσετε και να εμφανίσετε τα δεδομένα 
         //της βάσης
       /*  TypedQuery<Persinf> query =
         em.createNamedQuery("Persinf.findAll", Persinf.class);
        List<Persinf> results = query.getResultList();
           for (Persinf so:results)
        
            System.out.println(so.getName());*/
         
        try {// δημιουργία δομής XML δέντρου
	        
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();	
            Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("persons");
		doc.appendChild(rootElement);
 
         TypedQuery<Persinf> query =
         em.createNamedQuery("Persinf.findAll", Persinf.class);
        List<Persinf> results = query.getResultList();
           for (Persinf so:results)
        
          
           {
                Element person = doc.createElement("person");
		rootElement.appendChild(person);
		Attr attr = doc.createAttribute("id");
                attr.setValue(Integer.toString(so.getId()));// ο κωδικός κάθε ατόμου
		person.setAttributeNode(attr);
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(so.getName()));
		person.appendChild(name);
           }
		
		
 
		// εγγραφή δομής xml δένδρου σε αρχείο
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
	
               StreamResult result;  
            result = new StreamResult(new File("file.xml"));
 
		transformer.transform(source, result);
                 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
        }
}

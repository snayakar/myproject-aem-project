package com.myproject.aem.core.services;

import com.day.cq.wcm.api.Page;
import com.myproject.aem.core.models.EventsModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomerFormServices {

    private List<EventsModel> newsListGeneric = new ArrayList();
    private List<EventsModel> newsListFeatured = new ArrayList();
    private List<EventsModel> newsListLatest = new ArrayList();

    @Inject
    private ResourceResolver resourceResolver;

    @ScriptVariable(name = CURRENT_PAGE)
    Page currentPage;

    @PostConstruct
    protected void init() throws RepositoryException {
        try {

            if(currentPage.getName().equalsIgnoreCase("events")){
                getEventsListing(currentPage.getPath());
            }else if(currentPage.getName().equalsIgnoreCase("home")){
                // all events are created under this path /content/myproject/home/events
                getEventsListing("/content/myproject/home/events");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Object updateData(Object formData, Map<String, Object> data) throws ApiException {
        //Sample API client to make a Mock call to API
        ApiWebApplicationV1Client client = new ApiWebApplicationV1Client();

        EventRegistrationRequest eventRegistrationRequest= new EventRegistrationRequest();

        EventsController eventsController = client.getEvents();
        try {

            //Make a mock API call to submit the event registration details and get the response from the API.


            return response;
        }catch (APIException | IOException e) {
            logger.error("Throwable - could not update: {}", e.getMessage());

        }catch (Throwable  e) {
            logger.error("APIException | IOException - could not update: {}", e.getMessage());
        }
    }



    private void getEventsListing(String pagePath) throws PathNotFoundException, RepositoryException {
        Resource resource = resourceResolver.getResource(pagePath);
        try {
            if(resource != null){
                Iterator<Resource> eventsPages = resource.listChildren();
                while(eventsPages.hasNext()){
                    Resource eventsPage = eventsPages.next();
                    if(!eventsPage.getName().equalsIgnoreCase("jcr:content")) {
                        Resource eventPageContent = resourceResolver.getResource(eventsPage.getPath()+ "/jcr:content");
                        Node node = eventPageContent.adaptTo(Node.class);
                        EventsModel ns = eventPageContent.adaptTo(EventsModel.class);
                        if(ns.getEventsFeatured().equalsIgnoreCase("Featured")){
                            if(node.hasProperty("cq:lastReplicated")){
                                ns.setEventsPublishingDate(node.getProperty("cq:lastReplicated").getDate().getTime().toString());
                            }else{
                                ns.setEventsPublishingDate(node.getProperty("jcr:created").getDate().getTime().toString());
                            }
                            ns.getEventsPageProperties();
                            this.eventsListFeatured.add(ns);
                        } else if(ns.getEventsFeatured().equalsIgnoreCase("Latest")){
                            if(node.hasProperty("cq:lastReplicated")){
                                ns.setEventsPublishingDate(node.getProperty("cq:lastReplicated").getDate().getTime().toString());
                            }else{
                                ns.setEventsPublishingDate(node.getProperty("jcr:created").getDate().getTime().toString());
                            }
                            ns.getEventsPageProperties();
                            this.eventsListLatest.add(ns);
                        } else if(ns.getEventsFeatured().equalsIgnoreCase("Generic")){
                            if(node.hasProperty("cq:lastReplicated")){
                                ns.setEventsPublishingDate(node.getProperty("cq:lastReplicated").getDate().getTime().toString());
                            }else{
                                ns.setEventsPublishingDate(node.getProperty("jcr:created").getDate().getTime().toString());
                            }
                            ns.getEventsPageProperties();
                            this.eventsListLatest.add(ns);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EventsModel> getNewsListGeneric() {
        return newsListGeneric;
    }

    public List<EventsModel> getNewsListFeatured() {
        return newsListFeatured;
    }

    public List<EventsModel> getNewsListLatest() {
        return newsListLatest;
    }
}

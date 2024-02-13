package com.myproject.aem.core.models;

import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;


import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class)
public class EventsModel {

    private String name;

    private String email;

    private String id;



    private String eventsDescription;
    private String eventsPublishingSource;
    private String eventsPublishingDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventsDescription() {
        return eventsDescription;
    }

    public void setEventsDescription(String eventsDescription) {
        this.eventsDescription = eventsDescription;
    }

    public String getEventsPublishingSource() {
        return eventsPublishingSource;
    }

    public void setEventsPublishingSource(String eventsPublishingSource) {
        this.eventsPublishingSource = eventsPublishingSource;
    }

    public String getEventsPublishingDate() {
        return eventsPublishingDate;
    }

    public void setEventsPublishingDate(String eventsPublishingDate) {
        this.eventsPublishingDate = eventsPublishingDate;
    }

}

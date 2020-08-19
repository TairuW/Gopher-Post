package com.example.gopherpost;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Event {
    private static SparseArray<Event> allEvents = new SparseArray<>();
    public static Event getEvent(int id){ return  allEvents.get(id, null); }
    public static int getNewId(){
        if(allEvents.size() > 0) {
            return allEvents.keyAt(allEvents.size() - 1) + 1; // guaranteed to be bigger than any key still active.
        } else {
            return 1; // this is the first key to be created; let's use 1
        }
    }
    public static List<Event> getValidEvents(){
        List<Event> validEvents = new LinkedList<>();
        for(int i = 0; i < allEvents.size(); i++){
            Event e = allEvents.valueAt(i);
            if(e.getFinished()&& e.isApproved()){  // this is our definition of "valid"
                validEvents.add(e);
            }
        }

        return validEvents;
    }
    public static List<Event> getFeaturedEvents(){
        List<Event> relevantEvents = new LinkedList<>();
        for(int i = 0; i < allEvents.size(); i++){
            Event e = allEvents.valueAt(i);
            if(e.getFinished() && e.isApproved() && e.isFeatured()){  // is valid and is featured
                relevantEvents.add(e);
            }
        }

        return relevantEvents;
    }
    public static Event getNextPostToModerate(){
        for(int i = 0; i < allEvents.size(); i++){
            Event e = allEvents.valueAt(i);
            if(!e.moderated && e.finished){  // is not yet moderated
                return e;
            }
        }

        return null;
    }


    private int id;
    private String title;                                   // plain text
    private String shortdesc;                               // plain text
    private String longdesc;                                // supports Markdown
    private LinkedList<String> tags = new LinkedList<>();   // all the tags associated with this post
    private double rating = 0;                              // automatically updated based on ratings
    private SparseIntArray ratings;                         // <userid (int), rating (1-5) (int)>
    private String location;                                // the location entered by the user, or blank if no location
    private GregorianCalendar startCal;                     // the time an event starts, or the time a post becomes valid
    private GregorianCalendar endCal;                       // the time an event or post ends and becomes invalid (don't display)
    private String contactEmail;                            // plain text email for contacting user.  Not guaranteed to be a valid email address
    private Bitmap image;                                   // display image for the post.
    private Boolean moderated = false;                      // whether a moderator has made a decision about this post (either to approve or disapprove)
    private Boolean approved = false;                       // whether the post has been approved by moderators
    private Boolean finished = false;                       // whether the user is done editing the post (if false, then the event is a draft)
    private Boolean featured = false;                       // whether the post is Featured
    private int authorUserId = -1;                          // who posted this event (-1 for unknown)

    public Event(int id, String title, String shortdesc, String longdesc, String location, GregorianCalendar startCal, GregorianCalendar endCal, String contactEmail, Bitmap image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.longdesc = longdesc;
        this.location = location;
        this.startCal = startCal;
        this.endCal = endCal;
        this.contactEmail = contactEmail;
        this.image = image;

        allEvents.put(id, this);

    }
    public Event(int id) {
        this.id = id;
        title = "No Title";
        longdesc = "*No Description*";
        shortdesc = "";
        startCal = new GregorianCalendar();
        endCal = new GregorianCalendar();
        // todo: do something for a default image?

        allEvents.put(id, this);
    }
    public Event() {
        this.id = getNewId();
        title = "No Title";
        longdesc = "*No Description*";
        shortdesc = "";
        startCal = new GregorianCalendar();
        endCal = new GregorianCalendar();
        // todo: do something for a default image?

        allEvents.put(id, this);
    }

    // should be done at deletion of event to remove from listings.  Can not be undone.
    public void RemoveFromListings(){ allEvents.remove(id); }

    public int getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title){ this.title = title; }

    //defaults to longdesc if shortdesc isn't filled, despite markdown formatting
    public String getShortdesc() { return shortdesc.isEmpty() ? longdesc : shortdesc; }
    public void setShortdesc(String shortdesc) { this.shortdesc = shortdesc; }

    public String getLongdesc() { return longdesc; }
    public void setLongdesc(String longdesc){ this.longdesc = longdesc; }

    public LinkedList<String> getTags(){ return tags; }
    public void setTags(LinkedList<String> tags){ this.tags = tags; }
    public void addTag(String tag){ tags.add(tag); }
    public void removeTag(String tag){ tags.remove(tag); }  //todo: check if this works.  Might only work if the actual same object (memory address) is passed.

    private double recalculateRating() {
        rating = 0;
        for (int i=0, size = ratings.size(); i<size; i++) {
            rating += ratings.valueAt(i);
        }
        rating /= ratings.size();

        return rating;
    }
    public double getRating() { return rating; }
    public double addRating(int userId, int userRating) {
        ratings.put(userId, userRating);
        return recalculateRating(); // the current average rating for this post
    }
    public double removeRating(int userId){
        ratings.delete(userId);
        return recalculateRating();
    }

    public String getLocation(){ return location; }
    public void setLocation(String location){ this.location = location; }

    public GregorianCalendar getStartCal(){ return startCal; }
    public void setStartCal(GregorianCalendar startCal){ this.startCal = startCal; }
    public void setStartDate(int year, int month, int dayOfMonth){
        startCal.set(GregorianCalendar.YEAR, year);
        startCal.set(GregorianCalendar.MONTH, month);
        startCal.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
    }
    public void setStartTime(int hourOfDay, int minute){
        startCal.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
        startCal.set(GregorianCalendar.MINUTE, minute);
    }
    public GregorianCalendar getEndCal(){ return endCal; }
    public void setEndCal(GregorianCalendar endCal){ this.endCal = endCal; }
    public void setEndDate(int year, int month, int dayOfMonth){
        endCal.set(GregorianCalendar.YEAR, year);
        endCal.set(GregorianCalendar.MONTH, month);
        endCal.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
    }
    public void setEndTime(int hourOfDay, int minute){
        endCal.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
        endCal.set(GregorianCalendar.MINUTE, minute);
    }

    public String getContactEmail(){ return contactEmail; }
    public void setContactEmail(String contactEmail){ this.contactEmail = contactEmail; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap image){ this.image = image; }

    public Boolean needsModeration(){ return !moderated; }
    public Boolean isApproved(){ return approved; }
    public void approvePost(){ approved = moderated = true; }
    public void disapprovePost(){ approved = false; moderated = true; }

    public Boolean getFinished() { return finished; }
    public void setFinished(Boolean finish) { this.finished = finish; }

    public Boolean isFeatured() { return featured; }
    public void setFeatured(Boolean featured){ this.featured = featured; }

    public int getAuthorUserId(){ return authorUserId; }
    public void setAuthorUserId(int userId){ authorUserId = userId; }
}

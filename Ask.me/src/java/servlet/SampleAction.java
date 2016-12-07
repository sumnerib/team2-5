package servlet;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;

public class SampleAction extends ActionSupport implements SessionAware {

    private Map<String, Object> userSession;

    private String name;
    private String error;
    private String bio;
    private String favouriteColor;
    private String Friends;
    private String friendsInline;
    private String bestFriend;
    private boolean legalAge;
    private String region;
    private String picture;
    private String thoughts;

    @Override
    public String execute() {
        return SUCCESS;
    }

    @Override
    public void validate() {   
        if (name.length() == 0) {
            addFieldError("name", "Name is required");
        }
    }
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }

    public Map<String, Object> getUserSession() {
        return userSession;
    }

    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public void setFavouriteColor(String favouriteColor) {
        this.favouriteColor = favouriteColor;
    }

    public String getFriends() {
        return Friends;
    }

    public void setFriends(String Friends) {
        this.Friends = Friends;
    }

    public String getFriendsInline() {
        return friendsInline;
    }

    public void setFriendsInline(String friendsInline) {
        this.friendsInline = friendsInline;
    }

    public String getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(String bestFriend) {
        this.bestFriend = bestFriend;
    }

    public boolean isLegalAge() {
        return legalAge;
    }

    public void setLegalAge(boolean legalAge) {
        this.legalAge = legalAge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    
}

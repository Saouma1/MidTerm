package com.example.midterm;

/**
 * Represents an athletic entity with an image URL, title, and number.
 * This class can be used to hold information about an athlete or an athletic event.
 */
public class Athletics {
    private String imgUrl;
    private String title;
    private String number;

    /**
     * Default constructor.
     */
    public Athletics(){
        // Default constructor
    }

    /**
     * Constructs an Athletics object with specified image URL, title, and number.
     *
     * @param imgUrl The image URL of the athletic entity.
     * @param title The title or name of the athletic entity.
     * @param number The number associated with the athletic entity.
     */
    public Athletics(String imgUrl, String title, String number) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.number = number;
    }

    /**
     * Returns the image URL of the athletic entity.
     *
     * @return A string representing the image URL.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Sets the image URL of the athletic entity.
     *
     * @param imgUrl A string containing the image URL to be set.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Returns the title of the athletic entity.
     *
     * @return A string representing the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the athletic entity.
     *
     * @param title A string containing the title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the number associated with the athletic entity.
     *
     * @return A string representing the number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number associated with the athletic entity.
     *
     * @param number A string containing the number to be set.
     */
    public void setNumber(String number) {
        this.number = number;
    }
}

import book.Author;

import java.util.Date;

public class AuthorTalk {
    private String talkTitle;
    private Author author;
    private String topic;
    private Date startDate;
    private int periodInMinutes;

    public AuthorTalk(String talkTitle, String author, String topic, Date startDate, int periodInMinutes) {
        this.talkTitle = talkTitle;
        this.author = Author.getAuthor(author);
        this.topic = topic;
        this.startDate = startDate;
        this.periodInMinutes = periodInMinutes;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTopic() {
        return topic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public boolean isUpcoming() {
        return startDate.after(new Date());
    }


    public int getPeriodInMinutes() {
        return periodInMinutes;
    }

    public void displayInformation() {
        System.out.println("---------------------------------------------------");
        System.out.println("Title: " + talkTitle);
        System.out.println("Author: " + author.getName());
        System.out.println("Topic: " + topic);
        System.out.println("Date: " + utils.DateHelper.formatDateTime(startDate));
        System.out.println("Period: " + periodInMinutes + " minutes");
    }
}

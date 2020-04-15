package springapp.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "Select m From Message m"),
    @NamedQuery(name = "Message.removeAll", query = "Delete From Message")
})

@Entity
public class Message {

    @Id
    @GeneratedValue
    Integer number;

    @Column
    String text;

    public Message() {
        super();
    }

    public Message(String text) {
        super();
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Message [number=%s, text=%s]", number, text);
    }

}
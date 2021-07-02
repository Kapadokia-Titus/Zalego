package kapadokia.nyandoro.zalego.refferal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Refer {

    @Id
    public String id;
    private String client_name;
    private String client_email;
    private String client_phone;
    private String client_course;
    private String status;
    private String sender_uid;

    public Refer(){}
    public Refer(String client_name, String client_email, String client_phone, String client_course, String status) {
        this.client_name = client_name;
        this.client_email = client_email;
        this.client_phone = client_phone;
        this.client_course = client_course;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_course() {
        return client_course;
    }

    public void setClient_course(String client_course) {
        this.client_course = client_course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender_uid() {
        return sender_uid;
    }

    public void setSender_uid(String sender_uid) {
        this.sender_uid = sender_uid;
    }
}

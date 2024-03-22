package models;

public class ContactResponseModel {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;

    public ContactResponseModel(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactResponseModel{" +
                "message='" + message + '\'' +
                '}';
    }
}

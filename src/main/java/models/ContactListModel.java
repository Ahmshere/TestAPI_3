package models;

import java.util.List;

public class ContactListModel {
    public List<ContactModel> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

  private List<ContactModel> contacts;

    @Override
    public String toString() {
        return "ContactListModel{" +
                "contacts=" + contacts +
                '}';
    }
    public ContactListModel contacts(List<ContactModel> contacts) {
        this.contacts = contacts;
        return this;
    }
}

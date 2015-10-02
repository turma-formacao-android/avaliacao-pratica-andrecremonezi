package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Contact implements Parcelable {
    private Long id;
    private String name;
    private List<Email> emails;
    private List<SocialNetwork> socialNetworks;
    private List<Telephone> telephones;
    private Address address;


    public Contact(){
        super();
        address = new Address();
        socialNetworks = new ArrayList<SocialNetwork>();
        telephones     = new ArrayList<Telephone>();
        emails         = new ArrayList<Email>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!id.equals(contact.id)) return false;
        if (!name.equals(contact.name)) return false;
        if (!emails.equals(contact.emails)) return false;
        if (!socialNetworks.equals(contact.socialNetworks)) return false;
        if (!telephones.equals(contact.telephones)) return false;
        return address.equals(contact.address);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + emails.hashCode();
        result = 31 * result + socialNetworks.hashCode();
        result = 31 * result + telephones.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.telephones = in.createTypedArrayList(Telephone.CREATOR);
        this.socialNetworks = in.createTypedArrayList(SocialNetwork.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(this.address, 0);
        dest.writeList(emails);
        dest.writeList(socialNetworks);
        dest.writeList(telephones);
    }
}

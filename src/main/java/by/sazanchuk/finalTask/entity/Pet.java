package by.sazanchuk.finalTask.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * entity pet
 */
public class Pet {
    private Integer identity;
    private int user_identity;
    private String name;
    private PetList kind;
    private java.util.Date dateOfBirth;

    /**
     * Instantiates a new Pet.
     */
    public Pet () {}

    /**
     * gets user identity
     *
     * @return user_identity
     */
    public int getUser_identity() {
        return user_identity;
    }

    /**
     * @param user_identity the user identity
     */
    public void setUser_identity(int user_identity) {
        this.user_identity = user_identity;
    }

    /**
     * @param identity the identity
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param kind the kind
     */
    public void setKind(PetList kind) {
        this.kind = kind;
    }

    /**
     * gets date of pets birth
     *
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the date
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * gets identity
     *
     * @return identity
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return kind
     */
    public PetList getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return user_identity == pet.user_identity &&
                Objects.equals(identity, pet.identity) &&
                Objects.equals(name, pet.name) &&
                kind == pet.kind &&
                Objects.equals(dateOfBirth, pet.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, user_identity, name, kind, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "identity=" + identity +
                ", user_identity=" + user_identity +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

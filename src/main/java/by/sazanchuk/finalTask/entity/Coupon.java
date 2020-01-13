package by.sazanchuk.finalTask.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Entity coupon
 */
public class Coupon {
    private Integer identity;
    private Date time;
    private Integer doctor_id;
    private Integer user_id;
    private Integer pet_id;
    private Integer service_id;

    /**
     * Instantiates a new Coupon.
     */
    public Coupon() {}

    /**
     * gets service identity
     *
     * @return service_id
     */
    public Integer getService_id() {
        return service_id;
    }

    /**
     * @param service_id the service identity
     */
    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    /**
     * @param doctor_id the doctor identity
     */
    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    /**
     * @param user_id the user identity
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @param pet_id the pet identity
     */
    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    /**
     * gets doctor identity
     *
     * @return doctor_id
     */
    public Integer getDoctor_id() {
        return doctor_id;
    }

    /**
     * gets user identity
     *
     * @return user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * gets pet identity
     *
     * @return pet_id
     */
    public Integer getPet_id() {
        return pet_id;
    }

    /**
     * gets coupon identity
     *
     * @return identity
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * gets time of coupon
     *
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param identity the identity
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * @param time the time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(identity, coupon.identity) &&
                Objects.equals(time, coupon.time) &&
                Objects.equals(doctor_id, coupon.doctor_id) &&
                Objects.equals(user_id, coupon.user_id) &&
                Objects.equals(pet_id, coupon.pet_id) &&
                Objects.equals(service_id, coupon.service_id);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "identity=" + identity +
                ", time=" + time +
                ", doctor_id=" + doctor_id +
                ", user_id=" + user_id +
                ", pet_id=" + pet_id +
                ", service_id=" + service_id +
                '}';
    }
}

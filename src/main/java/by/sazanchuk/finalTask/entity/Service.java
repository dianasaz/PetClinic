package by.sazanchuk.finalTask.entity;

import java.util.Objects;

/**
 * entity service
 */
public class Service {
    private Integer identity;
    private String name;
    private Integer price;

    /**
     * Instantiates a new Service.
     */
    public Service() {}

    /**
     * @param identity the identity
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * @param price the price
     */
    public void setPrice(Integer price) {
        this.price = price;
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
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets price
     *
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    public String getClassName(){
        return "Service";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return identity == service.identity &&
                price == service.price &&
                name == service.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, price);
    }

    @Override
    public String toString() {
        return "Service{" +
                "identity=" + identity +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}

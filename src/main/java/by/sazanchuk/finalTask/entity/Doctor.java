package by.sazanchuk.finalTask.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * entity doctor
 */
public class Doctor {
    private Integer identity;
    private String name;
    private List<Service> service;

    /**
     * Instantiates a new Doctor.
     */
    public Doctor() {}

    /**
     * @param service list of services
     */
    public void setService(List<Service> service) {
        this.service = service;
    }

    /**
     * add service to list of services
     *
     * @param s service
     */
    public void addService(Service s){
        if (service == null){
            service = new ArrayList<>();
        }
        service.add(s);
    }

    /**
     * remove all services
     */
    public void removeServices(){
        if (service != null) {
            if (service.size() != 0) {
                for (int i = 0; i < service.size(); i++) {
                    service.remove(i);
                }
            }
        }
    }

    public String getClassName(){
        return "Doctor";
    }

    /**
     * gets identity of doctor
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
     * gets services
     *
     * @return list of services
     */
    public List<Service> getService() {
        return service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return identity == doctor.identity &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(service, doctor.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, service);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "identity=" + identity +
                ", name='" + name +
                ", service=" + service +
                '}';
    }
}

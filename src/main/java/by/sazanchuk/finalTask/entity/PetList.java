package by.sazanchuk.finalTask.entity;

/**
 * enum PetList
 */
public enum PetList {
    CAT ("cat"),
    DOG ("dog"),
    TURTLE ("turtle"),
    PARROT ("parrot"),
    HAMSTER ("hamster");

    private String name;

    PetList(String name) {
        this.name = name;
    }

    /**
     * gets kind of pet
     *
     * @param pet the name of pet
     *
     * @return kind of pet
     */
    public static PetList setPet(String pet){
        PetList r = null;
        if (pet.equalsIgnoreCase("cat")) r = CAT;
        if (pet.equalsIgnoreCase("dog")) r = DOG;
        if (pet.equalsIgnoreCase("turtle")) r = TURTLE;
        if (pet.equalsIgnoreCase("parrot")) r = PARROT;
        if (pet.equalsIgnoreCase("hamster")) r = HAMSTER;
        return r;
    }

    @Override
    public String toString() {
        return name;
    }
}

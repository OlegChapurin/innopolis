package lesson.task.service;

/**
 * @author Oleg_Chapurin
 */
public enum TypePage {
    ZERO("ZERO"),
    ENTER("ENTER"),
    REG("REG"),
    EDIT("EDIT");
    private String typePage;

    TypePage(String typePage) {
        this.typePage = typePage;
    }

    public String getTypePage() {
        return typePage;
    }
}

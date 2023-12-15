public interface Room {

    /**
     * Enter the room
     */
    void enter();

    /**
     * Exit the room
     */
    void exit();

    /**
     * Get the state of completion of the room
     *
     * @return Whether the room is complete or not
     */
    boolean isComplete();

    /**
     * Get the total time spend in the room in milliseconds
     *
     * @return The time spend in the room in milliseconds
     */
    long getTimeSpend();
}

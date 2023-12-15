public interface Room {

    /**
     * Enter the room
     *
     * @param player The player to enter the room
     */
    void enter(Player player);

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

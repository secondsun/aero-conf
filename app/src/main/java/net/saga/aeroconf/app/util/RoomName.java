package net.saga.aeroconf.app.util;

/**
 * Created by summers on 1/3/14.
 */
public enum RoomName {
    BALLROOM_A("Ballroom A", "track_1"),
    BALLROOM_B("Ballroom B", "track_2"),
    BALLROOM_C("Ballroom C", "track_3"),
    BALLROOM_D("Ballroom D", "track_3"),
    BALLROOM_E("Ballroom E", "track_4"),
    BALLROOM_F("Ballroom F", "track_3"),
    BALLROOM_G("Ballroom G", "track_1"),
    ROOM_102("Room 102", "track_102"),
    ROOM_103("Room 103", "track_5"),
    ROOM_104("Room 104", "track_6"),
    ROOM_105("Room 105", "track_7"),
    ROOM_113("Room 113", "track_11"),
    JOCKS_AND_JILLS("Jocks and Jills", "track_1");

    public final String roomName;
    public final String trackName;

    private RoomName(String roomName, String trackName) {
        this.roomName = roomName;
        this.trackName = trackName;
    }

    public static RoomName room(String roomName) {
        for (RoomName room : RoomName.values()) {
            if (room.roomName.equals(roomName)) {
                return room;
            }
        }

        throw new IllegalArgumentException("No such room " + roomName);
    }

}
package org.example.socialmediaapi.constants;

public enum InteractionType {

    COMMENT(0),
    LIKE(1),
    DISLIKE(2);

    private final int value;

    InteractionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

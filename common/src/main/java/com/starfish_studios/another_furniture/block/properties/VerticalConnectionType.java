package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.util.StringRepresentable;

public enum VerticalConnectionType implements StringRepresentable {
    SINGLE("single"),
    BOTTOM("bottom"),
    MIDDLE("middle"),
    TOP("top");

    private final String name;

    private VerticalConnectionType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }

    public boolean isConnectedUp() {
        return (this.equals(MIDDLE) || this.equals(BOTTOM));
    }

    public boolean isConnectedDown() {
        return (this.equals(MIDDLE) || this.equals(BOTTOM));
    }
}
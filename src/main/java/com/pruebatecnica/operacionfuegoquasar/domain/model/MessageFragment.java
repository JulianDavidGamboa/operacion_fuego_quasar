package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

import java.awt.*;

@Data
public class MessageFragment {
    private Point position;
    private String[] message;

    public MessageFragment(Point position, String[] message) {
        this.position = position;
        this.message = message;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

}

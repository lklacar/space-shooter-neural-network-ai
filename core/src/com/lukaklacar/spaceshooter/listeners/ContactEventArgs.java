package com.lukaklacar.spaceshooter.listeners;

import com.lukaklacar.spaceshooter.entities.PhysicsEntity;

/**
 * Created by Luka on 7/8/2016.
 */
public class ContactEventArgs extends EventArgs {

    private PhysicsEntity first;

    public ContactEventArgs(PhysicsEntity first, PhysicsEntity second) {
        this.first = first;
        this.second = second;
    }

    private PhysicsEntity second;

    public PhysicsEntity getFirst() {
        return first;
    }

    public void setFirst(PhysicsEntity first) {
        this.first = first;
    }

    public PhysicsEntity getSecond() {
        return second;
    }

    public void setSecond(PhysicsEntity second) {
        this.second = second;
    }
}

package com.lukaklacar.spaceshooter.listeners;

/**
 * Created by Luka on 7/8/2016.
 */
public abstract class ContactListener extends Listener<ContactEventArgs> {


    @Override
    public abstract void execute(ContactEventArgs args);
}

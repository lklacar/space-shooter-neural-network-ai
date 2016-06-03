package com.lukaklacar.spaceshooter.graphics.screens;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by l.klacar on 3.6.2016..
 */
public class GameScreen extends AbstractBox2dScreen {

    public GameScreen(int widthInMeters, int heightInMeters) {
        super(widthInMeters, heightInMeters);
    }

    @Override
    public void show() {
        super.show();
        addBody1();
        addBody2();

    }


    private void addBody2() {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(0, 0);


        Body body = getWorld().createBody(bodyDef);

        PolygonShape circle = new PolygonShape();
        circle.setAsBox(10, 0.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;


        Fixture fixture = body.createFixture(fixtureDef);


        circle.dispose();
    }


    private void addBody1() {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(3, 3);


        Body body = getWorld().createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(0.3f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;


        Fixture fixture = body.createFixture(fixtureDef);

        body.applyAngularImpulse(-0.1f, true);


        circle.dispose();
    }
}

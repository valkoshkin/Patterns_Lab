package com.valkoshkin.observer.model.publisher;

import com.valkoshkin.observer.model.common.FaceEvent;
import com.valkoshkin.observer.model.subscriber.Subscriber;

import java.util.ArrayList;

public class PictureEventPublisher implements Publisher {
    private final ArrayList<Subscriber> subscribers;

    public PictureEventPublisher() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyAllSubscribers(FaceEvent event) {
        for (var subscriber: subscribers) {
            subscriber.update(event);
        }
    }
}

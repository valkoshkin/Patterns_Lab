package com.valkoshkin.observer.model.publisher;

import com.valkoshkin.observer.model.common.FaceEvent;
import com.valkoshkin.observer.model.subscriber.Subscriber;

public interface Publisher {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifyAllSubscribers(FaceEvent event);
}

package com.valkoshkin.observer.model.subscriber;

import com.valkoshkin.observer.model.common.FaceEvent;

public interface Subscriber {
    void update(FaceEvent event);
}

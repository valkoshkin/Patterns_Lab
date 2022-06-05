package com.valkoshkin.model.strategy;

import com.valkoshkin.model.AverageMark;

public interface Strategy {
    AverageMark getAverageMark() throws Exception;
    void fixAverageMark(double averageMark, String outputFilePath) throws Exception;
}

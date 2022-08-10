package com.flying.demo.physique;

import lombok.Data;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/8/8 16:00
 * @Description 体质类
 */
@Data
public class Physique {
    /**
     * 体质类型（1 阳虚质，2 阴虚质，3 气虚质，4 痰湿质，5 湿热质，6 血瘀质，7 特禀质，8 气郁质，9 平和质）
     */
    private Integer type;
    /**
     * 原始分
     */
    private Integer originalScore;
    /**
     * 转化分
     */
    private Integer conversionScore;
    /**
     * 各条目分值
     */
    List<EntryScore> entryScores;

    void calculateReverseScore() {
        int maxScore = 5;
        int minScore = 1;
        this.getEntryScores().forEach(entryScore -> {
            if (entryScore.isReverse()) {
                entryScore.setScore(maxScore + minScore - entryScore.getScore());
            }
        });
    }

    Physique(Integer type, List<EntryScore> entryScores) {
        this.type = type;
        this.entryScores = entryScores;
    }
}

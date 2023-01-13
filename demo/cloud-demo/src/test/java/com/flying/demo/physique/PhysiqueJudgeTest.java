package com.flying.demo.physique;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/8/16 15:07
 * @Description
 */
public class PhysiqueJudgeTest {

    public static void main(String[] args) {
        List<Physique> physique = new ArrayList<>();
        // 阳虚质
        EntryScore yangXuScore1 = new EntryScore(1, false);
        EntryScore yangXuScore2 = new EntryScore(1, false);
        EntryScore yangXuScore3 = new EntryScore(1, false);
        EntryScore yangXuScore4 = new EntryScore(1, false);
        EntryScore yangXuScore5 = new EntryScore(1, false);
        EntryScore yangXuScore6 = new EntryScore(1, false);
        EntryScore yangXuScore7 = new EntryScore(1, false);
        List<EntryScore> yangXuEntryScores = Lists.newArrayList(yangXuScore1, yangXuScore2, yangXuScore3, yangXuScore4, yangXuScore5, yangXuScore6, yangXuScore7);
        Physique yangXu = new Physique(1, yangXuEntryScores);
        //physique.add(yangXu);
        // 阴虚质
        EntryScore yinXuScore1 = new EntryScore(4, false);
        EntryScore yinXuScore2 = new EntryScore(4, false);
        EntryScore yinXuScore3 = new EntryScore(3, false);
        EntryScore yinXuScore4 = new EntryScore(4, false);
        EntryScore yinXuScore5 = new EntryScore(4, false);
        EntryScore yinXuScore6 = new EntryScore(1, false);
        EntryScore yinXuScore7 = new EntryScore(5, false);
        EntryScore yinXuScore8 = new EntryScore(4, false);
        List<EntryScore> yinXuEntryScores = Lists.newArrayList(yinXuScore1, yinXuScore2, yinXuScore3, yinXuScore4, yinXuScore5, yinXuScore6, yinXuScore7, yinXuScore8);
        Physique yinXu = new Physique(2, yinXuEntryScores);
        physique.add(yinXu);
        // 气虚质
        EntryScore qiXuScore1 = new EntryScore(4, false);
        EntryScore qiXuScore2 = new EntryScore(1, false);
        EntryScore qiXuScore3 = new EntryScore(2, false);
        EntryScore qiXuScore4 = new EntryScore(3, false);
        EntryScore qiXuScore5 = new EntryScore(1, false);
        EntryScore qiXuScore6 = new EntryScore(5, false);
        EntryScore qiXuScore7 = new EntryScore(2, false);
        EntryScore qiXuScore8 = new EntryScore(5, false);
        List<EntryScore> qiXuEntryScores = Lists.newArrayList(qiXuScore1, qiXuScore2, qiXuScore3, qiXuScore4, qiXuScore5, qiXuScore6, qiXuScore7, qiXuScore8);
        Physique qiXu = new Physique(3, qiXuEntryScores);
        //physique.add(qiXu);
        // 痰湿质
        EntryScore tanShiScore1 = new EntryScore(2, false);
        EntryScore tanShiScore2 = new EntryScore(4, false);
        EntryScore tanShiScore3 = new EntryScore(3, false);
        EntryScore tanShiScore4 = new EntryScore(3, false);
        EntryScore tanShiScore5 = new EntryScore(2, false);
        EntryScore tanShiScore6 = new EntryScore(1, false);
        EntryScore tanShiScore7 = new EntryScore(1, false);
        EntryScore tanShiScore8 = new EntryScore(1, false);
        List<EntryScore> tanShiEntryScores = Lists.newArrayList(tanShiScore1, tanShiScore2, tanShiScore3, tanShiScore4, tanShiScore5, tanShiScore6, tanShiScore7, tanShiScore8);
        Physique tanShi = new Physique(4, tanShiEntryScores);
        //physique.add(tanShi);
        // 湿热质
        EntryScore shiReScore1 = new EntryScore(2, false);
        EntryScore shiReScore2 = new EntryScore(1, false);
        EntryScore shiReScore3 = new EntryScore(1, false);
        EntryScore shiReScore4 = new EntryScore(4, false);
        EntryScore shiReScore5 = new EntryScore(4, false);
        EntryScore shiReScore6 = new EntryScore(1, false);
        EntryScore shiReScore7 = new EntryScore(4, false);
        List<EntryScore> shiReEntryScores = Lists.newArrayList(shiReScore1, shiReScore2, shiReScore3, shiReScore4, shiReScore5, shiReScore6, shiReScore7);
        Physique shiRe = new Physique(5, shiReEntryScores);
        //physique.add(shiRe);
        // 血瘀质
        EntryScore xueYuScore1 = new EntryScore(1, false);
        EntryScore xueYuScore2 = new EntryScore(1, false);
        EntryScore xueYuScore3 = new EntryScore(3, false);
        EntryScore xueYuScore4 = new EntryScore(1, false);
        EntryScore xueYuScore5 = new EntryScore(4, false);
        EntryScore xueYuScore6 = new EntryScore(4, false);
        EntryScore xueYuScore7 = new EntryScore(1, false);
        List<EntryScore> xueYuEntryScores = Lists.newArrayList(xueYuScore1, xueYuScore2, xueYuScore3, xueYuScore4, xueYuScore5, xueYuScore6, xueYuScore7);
        Physique xueYu = new Physique(6, xueYuEntryScores);
        //physique.add(xueYu);
        // 特禀质
        EntryScore teBingScore1 = new EntryScore(2, false);
        EntryScore teBingScore2 = new EntryScore(1, false);
        EntryScore teBingScore3 = new EntryScore(4, false);
        EntryScore teBingScore4 = new EntryScore(1, false);
        EntryScore teBingScore5 = new EntryScore(1, false);
        EntryScore teBingScore6 = new EntryScore(1, false);
        EntryScore teBingScore7 = new EntryScore(5, false);
        List<EntryScore> teBingEntryScores = Lists.newArrayList(teBingScore1, teBingScore2, teBingScore3, teBingScore4, teBingScore5, teBingScore6, teBingScore7);
        Physique teBing = new Physique(7, teBingEntryScores);
        //physique.add(teBing);
        // 气郁质
        EntryScore qiYuScore1 = new EntryScore(3, false);
        EntryScore qiYuScore2 = new EntryScore(2, false);
        EntryScore qiYuScore3 = new EntryScore(4, false);
        EntryScore qiYuScore4 = new EntryScore(1, false);
        EntryScore qiYuScore5 = new EntryScore(1, false);
        EntryScore qiYuScore6 = new EntryScore(4, false);
        EntryScore qiYuScore7 = new EntryScore(1, false);
        List<EntryScore> qiYuEntryScores = Lists.newArrayList(qiYuScore1, qiYuScore2, qiYuScore3, qiYuScore4, qiYuScore5, qiYuScore6, qiYuScore7);
        Physique qiYu = new Physique(8, qiYuEntryScores);
        //physique.add(qiYu);
        // 平和质
        EntryScore pingHeScore1 = new EntryScore(4, false);
        EntryScore pingHeScore2 = new EntryScore(4, true);
        EntryScore pingHeScore3 = new EntryScore(2, true);
        EntryScore pingHeScore4 = new EntryScore(3, true);
        EntryScore pingHeScore5 = new EntryScore(1, true);
        EntryScore pingHeScore6 = new EntryScore(4, false);
        EntryScore pingHeScore7 = new EntryScore(1, true);
        EntryScore pingHeScore8 = new EntryScore(3, true);
        List<EntryScore> pingHeEntryScores = Lists.newArrayList(pingHeScore1, pingHeScore2, pingHeScore3, pingHeScore4, pingHeScore5, pingHeScore6, pingHeScore7, pingHeScore8);
        Physique pingHe = new Physique(9, pingHeEntryScores);
        //physique.add(pingHe);

        PhysiqueJudge physiqueJudge = new PhysiqueJudge();
        System.out.println("体质判定结果：" + physiqueJudge.physiqueJudge(physique));
    }
}

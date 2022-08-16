package com.flying.demo.physique;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author songfeifei
 * @Date 2022/8/8 10:03
 * @Description
 */
@Slf4j
public class PhysiqueJudge {

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
        physique.add(yangXu);
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
        physique.add(qiXu);
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
        physique.add(tanShi);
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
        physique.add(shiRe);
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
        physique.add(xueYu);
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
        physique.add(teBing);
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
        physique.add(qiYu);
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
        physique.add(pingHe);

        System.out.println("体质判定结果：" + physiqueJudge(physique));
    }

    /**
     * 体质判定
     *
     * @param physiques 体质列表
     * @return 体质判定结果
     */
    private static String physiqueJudge(List<Physique> physiques) {
        StringBuilder sb = new StringBuilder();
        // 计算各体质原始分和转化分
        physiques.forEach(physique -> {
            if (PhysiqueTypeEnum.PING_HE.getCode().equals(physique.getType())) {
                physique.calculateReverseScore();
            }
            // 原始分
            int originalScore = physique.entryScores.stream().mapToInt(EntryScore::getScore).sum();
            physique.setOriginalScore(originalScore);
            // 转化分
            int conversionScore = new BigDecimal(originalScore - physique.getEntryScores().size())
                    .divide(new BigDecimal(physique.getEntryScores().size() * 4), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100))
                    .intValue();
            physique.setConversionScore(conversionScore);
        });

        // 1、平和质判定
        sb.append(judgePeacePhysique(physiques));
        // 2、偏颇体质判定
        sb.append(judgeBiasedPhysique(physiques));

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 判定平和体质
     *
     * @param physiques 体质列表
     * @return 评定结果
     */
    private static String judgePeacePhysique(List<Physique> physiques) {
        StringBuilder sb = new StringBuilder();
        int peacePhysiqueScore = 0;
        List<Integer> biasedPhysiqueScores = new ArrayList<>();
        for (Physique physique : physiques) {
            if (PhysiqueTypeEnum.PING_HE.getCode().equals(physique.getType())) {
                peacePhysiqueScore = physique.getConversionScore();
                continue;
            }
            biasedPhysiqueScores.add(physique.getConversionScore());
        }
        log.info("平和质转化分：{}，偏颇体质转化分：{}", peacePhysiqueScore, biasedPhysiqueScores.toString());

        if (peacePhysiqueScore >= 60 && checkBiasedPhysiqueScore(biasedPhysiqueScores, 30)) {
            // 平和质转化分 >= 60 且 其他8种体质转化分均 < 30
            sb.append("平和质，");
        } else if (peacePhysiqueScore >= 60 && checkBiasedPhysiqueScore(biasedPhysiqueScores, 40)) {
            // 平和质转化分 >= 60 且 其他8种体质转化分均 < 40
            sb.append("基本是平和质，");
        }
        return sb.toString();
    }

    /**
     * 判定偏颇体质
     *
     * @param physiques 体质列表
     * @return 评定结果
     */
    private static String judgeBiasedPhysique(List<Physique> physiques) {
        StringBuilder sb = new StringBuilder();
        physiques.forEach(physique -> {
            if (PhysiqueTypeEnum.PING_HE.getCode().equals(physique.getType())) {
                return;
            }
            if (physique.getConversionScore() < 30) {
                return;
            }
            PhysiqueTypeEnum physiqueTypeEnum = PhysiqueTypeEnum.getEnumByCode(physique.getType());
            if (Objects.isNull(physiqueTypeEnum)) {
                return;
            }
            switch (physiqueTypeEnum) {
                case YANG_XU:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.YANG_XU, sb);
                    break;
                case YIN_XU:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.YIN_XU, sb);
                    break;
                case QI_XU:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.QI_XU, sb);
                    break;
                case TAN_SHI:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.TAN_SHI, sb);
                    break;
                case SHI_RE:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.SHI_RE, sb);
                    break;
                case XUE_YU:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.XUE_YU, sb);
                    break;
                case TE_BING:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.TE_BING, sb);
                    break;
                case QI_YU:
                    compareBiasedPhysiqueScore(physique.getConversionScore(), PhysiqueTypeEnum.QI_YU, sb);
                    break;
                default:
            }
        });
        return sb.toString();
    }

    /**
     * 检查其他8种偏颇体质分值范围
     *
     * @param biasedPhysiqueScores 偏颇体质分值
     * @param threshold            阈值
     * @return 是否小于阈值
     */
    private static boolean checkBiasedPhysiqueScore(List<Integer> biasedPhysiqueScores, int threshold) {
        for (Integer score : biasedPhysiqueScores) {
            if (score >= threshold) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比对偏颇体质转化分
     *
     * @param score 偏颇体质转化分
     */
    private static void compareBiasedPhysiqueScore(int score, PhysiqueTypeEnum physiqueTypeEnum, StringBuilder sb) {
        if (score >= 40) {
            sb.append(physiqueTypeEnum.getDesc()).append("，");
        } else if (score >= 30) {
            sb.append("有").append(physiqueTypeEnum.getDesc()).append("倾向，");
        }
    }
}

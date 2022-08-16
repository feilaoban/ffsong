package com.flying.demo.physique;

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

    /**
     * 体质判定
     *
     * @param physiques 体质列表
     * @return 体质判定结果
     */
    String physiqueJudge(List<Physique> physiques) {
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
    private String judgePeacePhysique(List<Physique> physiques) {
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
    private String judgeBiasedPhysique(List<Physique> physiques) {
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
    private boolean checkBiasedPhysiqueScore(List<Integer> biasedPhysiqueScores, int threshold) {
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
    private void compareBiasedPhysiqueScore(int score, PhysiqueTypeEnum physiqueTypeEnum, StringBuilder sb) {
        if (score >= 40) {
            sb.append(physiqueTypeEnum.getDesc()).append("，");
        } else if (score >= 30) {
            sb.append("有").append(physiqueTypeEnum.getDesc()).append("倾向，");
        }
    }
}

package com.flying.demo.physique;

import lombok.Getter;

/**
 * @Author songfeifei
 * @Date 2022/8/8 15:58
 * @Description 体质类型枚举
 */
@Getter
public enum PhysiqueTypeEnum {

    YANG_XU(1, "阳虚质"),
    YIN_XU(2, "阴虚质"),
    QI_XU(3, "气虚质"),
    TAN_SHI(4, "痰湿质"),
    SHI_RE(5, "湿热质"),
    XUE_YU(6, "血瘀质"),
    TE_BING(7, "特禀质"),
    QI_YU(8, "气郁质"),
    PING_HE(9, "平和质"),
    ;

    private Integer code;
    private String desc;

    PhysiqueTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(Integer code) {
        for (PhysiqueTypeEnum item : PhysiqueTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getDesc();
            }
        }
        return "";
    }

    public static PhysiqueTypeEnum getEnumByCode(Integer code) {
        for (PhysiqueTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}


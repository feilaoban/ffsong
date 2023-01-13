package com.flying.demo.physique;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author songfeifei
 * @Date 2022/8/8 15:59
 * @Description 条目分值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryScore {
    /**
     * 分值
     */
    private Integer score;
    /**
     * 是否逆向计分（true 是，false 否。目前仅平和质存在逆向计分的条目）
     */
    private boolean reverse;
}

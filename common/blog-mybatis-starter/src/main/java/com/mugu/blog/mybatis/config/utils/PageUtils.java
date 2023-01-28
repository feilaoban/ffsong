package com.mugu.blog.mybatis.config.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mugu.blog.core.model.BaseParam;
import com.mugu.blog.core.model.PageData;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 分页工具类
 */
public class PageUtils {
    /**
     * 获取分页数据
     * @param param 请求参数
     */
    public static <T,P extends BaseParam> PageData<T> getPage(P param, Consumer<P> consumer){
        Page<T> page = PageHelper.startPage(Objects.requireNonNull(Objects.requireNonNull(param).getPageNum()), Objects.requireNonNull(Objects.requireNonNull(param).getPageSize())).doSelectPage(() -> consumer.accept(param));
        PageData<T> data = new PageData<>();
        data.setResult(page.getResult());
        data.setTotal(page.getTotal());
        data.setPages(page.getPages());
        return data;
    }
}

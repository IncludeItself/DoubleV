package com.wuxinfeng.doublev.mm.datapermission;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-25  07:21
 */
@Slf4j
@Component
public class MyDataPermissionHandler implements DataPermissionHandler {


    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        return null;
    }
}


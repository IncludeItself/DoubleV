package com.wuxinfeng.common.to;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-22  11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiVoucherTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date documentDate;
    private Date postingDate;
    private String reference;
    private String docHeaderText;
    private String creator;
    private List<EntriesTo> entries;
}

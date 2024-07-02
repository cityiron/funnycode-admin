package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingUpdateCommand
 * @description
 * @date 2024/07/02 10:58
 */
@Getter
@Setter
@ToString
public class BookingUpdateCommand extends BaseDTO {

    /**
     * 价格
     */
    private Long price;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 上锁
     */
    private boolean lock;

}

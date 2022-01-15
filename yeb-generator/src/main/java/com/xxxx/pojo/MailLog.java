package com.xxxx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_mail_log")
public class MailLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 接收员工id
     */
    private Integer eid;

    /**
     * 状态（0:消息投递中 1:投递成功 2:投递失败）
     */
    private Integer status;

    /**
     * 路由键
     */
    private String routeKey;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 重试次数
     */
    private Integer count;

    /**
     * 重试时间
     */
    private LocalDateTime tryTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

package com.xucg.common;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.xucg.enums.AliPayEnum;

/**
 * @author xuchenguang
 * @date 2018/4/3
 */
public class AliPayClient {

    public static AlipayClient getAliPayClient() {
        return new DefaultAlipayClient(AliPayEnum.GRATEWAY_URL.getValue(), AliPayEnum.APP_ID.getValue(), AliPayEnum.PRIVATE_KEY.getValue(),
                AliPayEnum.FORMAT.getValue(), AliPayEnum.CHARTSET.getValue(),
                AliPayEnum.PUBLIC_KEY.getValue(), AliPayEnum.SING_TYPE.getValue());
    }
}

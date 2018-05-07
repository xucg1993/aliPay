package com.xucg.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.xucg.common.ResultJson;
import com.xucg.enums.AliPayEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝H5支付
 *
 * @author xuchenguang
 * @date 2018/4/3
 */
public class AliPayWap {

    public static void main(String[] args) {

        //手机网站支付

      /*  AlipayTradeWapPayModel payModel = new AlipayTradeWapPayModel();
        //订单号
        payModel.setOutTradeNo("20181542134311113");
        //金额
        payModel.setTotalAmount("100.00");
        //商品的标题
        payModel.setSubject("周永辉一只");
        //描述信息
        payModel.setBody("低价甩卖");
        //支付终止返回地址
        payModel.setQuitUrl("https://123.sogou.com/");

        String pay = AliPayTrade.wapPay(payModel, "http://www.baidu.com", "http://www.baidu.com");
        JSONObject jsonObject = JSONObject.parseObject(pay);
        String info = jsonObject.getString("info");
        System.out.println(info);*/

        //app支付
        AlipayTradeAppPayModel payModel = new AlipayTradeAppPayModel();
        //订单号
        payModel.setOutTradeNo("2018154213431113");
        //金额
        payModel.setTotalAmount("100.00");
        //商品的标题
        payModel.setSubject("周永辉一只");
        //描述信息
        payModel.setBody("低价甩卖");
        AliPayTrade.appPay(payModel, "http://www.baidu.com", "http://www.baidu.com");

        //查询
        /*AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo("20181542134311113");
        String query = AliPayTrade.query(queryModel);
        System.out.println(query);*/


        //扫码支付
       /* AlipayTradePrecreateModel payModel = new AlipayTradePrecreateModel();
        //订单号
        payModel.setOutTradeNo("201815421343111311");
        //金额
        payModel.setTotalAmount("0.01");
        //商品的标题
        payModel.setSubject("周永辉一只");
        //描述信息
        payModel.setBody("低价甩卖");

        String s = AliPayTrade.preCreate(payModel, "http://www.baidu.com");
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject precreateResponse = jsonObject.getJSONObject("alipay_trade_precreate_response");
        String sign = jsonObject.getString("sign");
        String code = precreateResponse.getString("code");
        String msg = precreateResponse.getString("msg");
        String outTradeNo = precreateResponse.getString("out_trade_no");
        String qrCode = precreateResponse.getString("qr_code");

        System.out.println("code : " + code);
        System.out.println("msg : " + msg);
        System.out.println("outTradeNo : " + outTradeNo);
        System.out.println("qrCode : " + qrCode);
        System.out.println("sign : " + sign);*/
        //2088102175469382


        //退款
       /* AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
        //	商户订单号
        refundModel.setOutTradeNo("20181542134311113");

        //退款金额
        refundModel.setRefundAmount("100.00");

        String refund = AliPayTrade.refund(refundModel);

        System.out.println(refund);*/


        //退款查询
       /* AlipayTradeFastpayRefundQueryModel queryModel = new AlipayTradeFastpayRefundQueryModel();
        queryModel.setOutTradeNo("20181542134311113");
        queryModel.setOutRequestNo("2018041621001004380200703120");
        String s = AliPayTrade.refundQuery(queryModel);
        System.out.println(s);*/

        //电脑网站支付
        /*AlipayTradePagePayModel payModel = new AlipayTradePagePayModel();
        //订单号
        payModel.setOutTradeNo("2018154213431111311");
        //金额
        payModel.setTotalAmount("100.00");
        //商品的标题
        payModel.setSubject("周永辉一只");
        //描述信息
        payModel.setBody("低价甩卖");

        String pay = AliPayTrade.pagePay(payModel, "http://www.baidu.com", "http://www.baidu.com");
        JSONObject jsonObject = JSONObject.parseObject(pay);
        String info = jsonObject.getString("info");
        System.out.println(info);*/


        //关闭订单
        /*AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        closeModel.setOutTradeNo("2018154213431111311");
        String close = AliPayTrade.close(closeModel);
        System.out.println(close);*/

        /*//撤销交易
        AlipayTradeCancelModel cancelModel = new AlipayTradeCancelModel();
        cancelModel.setOutTradeNo("2018154213431111311");
        String cancel = AliPayTrade.cancel(cancelModel);
        System.out.println(cancel);*/
    }

}